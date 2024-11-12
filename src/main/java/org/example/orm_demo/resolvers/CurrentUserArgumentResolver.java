package org.example.orm_demo.resolvers;

import jakarta.servlet.http.HttpServletRequest;
import org.example.orm_demo.authentication.UserService;
import org.springframework.core.MethodParameter;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.util.UUID;

public class CurrentUserArgumentResolver implements HandlerMethodArgumentResolver {

    private final UserService userService;

    public CurrentUserArgumentResolver(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(CurrentUser.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter,
                                  ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest,
                                  org.springframework.web.bind.support.WebDataBinderFactory binderFactory) {
        HttpServletRequest request =  webRequest.getNativeRequest(HttpServletRequest.class);

        return userService.retrieveUser(UUID.fromString(request.getHeader("x-user-id")));
    }
}