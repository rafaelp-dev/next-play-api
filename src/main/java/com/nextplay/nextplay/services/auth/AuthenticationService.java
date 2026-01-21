package com.nextplay.nextplay.services.auth;

import com.nextplay.nextplay.repositories.auth.AuthenticationRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService implements UserDetailsService {

    private final AuthenticationRepository authenticationRepository;

    public AuthenticationService (AuthenticationRepository authenticationRepository) {
        this.authenticationRepository = authenticationRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return authenticationRepository.findByEmail(email);
    }
}
