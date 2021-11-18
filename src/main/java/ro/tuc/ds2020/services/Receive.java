package ro.tuc.ds2020.services;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;
import ro.tuc.ds2020.entities.Monitored;
import ro.tuc.ds2020.repositories.MonitoredRepository;

@Service
@Component
public class Receive {
    static Logger logger = LoggerFactory.getLogger(Receive.class);

    private final static String QUEUE_NAME = "demo";
    private final MonitoredRepository monitoredRepository;

    @Autowired
    public Receive(MonitoredRepository monitoredRepository) {
        this.monitoredRepository = monitoredRepository;
    }

    public List<Monitored> findMonitoredValues(String sensor_id) {
        List<Monitored> list = monitoredRepository.findBySensor_id(sensor_id);
        return list;
    }

    @Scheduled(fixedRate = 10000)
    public void consume() throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties,
                    byte[] body) throws IOException {
                String message = new String(body, "UTF-8");
                JSONParser parser = new JSONParser(JSONParser.MODE_PERMISSIVE);
                try {
                    JSONObject obj = (JSONObject) parser.parse(message);
                    String timestamp = (String) obj.get("timestamp");
                    String sensor_id = (String) obj.get("sensor_id");
                    Double measurement_value = (Double) obj.get("measurement_value");
                    Monitored monitored = new Monitored(sensor_id, measurement_value, timestamp);
                    monitored = monitoredRepository.save(monitored);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                System.out.println(message);
            }
        };
        channel.basicConsume(QUEUE_NAME, true, consumer);
    }
}
