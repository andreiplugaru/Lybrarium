package com.fiipradctic.Lybrarium.services;

import com.fiipradctic.Lybrarium.Exceptions.ApiRequestException;
import com.fiipradctic.Lybrarium.Models.Book;
import com.fiipradctic.Lybrarium.Models.Rental;
import com.fiipradctic.Lybrarium.repositories.RentalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service
public class RentalService {
    @Autowired
    RentalRepository rentalRepository;

    public Long rent(Long itemId, Long clientId){
        if(rentalRepository.existsByItemId(itemId) && isItemRented(itemId)) {
        //    System.out.println("Test");
            throw new ApiRequestException("Book is already rented or it doesn't exist!");
        }
        try {
            Rental r = new Rental();
            r.setClientId(clientId);
            r.setItemId(itemId);
            r.setRentedDate(new Date());
            Rental newRental = rentalRepository.save(r);
            return newRental.getId();
        }
        catch(Exception e) {
            throw new ApiRequestException("Exception when renting book!");
        }
    }
    private boolean isItemRented(Long itemId){
       return rentalRepository.findByItemId(itemId).stream().
               anyMatch(r -> r.getReturnedDate() == null);
    }
}
