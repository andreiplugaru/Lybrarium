package com.fiipradctic.Lybrarium.controllers;

import com.fiipradctic.Lybrarium.Models.Book;
import com.fiipradctic.Lybrarium.services.ClientService;
import com.fiipradctic.Lybrarium.services.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class RentalController {
    @Autowired
    private RentalService rentalService;
    @PostMapping ("/rent/{clientId}/{itemId}")
    public ResponseEntity rent(@PathVariable long itemId, @PathVariable long clientId){
        rentalService.rent(itemId, clientId);
        ResponseEntity r = ResponseEntity.ok().body("Item id: " +itemId + " rented successsfuly!");
        return r;
    }
}
