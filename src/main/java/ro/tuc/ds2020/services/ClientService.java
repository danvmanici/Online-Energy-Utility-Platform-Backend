package ro.tuc.ds2020.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.tuc.ds2020.controllers.handlers.exceptions.model.ResourceNotFoundException;
import ro.tuc.ds2020.dtos.ClientDTO;
import ro.tuc.ds2020.dtos.builders.ClientBuilder;
import ro.tuc.ds2020.entities.Client;
import ro.tuc.ds2020.repositories.ClientRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ClientService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ClientService.class);
    private final ClientRepository clientRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public List<ClientDTO> findClients() {
        List<Client> clientList = clientRepository.findAll();
        return clientList.stream()
                .map(ClientBuilder::toClientDTO)
                .collect(Collectors.toList());
    }

    public ClientDTO findClientById(UUID id) {
        Optional<Client> prosumerOptional = clientRepository.findById(id);
        if (!prosumerOptional.isPresent()) {
            LOGGER.error("client with id {} was not found in db", id);
            throw new ResourceNotFoundException(Client.class.getSimpleName() + " with id: " + id);
        }
        return ClientBuilder.toClientDTO(prosumerOptional.get());
    }

    public UUID insert(ClientDTO clientDTO) {
        Client client = ClientBuilder.toEntity(clientDTO);
        client = clientRepository.save(client);
        LOGGER.debug("client with id {} was inserted in db", client.getId());
        return client.getId();
    }

    public void delete(UUID id) {
        clientRepository.deleteById(id);
        LOGGER.debug("client with id {} was deleted in db", id);
    }

    public UUID update(ClientDTO client){
        Client oldClient = clientRepository.findById(client.getId()).orElse(null);
        assert oldClient != null;
        oldClient.setAddress(client.getAddress());
        oldClient.setBirthdate(client.getBirthdate());
        oldClient.setName(client.getName());
        oldClient=clientRepository.save(oldClient);
        return  oldClient.getId();
    }
}
