package com.example.advancedqueryingexercise;

import com.example.advancedqueryingexercise.entities.Author;
import com.example.advancedqueryingexercise.entities.Book;
import com.example.advancedqueryingexercise.enums.AgeRestriction;
import com.example.advancedqueryingexercise.enums.EditionType;
import com.example.advancedqueryingexercise.services.AuthorService;
import com.example.advancedqueryingexercise.services.BookService;
import com.example.advancedqueryingexercise.services.SeedService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

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
//        List<Book> booksAfterYear = bookService.allBooksAfterYear(2000);
//        booksAfterYear.stream().map(Book::getTitle).forEach(System.out::println);

//        Second query
//        List<Author> authorsWithBooksBeforeYear = authorService.allAuthorsWithBooksBeforeYear(1990);
//        for (Author author : authorsWithBooksBeforeYear) {
//            System.out.println(author.getFirstName() + " " + author.getLastName());
//        }

//        Third Query
//        List<Author> allAuthorsOrderedByBookCount = authorService.allAuthorsOrderedByBookCount();
//        for (Author author : allAuthorsOrderedByBookCount) {
//            System.out.println(author.getFirstName() + " " + author.getLastName() +
//                    " " + author.getBooks().size());
//        }

//        Fourth Query
//        List<Book> bookList = bookService.getAllBooksFromAuthor("George", "Powell");
//        for (Book book : bookList) {
//            System.out.println(book.getTitle() + " " + book.getReleaseDate() +
//                    " " + book.getCopies());
//        }

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter task number from 1 to 14: ");

        int taskNumber = Integer.parseInt(scanner.nextLine());

        switch (taskNumber) {
            case 1:
                System.out.print("Enter age restriction name: ");
                String ageRestrictionName = scanner.nextLine();
                printBooksByRestrictionName(ageRestrictionName);
                break;
            case 2:
                System.out.print("Enter edition type: ");
                String editionType = scanner.nextLine();
                System.out.print("Enter number of copies: ");
                int copies = Integer.parseInt(scanner.nextLine());
                printBooksByEditionTypeAndCopies(editionType, copies);
                break;
            case 3:
                System.out.print("Enter lower price: ");
                int lower = Integer.parseInt(scanner.nextLine());
                System.out.print("Enter higher price: ");
                int higher = Integer.parseInt(scanner.nextLine());
                printAllBooksWithPriceNotBetweenNumbers(lower, higher);
                break;
            case 4:
                System.out.print("Enter year: ");
                Integer year = Integer.parseInt(scanner.nextLine());
                printBooksThatAreNotReleasedInThatYear(year);
                break;
            case 5:
                System.out.print("Enter date: ");
                String dateGiven = scanner.nextLine();
                getAllBooksWithReleaseDateBeforeGiven(dateGiven);
                break;
            case 6:
                System.out.print("Enter ending: ");
                String ending = scanner.nextLine();
                printAllAuthorsWithFirstNameEndingWith(ending);
                break;
            case 7:
                System.out.print("Enter containing: ");
                String containing = scanner.nextLine();
                printAllBooksTitlesThatContain(containing);
                break;
            case 8:
                System.out.print("Enter starting word: ");
                String starting = scanner.nextLine();
                getAuthorsWithLastName(starting);
                break;
            case 9:
                System.out.print("Enter number: ");
                int number = Integer.parseInt(scanner.nextLine());
                getBooksWithTitleLengthLongerThan(number);
                break;
            case 10:
                getAllCopiesForEachAuthor();
                break;
            case 11:
                System.out.print("Enter book title: ");
                String title = scanner.nextLine();
                printBookByTitle(title);
                break;
        }

    }

    private void printBookByTitle(String title) {
        Book book = bookService.getBookByGivenTitle(title);

        System.out.printf("%s %s %s %s\n",
                book.getTitle(), book.getEditionType(), book.getAgeRestriction(), book.getPrice());
    }

    private void getAllCopiesForEachAuthor() {

        List<Author> authors = authorService.allAuthors();
        for (Author author : authors) {
            Integer allCopies = 0;

            Set<Book> books = author.getBooks();
            for (Book book : books) {
                allCopies = allCopies + book.getCopies();
            }

            System.out.printf("%s %s - %d\n",
                    author.getFirstName(), author.getLastName(), allCopies);
        }
    }

    private void getBooksWithTitleLengthLongerThan(Integer number) {
        List<Book> books = bookService.getAllBooksTitlesLengthGreaterThan(number);

        System.out.println(books.size());
    }

    private void getAuthorsWithLastName(String starting) {
        List<Author> authors = authorService.allAuthorsWithLastNameStarting(starting);
        for (Author author : authors) {
            Set<Book> allBooksByAuthor = author.getBooks();
            allBooksByAuthor
                    .forEach(book -> System.out.printf("%s (%s %s)\n",
                            book.getTitle(), author.getFirstName(), author.getLastName()));
        }
    }

    private void printAllBooksTitlesThatContain(String containing) {
         bookService.getAllBooksTitlesContaining(containing)
                .forEach(book -> System.out.println(book.getTitle()));
    }

    private void printAllAuthorsWithFirstNameEndingWith(String ending) {
         authorService.allAuthorsWithFirstNameEnding(ending)
                .forEach(author ->
                        System.out.println(author.getFirstName() + " " + author.getLastName()));
    }

    private void getAllBooksWithReleaseDateBeforeGiven(String date) {
         bookService.getAllBooksWithReleaseDateBeforeGiven(date)
                .forEach(book -> System.out.printf("%s %s %s\n",
                        book.getTitle(), book.getEditionType(), book.getPrice()));
    }

    private void printBooksThatAreNotReleasedInThatYear(Integer year) {
        bookService.getAllBooksWithYearNotLikeGiven(year)
                .forEach(book -> System.out.println(book.getTitle()));
    }

    private void printAllBooksWithPriceNotBetweenNumbers(int lower, int higher) {
        bookService
                .getAllBooksWithPriceNotBetweenNumbers(BigDecimal.valueOf(lower), BigDecimal.valueOf(higher))
                .forEach(book -> System.out.printf("%s - $%s\n", book.getTitle(), book.getPrice()));
    }

    private void printBooksByRestrictionName(String ageRestrictionName) {
        bookService.
                getAllBooksByAgeRestriction(AgeRestriction.valueOf(ageRestrictionName.toUpperCase()))
                .forEach(book -> System.out.println(book.getTitle()));
    }

    private void printBooksByEditionTypeAndCopies(String editionType, int copies) {
        bookService.
                getAllBooksByEditionTypeAndCopies(EditionType.valueOf(editionType.toUpperCase()), copies)
                .forEach(book -> System.out.println(book.getTitle()));
    }

}
