package com.example.cp1Java.dto.tarefaDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record CadastroTarefaDto(
        @NotBlank @Size(max = 255)
        String nome,
        @NotBlank @Size(max = 255)
        String descricao,
        @NotBlank
        LocalDate dataConclusao,
        @NotBlank
        String status
) {
}
