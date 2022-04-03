package com.fiipradctic.Lybrarium.services;

import com.fiipradctic.Lybrarium.Exceptions.ApiRequestException;
import com.fiipradctic.Lybrarium.Models.Client;
import com.fiipradctic.Lybrarium.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {
    @Autowired
    ClientRepository clientRepository;
    public Long addClient(Client client){
        try {
            Client newClient = clientRepository.save(client);
            return newClient.getId();
        }
        catch(Exception e) {
            throw new ApiRequestException("Exception when adding client!");
        }
    }
    public void updateClient(Client client) {
        try {
            Client clientFromDb = clientRepository.getById(client.getId());
            clientFromDb.setName(client.getName());
            clientRepository.save(clientFromDb);
        }
        catch(Exception e) {
            throw new ApiRequestException("Exception when updating client!");
        }
    }
    public void removeClient(Long id){
        try {
            clientRepository.deleteById(id);
        }
        catch(Exception e){
            throw new ApiRequestException("This client id doesn't exist!");
        }
    }
    public List<Client> getAllClients(){
        return clientRepository.findAll();
    }
}
