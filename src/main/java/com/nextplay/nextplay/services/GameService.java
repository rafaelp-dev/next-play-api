package com.nextplay.nextplay.services;

import com.nextplay.nextplay.dtos.request.GameRequest;
import com.nextplay.nextplay.dtos.response.GameResponse;
import com.nextplay.nextplay.entities.GameEntity;
import com.nextplay.nextplay.entities.UserEntity;
import com.nextplay.nextplay.entities.UserProfileEntity;
import com.nextplay.nextplay.exceptions.NotFoundException;
import com.nextplay.nextplay.mappers.GameMapper;
import com.nextplay.nextplay.repositories.GameRepository;
import com.nextplay.nextplay.repositories.UserProfileRepository;
import com.nextplay.nextplay.repositories.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class GameService {
    private final GameRepository gameRepository;
    private final UserRepository userRepository;
    private final UserProfileRepository userProfileRepository;
    private final GameMapper gameMapper;

    public GameService(GameRepository gameRepository, UserRepository userRepository, UserProfileRepository userProfileRepository, GameMapper gameMapper) {
        this.gameRepository = gameRepository;
        this.userRepository = userRepository;
        this.userProfileRepository = userProfileRepository;
        this.gameMapper = gameMapper;
    }

    public GameResponse addGame (Authentication authentication, GameRequest gameRequest) {
        String email = authentication.getName();

        UserEntity userEntity = userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("Usuário não cadastrado."));

        UserProfileEntity userProfileEntity = userProfileRepository.findByUserUserId(userEntity.getUserId())
                .orElseThrow(() -> new NotFoundException("Perfil de usuário não cadastrado."));

        GameEntity gameEntity = gameMapper.toGame(userProfileEntity, gameRequest);

        gameRepository.save(gameEntity);

        return new GameResponse(
                "Jogo adicionado com sucesso!"
        );
    }
}
