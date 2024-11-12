package org.example.orm_demo.service;

import jakarta.transaction.Transactional;
import org.example.orm_demo.input.NewBookDto;
import org.example.orm_demo.jpa.model.BookEntity;

import java.util.UUID;

public interface IBookService {
    BookEntity createNewBook(NewBookDto newBook);

    BookEntity retrieveBook(UUID bookId);

    BookEntity updateBook(UUID bookId, String isbn);
}
