package com.nextplay.nextplay.dtos.request;

import jakarta.validation.constraints.NotBlank;

public record UserProfileRequest(

        @NotBlank(message = "O nome de exibição é obrigatório.")
        String displayName
) {
}
