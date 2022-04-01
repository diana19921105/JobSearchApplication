package com.dianaszanto.jobsearchapi.security;

import com.dianaszanto.jobsearchapi.service.ClientService;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

public class RequestFilter extends OncePerRequestFilter {
    private final ClientService clientService;

    public RequestFilter(ClientService clientService) {
        this.clientService = clientService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String apikey = request.getHeader("x-api-key");

        if (apikey == null || apikey.isBlank()) {
            throw new BadCredentialsException("Api key is missing!");
        }

        if (SecurityContextHolder.getContext().getAuthentication() == null) {
            clientService.findByApiKey(UUID.fromString(apikey)).orElseThrow(
                    () -> new BadCredentialsException("Invalid Api key!"));
        }
        ApiKeyAuthToken apiKeyAuthToken = new ApiKeyAuthToken(apikey, true);
        SecurityContextHolder.getContext().setAuthentication(apiKeyAuthToken);

        filterChain.doFilter(request, response);
    }
}
