package ro.tuc.ds2020.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ch.qos.logback.core.net.server.Client;
import ro.tuc.ds2020.entities.Device;
import ro.tuc.ds2020.entities.Sensor;
import ro.tuc.ds2020.entities.User;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

        @Query(value = "SELECT p " +
                "FROM User p " +
                "WHERE p.username = :username " +
                "AND p.password = :password  ")

        User findUsernamePassword(@Param("username") String username, @Param("password") String password);

        
}
