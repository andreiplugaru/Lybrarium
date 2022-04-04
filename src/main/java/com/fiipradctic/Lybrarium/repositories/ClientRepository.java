package com.fiipradctic.Lybrarium.repositories;

import com.fiipradctic.Lybrarium.Models.Client;
import com.fiipradctic.Lybrarium.Models.Rental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    //List<Rental> fi(Long itemId);
}
