package org.example.orm_demo.jpa.model;

import jakarta.persistence.*;
import org.hibernate.annotations.Parent;
import org.hibernate.annotations.SQLRestriction;
import org.hibernate.annotations.Where;

import java.util.UUID;

@Entity
@Table(name = "books")
@SQLRestriction("status <> 'PENDING'")
public class BookEntity {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private UUID id;

    private String title;

    private String isbn;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn( referencedColumnName = "id")
    public AuthorEntity author;

    @Enumerated(EnumType.STRING)
    private BookStatus status;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public AuthorEntity getAuthor() {
        return author;
    }

    public void setAuthor(AuthorEntity authorEntity) {
        this.author = authorEntity;
    }

    public BookStatus getStatus() {
        return status;
    }

    public void setStatus(BookStatus status) {
        this.status = status;
    }
}
