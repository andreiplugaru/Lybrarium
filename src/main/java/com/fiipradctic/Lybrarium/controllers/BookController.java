package com.fiipradctic.Lybrarium.controllers;

import com.fiipradctic.Lybrarium.Models.Book;
import com.fiipradctic.Lybrarium.repositories.BookRepository;
import com.fiipradctic.Lybrarium.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class BookController {
BookRepository t;
    @Autowired
    private BookService bookService;

    @GetMapping("/getBook/{id}")
    public String getName(@PathVariable Integer id){

        return bookService.getName(id);
    }
    @PostMapping("/addBook")
    public void addBook(@RequestBody Book book){
         bookService.addBook(book);
    }
}
