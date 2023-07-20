package com.example.springdataintroexercise.services;

import com.example.springdataintroexercise.entities.Author;
import org.springframework.stereotype.Service;
import com.example.springdataintroexercise.repositories.AuthorRepository;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class AuthorServiceImpl implements AuthorService {

    private AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public boolean isSeed() {
        if (authorRepository.count() > 0) {
            return false;
        }

        return true;
    }

    @Override
    public void saveSeed(List<Author> authors) {
        authorRepository.saveAll(authors);
    }

    @Override
    public Author getRandomAuthor() {
        Random random = new Random();
        long randomAuthorId = random.nextLong(1, authorRepository.count());
        Optional<Author> byId = authorRepository.findById(randomAuthorId);

        return byId.get();

    }

    @Override
    public List<Author> allAuthorsWithBooksBeforeYear(Integer year) {
        return authorRepository.findAuthorByBooksReleaseDateYearBefore(year);

    }

    @Override
    public List<Author> allAuthorsOrderedByBookCount() {
        return authorRepository.getAuthorsByBookCountDesc();
    }


}
