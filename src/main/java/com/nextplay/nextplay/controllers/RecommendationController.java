package com.nextplay.nextplay.controllers;

import com.nextplay.nextplay.dtos.request.GameRecommendationRequest;
import com.nextplay.nextplay.dtos.response.GameRecommendationResponse;
import com.nextplay.nextplay.services.RecommendationService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/recommendations")
public class RecommendationController {

    private final RecommendationService recommendationService;

    public RecommendationController(RecommendationService recommendationService) {
        this.recommendationService = recommendationService;
    }

    @PostMapping("/by-game-title")
    public ResponseEntity<GameRecommendationResponse> recommend (@Valid @RequestBody GameRecommendationRequest gameRecommendationRequest) {
        GameRecommendationResponse response = recommendationService.recommendations(gameRecommendationRequest.gameTitle());
        return ResponseEntity.ok().body(response);
    }
}
