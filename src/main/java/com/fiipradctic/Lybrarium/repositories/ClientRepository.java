package com.fiipradctic.Lybrarium.repositories;

import com.fiipradctic.Lybrarium.Models.Client;
import com.fiipradctic.Lybrarium.Models.Rental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
}
