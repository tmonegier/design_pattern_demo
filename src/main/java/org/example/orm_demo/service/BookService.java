package org.example.orm_demo.service;

import jakarta.transaction.Transactional;
import org.example.orm_demo.input.NewBookDto;
import org.example.orm_demo.jpa.model.BookEntity;
import org.example.orm_demo.jpa.model.BookStatus;
import org.example.orm_demo.jpa.repository.AuthorRepository;
import org.example.orm_demo.jpa.repository.BookRepository;
import org.springframework.http.HttpStatus;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@Service
public class BookService implements IBookService {

    private final BookRepository bookRepository;
    private final ILoggingService loggingService;
    private final AuthorRepository authorRepository;

    public BookService(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.loggingService = LoggingServiceSingleton.getInstance();
    }

    public BookEntity retrieveBook(UUID bookId) {
        var book = bookRepository.findById(bookId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        loggingService.log("Book has been retrieved %s".formatted(book.getTitle()));
        return book;
    }

    public BookEntity createNewBook(NewBookDto newBook) {
        var bookEntity = new BookEntity();
        bookEntity.setTitle(newBook.title());
        bookEntity.setIsbn(newBook.isbn());
        bookEntity.setAuthor(authorRepository.findById(newBook.authorId()).get());
        bookEntity.setStatus(BookStatus.PENDING);
        return bookRepository.save(bookEntity);
    }

    public BookEntity updateBook(UUID bookId, String isbn) {
        var book = retrieveBook(bookId);
        book.setIsbn(isbn);
        return bookRepository.save(book);
    }
}
