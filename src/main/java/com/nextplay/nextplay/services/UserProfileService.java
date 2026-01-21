package com.nextplay.nextplay.services;

import com.nextplay.nextplay.dtos.request.UserProfileRequest;
import com.nextplay.nextplay.dtos.response.UserProfileResponse;
import com.nextplay.nextplay.entities.UserEntity;
import com.nextplay.nextplay.entities.UserProfileEntity;
import com.nextplay.nextplay.exceptions.ConflictException;
import com.nextplay.nextplay.exceptions.NotFoundException;
import com.nextplay.nextplay.repositories.UserProfileRepository;
import com.nextplay.nextplay.repositories.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class UserProfileService {

    private final UserProfileRepository userProfileRepository;
    private final UserRepository userRepository;

    public UserProfileService(UserProfileRepository userProfileRepository, UserRepository userRepository) {
        this.userProfileRepository = userProfileRepository;
        this.userRepository = userRepository;
    }

    public UserProfileResponse createUserProfile (Authentication authentication, UserProfileRequest userProfileRequest) {
        String email = authentication.getName();

        UserEntity userEntity = userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("Usuário não cadastrado."));

        if (userProfileRepository.existsByDisplayName(userProfileRequest.displayName())) {
            throw new ConflictException("Este nome de exibição já está em uso.");
        }

        UserProfileEntity userProfileEntity = new UserProfileEntity(
                userEntity,
                userProfileRequest.displayName()
        );

        userProfileRepository.save(userProfileEntity);

        return new UserProfileResponse("Perfil cadastrado com sucesso.");
    }
}
