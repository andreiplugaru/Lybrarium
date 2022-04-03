package com.fiipradctic.Lybrarium.services;

import com.fiipradctic.Lybrarium.Exceptions.ApiRequestException;
import com.fiipradctic.Lybrarium.Models.Book;
import com.fiipradctic.Lybrarium.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    BookRepository bookRepository;

    public Long addBook(Book book){
        try {
            Book newBook = bookRepository.save(book);
            return newBook.getId();
        }
        catch(Exception e) {
            throw new ApiRequestException("Exception when adding book!");
        }
    }
    public void updateBook(Book book) {
        try {
            Book bookFromDb = bookRepository.getById(book.getId());
            bookFromDb.setAuthor(book.getAuthor());
            bookFromDb.setName(book.getName());
            bookRepository.save(bookFromDb);
        }
        catch(Exception e) {
            throw new ApiRequestException("Exception when updating book!");
        }
    }
    public void removeBook(Long id){
        try {
            bookRepository.deleteById(id);
        }
        catch(Exception e){
            throw new ApiRequestException("This book id doesn't exist!");
        }
    }
    public List<Book> getAllBooks(){
       return bookRepository.findAll();
    }
}
