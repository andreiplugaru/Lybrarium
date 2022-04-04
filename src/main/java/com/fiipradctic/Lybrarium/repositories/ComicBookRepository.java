package com.fiipradctic.Lybrarium.repositories;

import com.fiipradctic.Lybrarium.Models.ComicBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComicBookRepository extends JpaRepository<ComicBook, Long> {

}
