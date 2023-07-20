package com.example.springdataintroexercise.repositories;

import com.example.springdataintroexercise.entities.Author;
import com.example.springdataintroexercise.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    @Query("SELECT b FROM Book AS b WHERE YEAR(b.releaseDate) > :year")
    List<Book> findByReleaseDateYearAfter(Integer year);

    @Query("SELECT b FROM Book AS b " +
            "WHERE b.author.firstName = :firstName AND b.author.lastName = :lastName " +
            "ORDER BY b.releaseDate DESC, b.title ASC")
    List<Book> findBooksFromAuthor(String firstName, String lastName);

}
