package com.nextplay.nextplay.controllers;

import com.nextplay.nextplay.dtos.request.GameRequest;
import com.nextplay.nextplay.dtos.request.UserProfileRequest;
import com.nextplay.nextplay.dtos.response.GameResponse;
import com.nextplay.nextplay.dtos.response.ListGameResponse;
import com.nextplay.nextplay.dtos.response.UserProfileResponse;
import com.nextplay.nextplay.services.GameService;
import com.nextplay.nextplay.services.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final GameService gameService;

    public UserController(UserService userService, GameService gameService) {
        this.userService = userService;
        this.gameService = gameService;
    }

    @PostMapping("/create-profile")
    public ResponseEntity<UserProfileResponse> createUserProfile (Authentication authentication, @Valid @RequestBody UserProfileRequest userProfileRequest) {
        UserProfileResponse userProfileResponse = userService.createUserProfile(authentication, userProfileRequest);

        return ResponseEntity.status(HttpStatus.CREATED).body(userProfileResponse);
    }

    @PostMapping("/add-game")
    public ResponseEntity<GameResponse> addGame (Authentication authentication, @Valid @RequestBody GameRequest gameRequest) {
        GameResponse gameResponse = gameService.addGame(authentication, gameRequest);

        return ResponseEntity.ok().body(gameResponse);
    }

    @GetMapping("/list-all-games")
    public ResponseEntity<List<ListGameResponse>> listUserGames (Authentication authentication) {
        List<ListGameResponse> listGameResponses = gameService.listUserGames(authentication);

        return ResponseEntity.ok().body(listGameResponses);
    }
}
