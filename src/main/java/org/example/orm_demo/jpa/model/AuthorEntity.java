package org.example.orm_demo.jpa.model;

import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "author")
public class AuthorEntity {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    public UUID id;

    public String name;

    @OneToMany(mappedBy = "author", fetch = FetchType.LAZY)
    private List<BookEntity> books;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<BookEntity> getBooks() {
        return books;
    }

    public void setBooks(List<BookEntity> books) {
        this.books = books;
    }
}
