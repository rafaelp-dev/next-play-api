package com.nextplay.nextplay.services;

import com.nextplay.nextplay.dtos.request.UserLoginRequest;
import com.nextplay.nextplay.dtos.request.UserRegisterRequest;
import com.nextplay.nextplay.dtos.response.UserLoginResponse;
import com.nextplay.nextplay.dtos.response.UserRegisterResponse;
import com.nextplay.nextplay.entities.UserEntity;
import com.nextplay.nextplay.exceptions.ConflictException;
import com.nextplay.nextplay.exceptions.NotFoundException;
import com.nextplay.nextplay.mappers.UserMapper;
import com.nextplay.nextplay.repositories.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    public AuthService(UserRepository userRepository, AuthenticationManager authenticationManager, TokenService tokenService) {
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    public UserRegisterResponse register (UserRegisterRequest userRegisterRequest) {

        if (userRepository.existsByEmail(userRegisterRequest.email())) {
            throw new ConflictException("Este email já está cadastrado.");
        }

        String encodedPassword = new BCryptPasswordEncoder().encode(userRegisterRequest.password());

        UserEntity userEntity = new UserEntity(
                userRegisterRequest.username(),
                userRegisterRequest.email(),
                encodedPassword
        );

        userRepository.save(userEntity);

        return new UserRegisterResponse("Usuário registrado com sucesso.");
    }

    public UserLoginResponse login (UserLoginRequest userLoginRequest) {

        if (!userRepository.existsByEmail(userLoginRequest.email())) {
            throw new NotFoundException("Este email não está registrado.");
        }

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                userLoginRequest.email(),
                userLoginRequest.password()
        );

        Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        String token = tokenService.generateToken((UserEntity) authentication.getPrincipal());

        return new UserLoginResponse(token);
    }
}
