package com.fiipradctic.Lybrarium.controllers;

import com.fiipradctic.Lybrarium.Models.Book;
import com.fiipradctic.Lybrarium.Models.Client;
import com.fiipradctic.Lybrarium.repositories.ClientRepository;
import com.fiipradctic.Lybrarium.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class ClientController {
    @Autowired
    private ClientService clientService;
    @GetMapping("/getClients")
    public List<Client> getClients(@RequestParam("id") Optional<Long> id,
                                   @RequestParam("name") Optional<String> name,
                                   @RequestParam("sort") Optional<String> sort){

        return clientService.getClients(id, name, sort);
    }
    @DeleteMapping("/removeClient/{id}")
    public ResponseEntity removeClient(@PathVariable Long id){
        clientService.removeClient(id);
        ResponseEntity r = ResponseEntity.ok().body("Client id: " + id + " was removed successsfuly!");
        return r;
    }
    @PostMapping("/addClient")
    public ResponseEntity addClient(@RequestBody Client client){
        Long newId =  clientService.addClient(client);
        ResponseEntity r = ResponseEntity.ok().body("Client id: " + newId + " created successsfuly!");
        return r;
    }
    @PutMapping ("/updateClient")
    public ResponseEntity updateClient(@RequestBody Client client){
        clientService.updateClient(client);
        ResponseEntity r = ResponseEntity.ok().body("Client id: " + client.getId() + " updated successsfuly!");
        return r;
    }
   @GetMapping("/getBadClients")
    public List<Client> getClients(){

        return clientService.getBadClients();
    }
}
