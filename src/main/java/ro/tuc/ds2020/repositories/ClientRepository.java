package ro.tuc.ds2020.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ro.tuc.ds2020.entities.Client;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ClientRepository extends JpaRepository<Client, UUID> {

    /**
     * Example: JPA generate Query by Field
     */
<<<<<<< HEAD:src/main/java/ro/tuc/ds2020/repositories/ClientRepository.java
    List<Client> findByName(String name);
=======
    Client findByName(String name);
>>>>>>> main:src/main/java/ro/tuc/ds2020/repositories/PersonRepository.java

    /**
     * Example: Write Custom Query
     */


}
