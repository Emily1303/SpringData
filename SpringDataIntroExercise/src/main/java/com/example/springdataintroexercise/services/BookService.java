package com.example.springdataintroexercise.services;

import com.example.springdataintroexercise.entities.Author;
import com.example.springdataintroexercise.entities.Book;

import java.util.List;

public interface BookService {

    boolean isSeed();

    void saveSeed(List<Book> books);

    List<Book> allBooksAfterYear(Integer year);

    List<Book> getAllBooksFromAuthor(String firstName, String lastName);

}
