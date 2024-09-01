package com.example.cp1Java.model;

import com.example.cp1Java.dto.tarefaDto.AtualizacaoPostDto;
import com.example.cp1Java.dto.tarefaDto.CadastroTarefaDto;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "TB_CP_TAREFA")
@Getter
@Setter
@NoArgsConstructor
public class Tarefa {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "name_tarefa", nullable = false, length = 255)
    private String name;
    @Column(name = "descricao_tarefa", nullable = false, length = 255)
    private String descricao;
    @Column(name = "dataConclusao_tarefa", nullable = false)
    private LocalDate dataConclusao;

    @Column(name = "status_tarefa", nullable = false, length = 255)
    private String status;

    public Tarefa(@Valid CadastroTarefaDto dto) {
        name = dto.nome();
        descricao = dto.descricao();
        dataConclusao = dto.dataConclusao();
        status = dto.status();
    }

    public void atualizar(@Valid AtualizacaoPostDto dto) {
        if (dto.nome() != null)
            this.name = dto.nome();
        if (dto.descricao() != null)
            this.descricao = dto.descricao();
        if(dto.dataConclusao() != null)
            this.dataConclusao = dto.dataConclusao();
        if (dto.status() != null)
            this.status = dto.status();
    }
}
