package com.fiipradctic.Lybrarium.controllers;

import com.fiipradctic.Lybrarium.Models.Book;
import com.fiipradctic.Lybrarium.repositories.BookRepository;
import com.fiipradctic.Lybrarium.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
public class BookController {
    BookRepository t;
    @Autowired
    private BookService bookService;

    @GetMapping("/getBooks")
    public List<Book> getBooks(@RequestParam("id") Optional<Long> id,
                               @RequestParam("name") Optional<String> name,
                               @RequestParam("author") Optional<String> author,
                               @RequestParam("sort") Optional<String> sort){

        return bookService.getBooks(id,name,author,sort);
    }
    @DeleteMapping("/removeBook/{id}")
    public ResponseEntity removeBook(@PathVariable Long id){
        bookService.removeBook(id);
        ResponseEntity r = ResponseEntity.ok().body("Book id: " + id + " was removed successsfuly!");
        return r;
    }
    @PostMapping("/addBook")
    public ResponseEntity addBook(@RequestBody Book book){
        Long newId =  bookService.addBook(book);
        ResponseEntity r = ResponseEntity.ok().body("Book id: " + newId + " created successsfuly!");
        return r;
    }
    @PutMapping ("/updateBook")
    public ResponseEntity updateBook(@RequestBody Book book){
        bookService.updateBook(book);
        ResponseEntity r = ResponseEntity.ok().body("Book id: " + book.getId() + " updated successsfuly!");
        return r;
    }

}
