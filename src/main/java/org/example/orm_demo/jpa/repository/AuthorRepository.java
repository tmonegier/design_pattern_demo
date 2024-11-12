package org.example.orm_demo.jpa.repository;

import org.example.orm_demo.jpa.model.AuthorEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface AuthorRepository extends JpaRepository<AuthorEntity, UUID> {

    @EntityGraph(attributePaths = {"books"})
    List<AuthorEntity> findAll();
}

