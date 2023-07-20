package com.example.springdataintroexercise.services;

import com.example.springdataintroexercise.entities.Author;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface AuthorService{

    boolean isSeed();

    void saveSeed(List<Author> authors);

    Author getRandomAuthor();

    List<Author> allAuthorsWithBooksBeforeYear(Integer year);

    List<Author> allAuthorsOrderedByBookCount();

}
