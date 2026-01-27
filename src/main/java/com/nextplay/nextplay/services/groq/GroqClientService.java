package com.nextplay.nextplay.services.groq;

import com.nextplay.nextplay.dtos.request.groq.GroqMessage;
import com.nextplay.nextplay.dtos.request.groq.GroqRequest;
import com.nextplay.nextplay.dtos.response.groq.GroqResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
public class GroqClientService {

    private final RestClient restClient;

    public GroqClientService(RestClient restClient) {
        this.restClient = restClient;
    }

    public String getRecommendations (String prompt) {
        GroqRequest groqRequest = new GroqRequest(
                "openai/gpt-oss-20b",
                List.of(new GroqMessage("user", prompt)),
                1.0,
                2048,
                false
        );

        GroqResponse groqResponse = restClient.post()
                .uri("/chat/completions")
                .body(groqRequest)
                .retrieve()
                .body(GroqResponse.class);

        if (groqResponse == null || groqResponse.choices() == null || groqResponse.choices().isEmpty()) {
            throw new RuntimeException("Resposta inválida do Groq");
        }

        return groqResponse.choices()
                .get(0)
                .message()
                .content();
    }
}
