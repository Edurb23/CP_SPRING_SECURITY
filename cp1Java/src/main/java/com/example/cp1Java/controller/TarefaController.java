package com.example.cp1Java.controller;

import com.example.cp1Java.dto.tarefaDto.AtualizacaoPostDto;
import com.example.cp1Java.dto.tarefaDto.CadastroTarefaDto;
import com.example.cp1Java.dto.tarefaDto.DetalhesTarefasDto;
import com.example.cp1Java.dto.usuarioDto.CadastroUsuarioDto;
import com.example.cp1Java.model.Tarefa;
import com.example.cp1Java.repository.TarefaRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Pageable;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("task")
public class TarefaController {

    @Autowired
    TarefaRepository tarefaRepository;


    @GetMapping
    public ResponseEntity<List<DetalhesTarefasDto>> get(Pageable pageable){
        var tarefas = tarefaRepository.findAll(pageable).map(DetalhesTarefasDto::new).toList();
        return ResponseEntity.ok(tarefas);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<DetalhesTarefasDto> post(@RequestBody @Valid CadastroTarefaDto dto,
                                                   UriComponentsBuilder builder){
        var tarefa = new Tarefa(dto);
        tarefaRepository.save(tarefa);
        var url = builder.path("tarefa/{id}").buildAndExpand(tarefa.getId()).toUri();
        return ResponseEntity.created(url).body(new DetalhesTarefasDto(tarefa));

    }

    @PutMapping("{id}")
    @Transactional
    public ResponseEntity<DetalhesTarefasDto> put(@PathVariable("id") Long id,
                                                  @RequestBody @Valid AtualizacaoPostDto dto){
        var tarefa = tarefaRepository.getReferenceById(id);
        tarefa.atualizar(dto);
        return ResponseEntity.ok(new DetalhesTarefasDto(tarefa));
    }

    @DeleteMapping("{id}")
    @Transactional
    public ResponseEntity<Void> delete(@PathVariable("id") Long id){
        tarefaRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }



}
