package org.example.orm_demo.authentication;

import lombok.extern.slf4j.Slf4j;
import org.example.orm_demo.authentication.model.User;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
public class UserService {

    public User retrieveUser(UUID id) {
        log.info("Retrieve user %s".formatted(id));
        return new User(id, "Thibault", "Monegier");
    }
}
