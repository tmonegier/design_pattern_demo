package org.example.orm_demo.controller;

import org.example.orm_demo.jpa.repository.AuthorRepository;
import org.example.orm_demo.output.AuthorDto;
import org.example.orm_demo.output.BookDto;
import org.example.orm_demo.service.ILoggingService;
import org.example.orm_demo.service.LoggingService;
import org.example.orm_demo.service.LoggingServiceSingleton;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    private final AuthorRepository authorRepository;
    private final ILoggingService loggingService;

    public AuthorController(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
        this.loggingService = LoggingServiceSingleton.getInstance();
    }

    @GetMapping("/{authorId}")
    private AuthorDto retrieveAuthor(@PathVariable UUID authorId) {
        loggingService.log("Retrieve author %s".formatted(authorId));
        var author = authorRepository.findById(authorId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        return new AuthorDto(
                author.getName(),
                author.getBooks().stream().map(
                    bookEntity -> new BookDto(bookEntity.getId(), bookEntity.getTitle(), bookEntity.getIsbn(), bookEntity.getAuthor().getName())
                ).toList()
        );
    }

    @GetMapping()
    private List<AuthorDto> retrieveAllAuthors() {
        loggingService.log("Retrieve all authors");
        return authorRepository.findAll().stream().map(
            authorEntity -> new AuthorDto(
                    authorEntity.getName(),
                    authorEntity.getBooks().stream().map(
                        bookEntity -> new BookDto(bookEntity.getId(), bookEntity.getTitle(), bookEntity.getIsbn(), bookEntity.getAuthor().getName())
                    ).toList()
            )
        ).toList();
    }
}
