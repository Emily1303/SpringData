package com.example.advancedqueryingexercise.services;

import com.example.advancedqueryingexercise.entities.Author;

import java.util.List;

public interface AuthorService{

    boolean isSeed();

    void saveSeed(List<Author> authors);

    Author getRandomAuthor();

    List<Author> allAuthorsWithBooksBeforeYear(Integer year);

    List<Author> allAuthorsOrderedByBookCount();

    List<Author> allAuthorsWithFirstNameEnding(String ending);

    List<Author> allAuthorsWithLastNameStarting(String ending);

    List<Author> allAuthors();

}
