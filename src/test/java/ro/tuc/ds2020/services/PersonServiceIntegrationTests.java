package ro.tuc.ds2020.services;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import ro.tuc.ds2020.Ds2020TestConfig;
import ro.tuc.ds2020.dtos.ClientDTO;

import static org.springframework.test.util.AssertionErrors.assertEquals;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:/test-sql/create.sql")
@Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = "classpath:/test-sql/delete.sql")
public class PersonServiceIntegrationTests extends Ds2020TestConfig {

    @Autowired
    ClientService personService;

    @Test
    public void testGetCorrect() {
        List<ClientDTO> personDTOList = personService.findClients();
        assertEquals("Test Insert Person", 1, personDTOList.size());
    }

    @Test
    public void testInsertCorrectWithGetById() {
        Date firstDate1 = new Date(1999, 10, 10);
        ClientDTO p = new ClientDTO("John", "Somewhere Else street", firstDate1);
        UUID insertedID = personService.insert(p);

        ClientDTO insertedPerson = new ClientDTO(insertedID, p.getName(),p.getAddress(), p.getBirthdate());
        ClientDTO fetchedPerson = personService.findClientById(insertedID);

        assertEquals("Test Inserted Person", insertedPerson, fetchedPerson);
    }

    @Test
    public void testInsertCorrectWithGetAll() {
        Date firstDate1 = new Date(1999, 10, 10);
        ClientDTO p = new ClientDTO("John", "Somewhere Else street", firstDate1);
        personService.insert(p);

        List<ClientDTO> personDTOList = personService.findClients();
        assertEquals("Test Inserted Persons", 2, personDTOList.size());
    }
}
