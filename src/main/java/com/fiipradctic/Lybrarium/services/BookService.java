package com.fiipradctic.Lybrarium.services;

import com.fiipradctic.Lybrarium.Exceptions.ApiRequestException;
import com.fiipradctic.Lybrarium.Models.Book;
import com.fiipradctic.Lybrarium.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookService {

    @Autowired
    BookRepository bookRepository;

    public Long addBook(Book book){
        if(book.getAuthor() == null || book.getName() == null)
            throw new ApiRequestException("Name or author is null!");
        try {
            Book newBook = bookRepository.save(book);
            return newBook.getId();
        }
        catch(Exception e) {
            throw new ApiRequestException("Exception when adding book!");
        }
    }
    public void updateBook(Book book) {
        if(book.getAuthor() == null || book.getName() == null)
            throw new ApiRequestException("Name or author is null!");

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
    public List<Book> getBooks(Optional<Long> id,
                               Optional<String> name,
                               Optional<String> author,
                               Optional<String> sort){
        List<Book> filteredBooks = bookRepository.findAll();
        if(id.isPresent())
            filteredBooks = filteredBooks.stream().filter(x -> x.getId() == id.get()).collect(Collectors.toList());
        if(name.isPresent())
            filteredBooks = filteredBooks.stream().filter(x -> x.getName().toLowerCase(Locale.ROOT).contains(name.get().toLowerCase(Locale.ROOT))).collect(Collectors.toList());
        if(author.isPresent())
            filteredBooks = filteredBooks.stream().filter(x -> x.getAuthor().toLowerCase(Locale.ROOT).contains(author.get().toLowerCase(Locale.ROOT))).collect(Collectors.toList());
        if(sort.isPresent())
            if(sort.get().equals("name")) {
                Comparator<Book> nameSorter =
                    Comparator.comparing(Book::getName, Comparator.nullsLast(Comparator.naturalOrder()));
                filteredBooks = filteredBooks.stream().sorted(nameSorter).collect(Collectors.toList());

            }
            else if(sort.get().equals("author")) {
                Comparator<Book> authorSorter =
                        Comparator.comparing(Book::getName, Comparator.nullsLast(Comparator.naturalOrder()));
                filteredBooks = filteredBooks.stream().sorted(authorSorter).collect(Collectors.toList());
            }
        return filteredBooks;
    }
}
