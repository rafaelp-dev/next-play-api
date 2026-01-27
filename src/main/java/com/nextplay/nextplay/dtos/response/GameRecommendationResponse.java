package com.nextplay.nextplay.dtos.response;

import java.util.List;

public record GameRecommendationResponse(
        List<RecommendationItem> recommendations
) {
}
