package com.fiipradctic.Lybrarium.services;

import com.fiipradctic.Lybrarium.Models.Book;
import com.fiipradctic.Lybrarium.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    @Autowired
    BookRepository bookRepository;

    public String getName(Integer id){
        return bookRepository.findById(Long.valueOf(id)).toString();
    }
    public void addBook(Book book){
bookRepository.save(book);
    }


}
