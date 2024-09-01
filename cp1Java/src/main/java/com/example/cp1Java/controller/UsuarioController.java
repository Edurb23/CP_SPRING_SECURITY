package com.example.cp1Java.controller;

import com.example.cp1Java.dto.token.DataAuthDto;
import com.example.cp1Java.dto.token.DataTokenDto;
import com.example.cp1Java.dto.usuarioDto.CadastroUsuarioDto;
import com.example.cp1Java.dto.usuarioDto.DetalhesUsuarioDto;
import com.example.cp1Java.model.Usuario;
import com.example.cp1Java.repository.UsuarioRepository;
import com.example.cp1Java.service.TokenService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;


@RestController
@RequestMapping("/auth")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private AuthenticationManager manager;


    @PostMapping("login")
    public ResponseEntity<DataTokenDto> login(@RequestBody DataAuthDto dto){
        var token = new UsernamePasswordAuthenticationToken(dto.email(), dto.password());
        var authentication = manager.authenticate(token);
        var tokenJwt = tokenService.generateToken((Usuario) authentication.getPrincipal());
        return ResponseEntity.ok(new DataTokenDto(tokenJwt));

    }


    @PostMapping("register")
    @Transactional
    public ResponseEntity<DetalhesUsuarioDto> post(@RequestBody @Valid CadastroUsuarioDto dto,
                                                   UriComponentsBuilder builder){
        var usuario = new Usuario(dto.name(),dto.email(), passwordEncoder.encode(dto.password()));
        usuarioRepository.save(usuario);
        var uri = builder.path("/usuarios/{id}").buildAndExpand(usuario.getId()).toUri();
        return ResponseEntity.created(uri).body(new DetalhesUsuarioDto(usuario));


    }
}
