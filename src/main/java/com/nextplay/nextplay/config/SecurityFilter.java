package com.nextplay.nextplay.config;

import com.auth0.jwt.exceptions.JWTCreationException;
import com.nextplay.nextplay.services.auth.AuthenticationService;
import com.nextplay.nextplay.services.auth.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    private final TokenService tokenService;
    private final AuthenticationService authService;

    public SecurityFilter(TokenService tokenService, AuthenticationService authService1) {
        this.tokenService = tokenService;
        this.authService = authService1;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = recoverToken(request);

        if (token != null) {
            try {
                String email = tokenService.validateToken(token);

                if (email != null) {
                    UserDetails userDetails = authService.loadUserByUsername(email);
                    if (userDetails != null) {
                        UsernamePasswordAuthenticationToken authenticationToken =
                                new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                    }
                }
            } catch (JWTCreationException ex) {
                response.setStatus(HttpStatus.UNAUTHORIZED.value());
                response.setContentType("application/json");
                response.getWriter().write("{\"error\": \"Token inválido ou expirado.\"}");
                return;
            }
        }

        filterChain.doFilter(request, response);
    }

    private String recoverToken(HttpServletRequest httpServletRequest) {
        String authoziationHeader = httpServletRequest.getHeader("Authorization");

        if (authoziationHeader == null) {
            return null;
        } else {
            return authoziationHeader.replace("Bearer ", "");
        }
    }
}
