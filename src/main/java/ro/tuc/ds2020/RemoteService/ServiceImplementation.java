package ro.tuc.ds2020.RemoteService;
import org.springframework.beans.factory.annotation.Autowired;
import ro.tuc.ds2020.DependencyInterface.Service;
import ro.tuc.ds2020.entities.Monitored;
import ro.tuc.ds2020.entities.User;
import ro.tuc.ds2020.repositories.MonitoredRepository;
import ro.tuc.ds2020.repositories.UserRepository;

import java.util.ArrayList;
import java.util.List;


public class ServiceImplementation implements Service {

    private final MonitoredRepository monitoredRepository;
    private final UserRepository userRepository;

    @Autowired
    public ServiceImplementation(MonitoredRepository monitoredRepository, UserRepository userRepository) {
        this.monitoredRepository = monitoredRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<String> monitor(String pickUpLocation)  {
        List<Monitored> list = monitoredRepository.findBySensor_id(pickUpLocation);
        List<String> measuredList = new ArrayList<String>();
        for (Monitored l : list) {
            Monitored b = new Monitored(l.getMeasurement_value(),l.getTimestamp());
            measuredList.add(b.toString());

        }
        return measuredList;
    }

    @Override
    public int login(String username, String password){
        User u = userRepository.findUsernamePassword(username,password);
        if (u.getId().toString().equals(""))
            return 1;
        return 0;
    }
}