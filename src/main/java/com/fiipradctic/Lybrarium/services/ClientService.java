package com.fiipradctic.Lybrarium.services;

import com.fiipradctic.Lybrarium.Exceptions.ApiRequestException;
import com.fiipradctic.Lybrarium.Models.Book;
import com.fiipradctic.Lybrarium.Models.Client;
import com.fiipradctic.Lybrarium.repositories.ClientRepository;
import com.fiipradctic.Lybrarium.repositories.RentalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
public class ClientService {
    @Autowired
    ClientRepository clientRepository;

    @Autowired
    RentalRepository rentalRepository;
    public Long addClient(Client client){
        if(client.getName() == null)
            throw new ApiRequestException("Name is null!");
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
    public List<Client> getClients(Optional<Long> id,
                                   Optional<String> name,
                                   Optional<String> sort){
        List<Client> filteredClients = clientRepository.findAll();
        if(id.isPresent())
            filteredClients = filteredClients.stream().filter(x -> x.getId() == id.get()).collect(Collectors.toList());
        if(name.isPresent())
            filteredClients = filteredClients.stream().filter(x -> x.getName().toLowerCase(Locale.ROOT).contains(name.get().toLowerCase(Locale.ROOT))).collect(Collectors.toList());
        if(sort.isPresent())
            if(sort.get().equals("name")) {
                Comparator<Client> nameSorter =
                    Comparator.comparing(Client::getName, Comparator.nullsLast(Comparator.naturalOrder()));
                filteredClients = filteredClients.stream().sorted(nameSorter).collect(Collectors.toList());
            }
        return filteredClients;
    }

    public List<Client> getBadClients() {
        List<Long> badCLientsId = rentalRepository.findAll().stream().filter(x -> {
            if(x.getReturnedDate() != null)
               return TimeUnit.DAYS.convert(Math.abs(x.getReturnedDate().getTime()-x.getRentedDate().getTime()),TimeUnit.MILLISECONDS) >= 14;
        else
            return TimeUnit.DAYS.convert(Math.abs(new Date().getTime()-x.getRentedDate().getTime()),TimeUnit.MILLISECONDS) >= 14;

        }).map(c -> c.getClientId()).collect(Collectors.toList());
        List<Client> badCLients = clientRepository.findAll().stream().filter(x-> badCLientsId.contains(x.getId())).collect(Collectors.toList());
        return badCLients;
    }
}
