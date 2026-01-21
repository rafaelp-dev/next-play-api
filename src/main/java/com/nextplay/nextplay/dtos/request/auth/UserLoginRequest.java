package com.nextplay.nextplay.dtos.request.auth;

import jakarta.validation.constraints.NotBlank;

public record UserLoginRequest(

        @NotBlank(message = "Email é obrigatório")
        String email,

        @NotBlank(message = "Senha é obrigatória")
        String password
) {
}
