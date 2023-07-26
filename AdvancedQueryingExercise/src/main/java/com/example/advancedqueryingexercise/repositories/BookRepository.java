package com.example.advancedqueryingexercise.repositories;

import com.example.advancedqueryingexercise.entities.Book;
import com.example.advancedqueryingexercise.enums.AgeRestriction;
import com.example.advancedqueryingexercise.enums.EditionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    @Query("SELECT b FROM Book AS b WHERE YEAR(b.releaseDate) > :year")
    List<Book> findByReleaseDateYearAfter(Integer year);

    @Query("SELECT b FROM Book AS b " +
            "WHERE b.author.firstName = :firstName AND b.author.lastName = :lastName " +
            "ORDER BY b.releaseDate DESC, b.title ASC")
    List<Book> findBooksFromAuthor(String firstName, String lastName);

    List<Book> findByAgeRestriction(AgeRestriction ageRestriction);

    List<Book> findByEditionTypeAndCopiesLessThan(EditionType editionType, int copies);

    @Query("SELECT b FROM Book AS b WHERE b.price NOT BETWEEN :lowerNumber AND :higherNumber")
    List<Book> findByPriceNotBetween(BigDecimal lowerNumber, BigDecimal higherNumber);

    @Query("SELECT b FROM Book AS b WHERE YEAR(b.releaseDate) != :year")
    List<Book> findByReleaseDate_YearNot(Integer year);

    List<Book> findByReleaseDateBefore(LocalDate date);

    List<Book> findByTitleContaining(String containing);

    @Query("SELECT b FROM Book AS b WHERE length(b.title) > :number")
    List<Book> findByTitleSizeGreaterThan(Integer number);

    Book findByTitle(String title);

}
