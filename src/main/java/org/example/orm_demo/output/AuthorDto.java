package org.example.orm_demo.output;

import java.util.List;

public record AuthorDto(String name, List<BookDto> books) {
}
