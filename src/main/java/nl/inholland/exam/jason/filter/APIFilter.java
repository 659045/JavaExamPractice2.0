package nl.inholland.exam.jason.filter;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class APIFilter extends OncePerRequestFilter {
    private static final String apiKey = "12345";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (request.getHeader("Authorization") != null && request.getHeader("Authorization").startsWith("Bearer ")) {
            String token = request.getHeader("Authorization").substring(7);
            if (token.equals(apiKey)) {
                filterChain.doFilter(request, response);
            } else {
                response.sendError(HttpServletResponse.SC_FORBIDDEN, "Invalid API Key");
            }
        } else {
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "API Key is missing");
        }
    }
}
