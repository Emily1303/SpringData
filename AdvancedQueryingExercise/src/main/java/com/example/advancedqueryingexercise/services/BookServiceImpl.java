package com.example.advancedqueryingexercise.services;

import com.example.advancedqueryingexercise.entities.Book;
import com.example.advancedqueryingexercise.enums.AgeRestriction;
import com.example.advancedqueryingexercise.enums.EditionType;
import com.example.advancedqueryingexercise.repositories.BookRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

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

    @Override
    public List<Book> getAllBooksByAgeRestriction(AgeRestriction ageRestriction) {
        return bookRepository.findByAgeRestriction(ageRestriction);
    }

    @Override
    public List<Book> getAllBooksByEditionTypeAndCopies(EditionType editionType, int copies) {
        return bookRepository.findByEditionTypeAndCopiesLessThan(editionType, copies);
    }

    @Override
    public List<Book> getAllBooksWithPriceNotBetweenNumbers(BigDecimal lower, BigDecimal higher) {
        return bookRepository.findByPriceNotBetween(lower, higher);
    }

    @Override
    public List<Book> getAllBooksWithYearNotLikeGiven(Integer year) {
        return bookRepository.findByReleaseDate_YearNot(year);
    }

    @Override
    public List<Book> getAllBooksWithReleaseDateBeforeGiven(String date) {
        int day = Integer.parseInt(date.split("-")[0]);
        int month = Integer.parseInt(date.split("-")[1]);
        int year = Integer.parseInt(date.split("-")[2]);

        LocalDate wantedDate = LocalDate.of(year, month, day);
        return bookRepository.findByReleaseDateBefore(wantedDate);
    }

    @Override
    public List<Book> getAllBooksTitlesContaining(String containing) {
        return bookRepository.findByTitleContaining(containing);
    }

    @Override
    public List<Book> getAllBooksTitlesLengthGreaterThan(Integer number) {
        return bookRepository.findByTitleSizeGreaterThan(number);
    }

    @Override
    public Book getBookByGivenTitle(String title) {
        return bookRepository.findByTitle(title);
    }


}
