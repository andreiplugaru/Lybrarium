package com.fiipradctic.Lybrarium.services;

import com.fiipradctic.Lybrarium.Exceptions.ApiRequestException;
import com.fiipradctic.Lybrarium.Models.Book;
import com.fiipradctic.Lybrarium.Models.Rental;
import com.fiipradctic.Lybrarium.repositories.BookRepository;
import com.fiipradctic.Lybrarium.repositories.ClientRepository;
import com.fiipradctic.Lybrarium.repositories.ComicBookRepository;
import com.fiipradctic.Lybrarium.repositories.RentalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
@Service
public class RentalService {
    @Autowired
    RentalRepository rentalRepository;

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    ComicBookRepository comicBookRepository;
    public Long rent(Long itemId, Long clientId){
        if(!bookRepository.existsById(itemId) && !comicBookRepository.existsById(itemId))
            throw new ApiRequestException("Item doesn't exist!");

        if(isItemRented(itemId))
            throw new ApiRequestException("Item is already rented!");

        if(!clientRepository.existsById(clientId))
            throw new ApiRequestException("User doesn't exist!");
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

    public void returnItem(Long itemId, Long clientId) {
        if(!bookRepository.existsById(itemId) && !comicBookRepository.existsById(itemId))
            throw new ApiRequestException("Item doesn't exist!");

        Rental currentRent = rentalRepository.findByClientIdAndItemId(clientId, itemId);
        if(currentRent == null || !(currentRent.getReturnedDate() == null))
            throw new ApiRequestException("This user hasn't rented this item!");

        try {
            currentRent.setReturnedDate(new Date());
            Rental newRental = rentalRepository.save(currentRent);
        }
        catch(Exception e) {
            throw new ApiRequestException("Exception when renting item!");
        }
    }
    private boolean isItemRented(Long itemId){
       return rentalRepository.findByItemId(itemId).stream().
               anyMatch(r -> r.getReturnedDate() == null);
    }
}
