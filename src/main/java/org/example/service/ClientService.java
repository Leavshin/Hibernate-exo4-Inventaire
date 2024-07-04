package org.example.service;

import org.example.entity.Client;
import org.example.exception.NotFoundException;
import org.example.repository.ClientRepository;

import java.util.List;
import java.util.Optional;

public class ClientService {

    private final ClientRepository clientRepository;

    public ClientService() {
        this.clientRepository = new ClientRepository();
    }

    public Client addClient(Client client) {
        return clientRepository.save(client);
    }

    public Client updateClient(Client client) {
        Optional<Client> existingClient = clientRepository.findById(client.getId());
        if (existingClient.isPresent()) {
            return clientRepository.update(client);
        } else {
            throw new NotFoundException("Client non trouvé avec l'ID : " + client.getId());
        }
    }

    public boolean deleteClient(Long id) {
        return clientRepository.deleteById(id);
    }

    public Client findById(Long id) {
        return clientRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Client non trouvé avec l'ID : " + id));
    }

    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }
}
