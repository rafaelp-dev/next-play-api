package com.nextplay.nextplay.services;

import com.nextplay.nextplay.dtos.request.UserRegisterRequest;
import com.nextplay.nextplay.dtos.response.UserRegisterResponse;
import com.nextplay.nextplay.entities.UserEntity;
import com.nextplay.nextplay.exceptions.ConflictException;
import com.nextplay.nextplay.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserRegisterResponse registerUser (UserRegisterRequest request) {

        if (userRepository.existsByEmail(request.email())) {
            throw new ConflictException("Email já cadastrado.");
        }

        UserEntity user = new UserEntity(
                request.username(),
                request.email(),
                request.password()
        );

        userRepository.save(user);

        return new UserRegisterResponse(
                "Usuário registrado com sucesso."
        );
    }
}
