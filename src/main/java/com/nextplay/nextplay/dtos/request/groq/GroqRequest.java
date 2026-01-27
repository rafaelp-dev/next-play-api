package com.nextplay.nextplay.dtos.request.groq;

import java.util.List;

public record GroqRequest(
        String model,
        List<GroqMessage> messages,
        Double temperature,
        Integer max_completion_tokens,
        Boolean stream
) {
}
