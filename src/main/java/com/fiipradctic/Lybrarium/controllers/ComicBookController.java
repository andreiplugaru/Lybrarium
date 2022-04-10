package com.fiipradctic.Lybrarium.controllers;

import com.fiipradctic.Lybrarium.Models.ComicBook;
import com.fiipradctic.Lybrarium.repositories.ComicBookRepository;
import com.fiipradctic.Lybrarium.services.ComicBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ComicBookController {
    ComicBookRepository t;
    @Autowired
    private ComicBookService comicBookService;
    @GetMapping("/getComicBooks")
    public List<ComicBook> getComicBooks(@RequestParam("id") Optional<Long> id,
                                         @RequestParam("number") Optional<Long> number,
                                         @RequestParam("series") Optional<Long> series,
                                         @RequestParam("sort") Optional<String> sort){

        return comicBookService.getComicBooks(id, number, series, sort);
    }
    @DeleteMapping("/removeComicBook/{id}")
    public ResponseEntity removeComicBook(@PathVariable Long id){
        comicBookService.removeComicBook(id);
        ResponseEntity r = ResponseEntity.ok().body("ComicBook id: " + id + " was removed successsfuly!");
        return r;
    }
    @PostMapping("/addComicBook")
    public ResponseEntity addComicBook(@RequestBody ComicBook comicBook){
        Long newId =  comicBookService.addComicBook(comicBook);
        ResponseEntity r = ResponseEntity.ok().body("ComicBook id: " + newId + " created successsfuly!");
        return r;
    }
    @PutMapping ("/updateComicBook")
    public ResponseEntity updateComicBook(@RequestBody ComicBook comicBook){
        comicBookService.updateComicBook(comicBook);
        ResponseEntity r = ResponseEntity.ok().body("ComicBook id: " + comicBook.getId() + " updated successsfuly!");
        return r;
    }
}
