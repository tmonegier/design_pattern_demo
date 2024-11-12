package org.example.orm_demo.input;

import java.util.UUID;

public record NewBookDto(String title, String isbn, UUID authorId) {
}
