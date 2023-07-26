package com.example.advancedqueryingexercise.services;

import com.example.advancedqueryingexercise.entities.Author;
import com.example.advancedqueryingexercise.entities.Book;
import com.example.advancedqueryingexercise.entities.Category;
import com.example.advancedqueryingexercise.enums.AgeRestriction;
import com.example.advancedqueryingexercise.enums.EditionType;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SeedServiceImpl implements SeedService {

    private static final String COMMON_PATH = "src\\main\\resources\\files\\";
    private static final String PATH_AUTHORS = "authors.txt";
    private static final String PATH_CATEGORIES = "categories.txt";
    private static final String PATH_BOOKS = "books.txt";


    private AuthorService authorService;

    private CategoryService categoryService;

    private BookService bookService;

    public SeedServiceImpl(AuthorService authorService, CategoryService categoryService, BookService bookService) {
        this.authorService = authorService;
        this.categoryService = categoryService;
        this.bookService = bookService;
    }

    @Override
    public void seedDatabase() throws IOException {
        if (authorService.isSeed()) {
            List<String> authorsValues = Files.readAllLines(Path.of(COMMON_PATH + PATH_AUTHORS));

            List<Author> authors = new ArrayList<>();

            for (String authorsValue : authorsValues) {
                String firstName = authorsValue.split(" ")[0];
                String lastName = authorsValue.split(" ")[1];

                Author author = new Author(firstName, lastName);
                authors.add(author);

            }

            authorService.saveSeed(authors);
        }

        if (categoryService.isSeed()) {
            List<String> categoriesValues =
                    Files.readAllLines(Path.of(COMMON_PATH + PATH_CATEGORIES))
                            .stream().
                            filter(category -> !category.isBlank()).
                            toList();

            List<Category> categories = new ArrayList<>();

            for (String categoriesValue : categoriesValues) {
                Category category = new Category(categoriesValue);

                categories.add(category);
            }

            categoryService.saveSeed(categories);

        }

        if (bookService.isSeed()) {

            List<String> booksValues = Files.readAllLines(Path.of(COMMON_PATH + PATH_BOOKS));

            List<Book> books = new ArrayList<>();

            for (String booksValue : booksValues) {

                Author author = authorService.getRandomAuthor();

                Category category = categoryService.getRandomCategory();

                String[] bookInfo = booksValue.split(" ");
                int editionTypeIndex = Integer.parseInt(bookInfo[0]);
                EditionType editionType = EditionType.values()[editionTypeIndex];
                LocalDate date = LocalDate.parse(bookInfo[1], DateTimeFormatter.ofPattern("d/M/yyyy"));
                int copies = Integer.parseInt(bookInfo[2]);
                BigDecimal price = new BigDecimal(bookInfo[3]);

                int ageRestrictionIndex = Integer.parseInt(bookInfo[4]);
                AgeRestriction ageRestriction = AgeRestriction.values()[ageRestrictionIndex];

                String title = Arrays.stream(bookInfo).skip(5)
                        .collect(Collectors.joining(" "));

                Book book = new Book(title, editionType, price,
                        copies, date, ageRestriction, author, category);

                books.add(book);
            }

            bookService.saveSeed(books);

        }
    }
}
