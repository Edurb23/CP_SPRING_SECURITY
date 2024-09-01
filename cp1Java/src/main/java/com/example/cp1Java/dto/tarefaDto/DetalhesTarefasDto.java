package com.example.cp1Java.dto.tarefaDto;

import com.example.cp1Java.model.Tarefa;

import java.time.LocalDate;

public record DetalhesTarefasDto(Long id, String name, String descricao, LocalDate dataConclusao, String status) {
    public DetalhesTarefasDto(Tarefa tarefa){
        this(tarefa.getId(),tarefa.getName(),tarefa.getDescricao(),tarefa.getDataConclusao(),tarefa.getStatus());
    }
}
