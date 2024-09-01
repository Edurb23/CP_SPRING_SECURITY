package com.example.cp1Java.dto.usuarioDto;

import com.example.cp1Java.model.Usuario;

public record DetalhesUsuarioDto(Long id, String name , String email, String password ) {
    public DetalhesUsuarioDto(Usuario usuario){
        this(usuario.getId(),usuario.getName(),usuario.getEmail(),usuario.getPassword());
    }
}
