package ro.tuc.ds2020.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ro.tuc.ds2020.entities.Sensor;
import ch.qos.logback.core.net.server.Client;
import ro.tuc.ds2020.entities.Device;
import ro.tuc.ds2020.entities.User;

import java.util.Optional;
import java.util.UUID;

public interface SensorRepository extends JpaRepository<Sensor, UUID>{


    @Query(value = "SELECT s.id, s.max_value, s.description, s.smart_device_id " +
        "FROM sensor s " +
        "JOIN smart_device d ON d.id = s.smart_device_id " +
        "JOIN client c ON c.id = d.client_id " +
        "WHERE c.name = :name", nativeQuery = true  )

    Sensor findSensorValue(@Param("name") String name);
    

}