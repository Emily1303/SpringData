package com.example.advancedqueryingexercise.services;

import com.example.advancedqueryingexercise.entities.Book;
import com.example.advancedqueryingexercise.enums.AgeRestriction;
import com.example.advancedqueryingexercise.enums.EditionType;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface BookService {

    boolean isSeed();

    void saveSeed(List<Book> books);

    List<Book> allBooksAfterYear(Integer year);

    List<Book> getAllBooksFromAuthor(String firstName, String lastName);

    List<Book> getAllBooksByAgeRestriction(AgeRestriction ageRestriction);

    List<Book> getAllBooksByEditionTypeAndCopies(EditionType editionType, int copies);

    List<Book> getAllBooksWithPriceNotBetweenNumbers(BigDecimal lower, BigDecimal higher);

    List<Book> getAllBooksWithYearNotLikeGiven(Integer year);

    List<Book> getAllBooksWithReleaseDateBeforeGiven(String date);

    List<Book> getAllBooksTitlesContaining(String containing);

    List<Book> getAllBooksTitlesLengthGreaterThan(Integer number);

    Book getBookByGivenTitle(String title);

}
