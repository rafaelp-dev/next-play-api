package com.nextplay.nextplay.dtos.response.groq;

import com.nextplay.nextplay.dtos.request.groq.GroqMessage;

public record GroqChoice(
        GroqMessage message
) {
}
