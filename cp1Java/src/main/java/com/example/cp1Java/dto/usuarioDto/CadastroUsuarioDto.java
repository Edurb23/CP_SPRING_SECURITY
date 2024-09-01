package com.example.cp1Java.dto.usuarioDto;

import jakarta.validation.constraints.NotBlank;

public record CadastroUsuarioDto(
        @NotBlank
        String name,
        @NotBlank
        String email,
        @NotBlank
        String password


) {
}
