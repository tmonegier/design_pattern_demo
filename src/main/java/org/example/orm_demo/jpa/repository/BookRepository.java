package org.example.orm_demo.jpa.repository;

import org.example.orm_demo.jpa.model.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.nio.channels.FileChannel;
import java.util.Optional;
import java.util.UUID;

public interface BookRepository extends JpaRepository<BookEntity, UUID> {
    Optional<BookEntity> findByIsbn(String isbn);
}
