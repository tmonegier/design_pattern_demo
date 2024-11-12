package org.example.orm_demo.filters;

import jakarta.servlet.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class HeaderCheckFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        /*HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;

        String headerValue = httpRequest.getHeader("X-User-Id");
        if(headerValue == null) {
            httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized: Missing X-User-Id header");
            return;
        }*/
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
