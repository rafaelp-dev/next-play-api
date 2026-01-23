package com.nextplay.nextplay.dtos.request;

import com.nextplay.nextplay.enums.GameStatus;

public record GameRequest(
        String title,
        GameStatus status,
        Boolean favorite,
        Long rating,
        String review
) {
}
