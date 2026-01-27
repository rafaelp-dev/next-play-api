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

        @Min(value = 1, message = "A avaliação mínima é 1.")
        @Min(value = 5, message = "A avaliação máxima é 5.")
        Long rating,

        String review
) {
}
