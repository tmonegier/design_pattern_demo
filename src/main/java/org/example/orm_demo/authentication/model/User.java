package org.example.orm_demo.authentication.model;

import java.util.UUID;

public record User(UUID id, String firstName, String lastName) {
}
