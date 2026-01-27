package com.nextplay.nextplay.dtos.request;

import com.nextplay.nextplay.enums.GameStatus;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record GameRequest(

        @NotBlank(message = "O título do jogo é obrigatório.")
        String title,

        @NotNull(message = "O status do jogo é obrigatório.")
        GameStatus status,

        @NotNull(message = "O campo favorito é obrigatório.")
        Boolean favorite,

        @NotNull(message = "A nota do jogo é obrigatória.")
        Long rating,

        String review
) {
}
