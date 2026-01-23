package com.nextplay.nextplay.controllers;

import com.nextplay.nextplay.dtos.request.GameRequest;
import com.nextplay.nextplay.dtos.response.GameResponse;
import com.nextplay.nextplay.dtos.response.ListGameResponse;
import com.nextplay.nextplay.services.GameService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/game")
public class GameController {

    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
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

    @GetMapping("/list-favorite-games")
    public ResponseEntity<List<ListGameResponse>> listFavoriteGames (Authentication authentication) {
        List<ListGameResponse> listGameResponses = gameService.listFavoriteGames(authentication);

        return ResponseEntity.ok().body(listGameResponses);
    }
}
