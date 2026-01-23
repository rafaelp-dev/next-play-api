package com.nextplay.nextplay.services;

import com.nextplay.nextplay.dtos.request.GameRequest;
import com.nextplay.nextplay.dtos.request.UserProfileRequest;
import com.nextplay.nextplay.dtos.response.GameResponse;
import com.nextplay.nextplay.dtos.response.UserProfileResponse;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserProfileService userProfileService;
    private final GameService gameService;

    public UserService(UserProfileService userProfileService, GameService gameService) {
        this.userProfileService = userProfileService;
        this.gameService = gameService;
    }

    public UserProfileResponse createUserProfile(Authentication authentication, UserProfileRequest userProfileRequest) {
        return userProfileService.createUserProfile(authentication, userProfileRequest);
    }

    public GameResponse addGame (Authentication authentication, GameRequest gameRequest) {
        return gameService.addGame(authentication, gameRequest);
    }
}
