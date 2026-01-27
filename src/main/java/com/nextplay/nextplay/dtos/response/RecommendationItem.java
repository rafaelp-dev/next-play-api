package com.nextplay.nextplay.dtos.response;

import java.util.List;

public record RecommendationItem(
        String title,
        String reason,
        String similarityLevel,
        List<String> platform
) {
}
