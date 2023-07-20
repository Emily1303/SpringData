package com.example.springdataintroexercise;

import com.example.springdataintroexercise.entities.Author;
import com.example.springdataintroexercise.entities.Book;
import com.example.springdataintroexercise.services.AuthorService;
import com.example.springdataintroexercise.services.BookService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import com.example.springdataintroexercise.services.SeedService;

import java.util.List;

@Component
public class ConsoleRunner implements CommandLineRunner {

    private SeedService seedService;
    private BookService bookService;
    private AuthorService authorService;

    public ConsoleRunner(SeedService seedService, BookService bookService, AuthorService authorService) {
        this.seedService = seedService;
        this.bookService = bookService;
        this.authorService = authorService;
    }

    @Override
    public void run(String... args) throws Exception {
        seedService.seedDatabase();

//        First query
        List<Book> booksAfterYear = bookService.allBooksAfterYear(2000);
        booksAfterYear.stream().map(Book::getTitle).forEach(System.out::println);

//        Second query
        List<Author> authorsWithBooksBeforeYear = authorService.allAuthorsWithBooksBeforeYear(1990);
        for (Author author : authorsWithBooksBeforeYear) {
            System.out.println(author.getFirstName() + " " + author.getLastName());
        }

//        Third Query
        List<Author> allAuthorsOrderedByBookCount = authorService.allAuthorsOrderedByBookCount();
        for (Author author : allAuthorsOrderedByBookCount) {
            System.out.println(author.getFirstName() + " " + author.getLastName() +
                    " " + author.getBooks().size());
        }

//        Fourth Query
        List<Book> bookList = bookService.getAllBooksFromAuthor("George", "Powell");
        for (Book book : bookList) {
            System.out.println(book.getTitle() + " " + book.getReleaseDate() +
                    " " + book.getCopies());
        }

    }
}
