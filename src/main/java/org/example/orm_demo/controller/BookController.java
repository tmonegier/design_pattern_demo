package org.example.orm_demo.controller;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.orm_demo.authentication.UserService;
import org.example.orm_demo.authentication.model.User;
import org.example.orm_demo.input.NewBookDto;
import org.example.orm_demo.jpa.repository.BookRepository;
import org.example.orm_demo.output.BookDto;
import org.example.orm_demo.resolvers.CurrentUser;
import org.example.orm_demo.service.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/books")
@Slf4j
public class BookController {

    private final BookRepository bookRepository;
    private final IBookService bookService;
    private final UserService userService;
    private final ILoggingService loggingService;

    public BookController(BookRepository bookRepository, IBookService bookService, UserService userService) {
        this.bookRepository = bookRepository;
        this.bookService = bookService;
        this.userService = userService;
        this.loggingService = LoggingServiceSingleton.getInstance();
    }

    @PostMapping
    public BookDto createNewBook(@RequestBody NewBookDto newBook) {
        loggingService.log("Create new book %s".formatted(newBook.title()));
        var bookEntity =  bookService.createNewBook(newBook);
        return new BookDto(bookEntity.getId(), bookEntity.getTitle(), bookEntity.getIsbn(), bookEntity.getAuthor().getName());
    }

    @GetMapping
    public List<BookDto> findAllBooks() {
        loggingService.log("Retrieve all books");
        return bookRepository.findAll().stream().map(
                bookEntity -> new BookDto(
                        bookEntity.getId(), bookEntity.getTitle(), bookEntity.getIsbn(), bookEntity.getAuthor().getName()
                )
        ).toList();
    }

    @GetMapping("/{bookId}")
    public BookDto retrieveBook(@PathVariable UUID bookId) {
        loggingService.log("Retrieve book %s".formatted(bookId));
        var bookEntity =  bookService.retrieveBook(bookId);
        return new BookDto(bookEntity.getId(), bookEntity.getTitle(), bookEntity.getIsbn(), bookEntity.author.name);
    }
}
