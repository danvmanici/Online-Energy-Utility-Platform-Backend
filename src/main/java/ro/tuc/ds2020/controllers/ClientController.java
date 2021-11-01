package ro.tuc.ds2020.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.tuc.ds2020.dtos.ClientDTO;
import ro.tuc.ds2020.services.ClientService;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(value = "/client")
public class ClientController {

    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping()
    public ResponseEntity<List<ClientDTO>> getClients() {
        List<ClientDTO> dtos = clientService.findClients();
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @PostMapping("/insert")
    public ResponseEntity<UUID> insertProsumer(@Valid @RequestBody ClientDTO clientDTO) {

        UUID clientID = clientService.
                insert(clientDTO);
        return new ResponseEntity<>(clientID, HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ClientDTO> getClient(@PathVariable("id") UUID clientId) {
        ClientDTO dto = clientService.findClientById(clientId);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }


    @GetMapping(value = "/{name}")
    public ResponseEntity<ClientDTO> getClientName(@PathVariable("name") String name) {
        ClientDTO dto = clientService.findClientByName(name);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<UUID> deleteClient(@PathVariable UUID id){

        UUID clientID = clientService.delete(id);
        return new ResponseEntity<>(clientID, HttpStatus.OK);

    }

    @PutMapping("/update")
    public  ResponseEntity<UUID> updateClient(@Valid @RequestBody ClientDTO clientDetailsDTO){
        UUID clientID = clientService.update(clientDetailsDTO);
        return new ResponseEntity<>(clientID, HttpStatus.OK);
    }


}
