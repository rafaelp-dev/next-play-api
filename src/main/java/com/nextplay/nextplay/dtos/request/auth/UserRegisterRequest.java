package com.nextplay.nextplay.dtos.request.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserRegisterRequest(
        @NotBlank(message = "O nome de usuário é obrigatório.")
        String username,

        @Email(message = "O email é obrigatório.")
        String email,

        @NotBlank(message = "A senha é obrigatória.")
        String password
) {
}
