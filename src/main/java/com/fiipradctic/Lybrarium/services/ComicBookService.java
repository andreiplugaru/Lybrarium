package com.fiipradctic.Lybrarium.services;

import com.fiipradctic.Lybrarium.Exceptions.ApiRequestException;
import com.fiipradctic.Lybrarium.Models.ComicBook;
import com.fiipradctic.Lybrarium.repositories.ComicBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ComicBookService {

    @Autowired
    ComicBookRepository comicBookRepository;

    public Long addComicBook(ComicBook comicBook){
        if(comicBook.getSeries() == null)
            throw new ApiRequestException("Series is null!");

        try {
            ComicBook newComicBook = comicBookRepository.save(comicBook);
            return newComicBook.getId();
        }
        catch(Exception e) {
            throw new ApiRequestException("Exception when adding comicBook!");
        }
    }
    public void updateComicBook(ComicBook comicBook) {
        if(comicBook.getSeries() == null)
            throw new ApiRequestException("Series is null!");
        try {
            ComicBook comicBookFromDb = comicBookRepository.getById(comicBook.getId());
            comicBookFromDb.setSeries(comicBook.getSeries());
            comicBookFromDb.setNumber(comicBook.getNumber());
            comicBookRepository.save(comicBookFromDb);
        }
        catch(Exception e) {
            throw new ApiRequestException("Exception when updating comicBook!");
        }
    }
    public void removeComicBook(Long id){
        try {
            comicBookRepository.deleteById(id);
        }
        catch(Exception e){
            throw new ApiRequestException("This comicBook id doesn't exist!");
        }
    }
    public List<ComicBook> getComicBooks(Optional<Long> id,
                                            Optional<Long> number,
                                            Optional<Long> series,
                                            Optional<String> sort){
        List<ComicBook> filteredComicBooks = comicBookRepository.findAll();
        if(id.isPresent())
            filteredComicBooks = filteredComicBooks.stream().filter(x -> x.getId() == id.get()).collect(Collectors.toList());
        if(number.isPresent())
            filteredComicBooks = filteredComicBooks.stream().filter(x -> x.getNumber() == number.get()).collect(Collectors.toList());
        if(series.isPresent())
            filteredComicBooks = filteredComicBooks.stream().filter(x -> x.getNumber() == series.get()).collect(Collectors.toList());
        if(sort.isPresent())
            if(sort.get().equals("series")) {
                Comparator<ComicBook> sorter = Comparator.comparing(ComicBook::getSeries, Comparator.nullsLast(Comparator.naturalOrder()));
                sorter = sorter.thenComparing(comicBook -> comicBook.getNumber(), Comparator.reverseOrder());
                filteredComicBooks = filteredComicBooks.stream().sorted(sorter).collect(Collectors.toList());
            }
        return filteredComicBooks;
    }
}
