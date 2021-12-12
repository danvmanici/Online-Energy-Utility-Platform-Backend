package ro.tuc.ds2020.RemoteService;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.caucho.HessianServiceExporter;

import org.springframework.remoting.support.RemoteExporter;
import ro.tuc.ds2020.DependencyInterface.Service;
import ro.tuc.ds2020.repositories.MonitoredRepository;
import ro.tuc.ds2020.repositories.UserRepository;

@Configuration
@ComponentScan
@EnableAutoConfiguration
@RequiredArgsConstructor
public class Server {

    final MonitoredRepository monitoredRepository;
    final UserRepository userRepository;
    @Bean(name = "/service")
    RemoteExporter monitorService() {
        HessianServiceExporter exporter = new HessianServiceExporter();
        exporter.setService(new ServiceImplementation(monitoredRepository, userRepository));
        exporter.setServiceInterface( Service.class );
        return exporter;
    }


}
