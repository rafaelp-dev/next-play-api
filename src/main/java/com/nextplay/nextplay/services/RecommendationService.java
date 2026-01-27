package com.nextplay.nextplay.services;

import com.nextplay.nextplay.dtos.response.GameRecommendationResponse;
import com.nextplay.nextplay.services.groq.GroqClientService;
import com.nextplay.nextplay.utils.GameRecommendationPromptBuilder;
import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;

@Service
public class RecommendationService {

    private final GroqClientService groqClientService;
    private final ObjectMapper objectMapper;
    private final GameRecommendationPromptBuilder gameRecommendationPromptBuilder;

    public RecommendationService(GroqClientService groqClientService, ObjectMapper objectMapper, GameRecommendationPromptBuilder gameRecommendationPromptBuilder) {
        this.groqClientService = groqClientService;
        this.objectMapper = objectMapper;
        this.gameRecommendationPromptBuilder = gameRecommendationPromptBuilder;
    }

    public GameRecommendationResponse recommendations (String gameTitle) {
        String prompt = gameRecommendationPromptBuilder.buildPrompt(gameTitle);

        String groqResponse = groqClientService.getRecommendations(prompt);

        try {
            return objectMapper.readValue(groqResponse, GameRecommendationResponse.class);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao desserializar a resposta do Groq", e);
        }
    }
}
