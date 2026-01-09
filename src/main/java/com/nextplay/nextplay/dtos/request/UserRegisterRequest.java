package com.nextplay.nextplay.dtos.request;

import jakarta.validation.constraints.NotBlank;

public record UserRegisterRequest(
        @NotBlank(message = "O nome de usuário é obrigatório.")
        String username,

        @NotBlank(message = "O email é obrigatório.")
        String email,

        @NotBlank(message = "A senha é obrigatória.")
        String password
) {
}
