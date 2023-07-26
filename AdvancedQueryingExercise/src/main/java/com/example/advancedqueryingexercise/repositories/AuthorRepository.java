package com.example.advancedqueryingexercise.repositories;

import com.example.advancedqueryingexercise.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    @Query("SELECT a FROM Author AS a " +
            "INNER JOIN a.books AS b " +
            "WHERE YEAR(b.releaseDate) < :year")
    List<Author> findAuthorByBooksReleaseDateYearBefore(Integer year);

    @Query("SELECT a FROM Author AS a ORDER BY size(a.books) DESC")
    List<Author> getAuthorsByBookCountDesc();

    List<Author> findByFirstNameEndingWith(String ending);

    List<Author> findByLastNameStartingWith(String starting);

}
