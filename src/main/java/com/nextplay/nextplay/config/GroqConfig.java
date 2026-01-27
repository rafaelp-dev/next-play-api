package com.nextplay.nextplay.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;


@Configuration
public class GroqConfig {

    @Value("${groq.api.key}")
    private String apiKey;

    @Bean
    public RestClient groqClient() {
        return RestClient.builder()
                .baseUrl("https://api.groq.com/openai/v1" )
                .defaultHeader("Authorization", "Bearer " + apiKey)
                .defaultHeader("Content-Type", "application/json")
                .build();
    }
}
