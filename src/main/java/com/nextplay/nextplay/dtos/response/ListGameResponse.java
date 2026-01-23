package com.nextplay.nextplay.dtos.response;

import com.nextplay.nextplay.enums.GameStatus;

import java.time.LocalDateTime;

public record ListGameResponse(
        String title,
        GameStatus status,
        Boolean favorite,
        LocalDateTime addedAt,
        Long rating,
        String review
) {
}
