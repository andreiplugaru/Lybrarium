package com.fiipradctic.Lybrarium.repositories;

import com.fiipradctic.Lybrarium.Models.Book;
import com.fiipradctic.Lybrarium.Models.Rental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RentalRepository extends JpaRepository<Rental, Long> {
    List<Rental> findByItemId(Long itemId);
    boolean existsByItemId(Long itemId);
    Rental findByClientIdAndItemId(Long clientId, Long itemId);
}
