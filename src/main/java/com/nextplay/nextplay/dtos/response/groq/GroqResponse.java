package com.nextplay.nextplay.dtos.response.groq;

import java.util.List;

public record GroqResponse(
        List<GroqChoice> choices
) {
}
