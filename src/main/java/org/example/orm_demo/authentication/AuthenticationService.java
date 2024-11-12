package org.example.orm_demo.authentication;

import org.example.orm_demo.authentication.model.User;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AuthenticationService {

    private final UserService userService;
    public AuthenticationService(UserService userService) {
        this.userService = userService;
    }

    public boolean hasAccess(UUID id) {
        return hasAccess(userService.retrieveUser(id));
    }

    public boolean hasAccess(User user) {
        return true;
    }
}
