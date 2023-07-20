package com.example.springdataintroexercise.repositories;

import com.example.springdataintroexercise.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    @Query("SELECT a FROM Author AS a " +
            "INNER JOIN a.books AS b " +
            "WHERE YEAR(b.releaseDate) < :year")
    List<Author> findAuthorByBooksReleaseDateYearBefore(Integer year);

    @Query("SELECT a FROM Author AS a ORDER BY size(a.books) DESC")
    List<Author> getAuthorsByBookCountDesc();

}
