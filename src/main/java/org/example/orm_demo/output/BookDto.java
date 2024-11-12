package org.example.orm_demo.output;

import java.util.UUID;

public record BookDto(UUID id, String title, String isbn, String authorName) {
}
