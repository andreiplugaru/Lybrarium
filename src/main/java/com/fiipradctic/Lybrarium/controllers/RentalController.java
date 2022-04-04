package com.fiipradctic.Lybrarium.controllers;

import com.fiipradctic.Lybrarium.Models.Book;
import com.fiipradctic.Lybrarium.Models.Rental;
import com.fiipradctic.Lybrarium.services.ClientService;
import com.fiipradctic.Lybrarium.services.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class RentalController {
    @Autowired
    private RentalService rentalService;

    @PostMapping ("/rentItem")
    public ResponseEntity rentItem(@RequestBody Rental rental){
        rentalService.rent(rental.getItemId(), rental.getClientId());
        ResponseEntity r = ResponseEntity.ok().body("Item id: " +rental.getItemId() + " rented successsfuly!");
        return r;
    }
    @PostMapping ("/returnItem")
    public ResponseEntity returnItem(@RequestBody Rental rental){
        rentalService.returnItem(rental.getItemId(), rental.getClientId());
        ResponseEntity r = ResponseEntity.ok().body("Item id: " +rental.getItemId() + " has been returned successsfuly!");
        return r;
    }
}
