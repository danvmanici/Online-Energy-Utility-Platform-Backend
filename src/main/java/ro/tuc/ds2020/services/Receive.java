package ro.tuc.ds2020.services;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Receive {
    static Logger logger = LoggerFactory.getLogger(Receive.class);

    private final static String QUEUE_NAME = "demo";

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
                System.out.println(message);
            }
        };
        channel.basicConsume(QUEUE_NAME, true, consumer);
    }
}
