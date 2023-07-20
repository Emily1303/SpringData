package com.example.springdataintroexercise.services;

import com.example.springdataintroexercise.entities.Author;
import com.example.springdataintroexercise.entities.Book;
import org.springframework.stereotype.Service;
import com.example.springdataintroexercise.repositories.BookRepository;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public boolean isSeed() {
        if (bookRepository.count() > 0) {
            return false;
        }

        return true;
    }

    @Override
    public void saveSeed(List<Book> books) {
        bookRepository.saveAll(books);
    }

    @Override
    public List<Book> allBooksAfterYear(Integer year) {
        return bookRepository.findByReleaseDateYearAfter(year);
    }

    @Override
    public List<Book> getAllBooksFromAuthor(String firstName, String lastName) {
        return bookRepository.findBooksFromAuthor(firstName, lastName);
    }


}
