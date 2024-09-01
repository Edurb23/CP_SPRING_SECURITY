package com.example.cp1Java.security;

import com.example.cp1Java.repository.UsuarioRepository;
import com.example.cp1Java.service.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsuarioRepository usuarioRepository;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader("Authorization");
        if (token != null){
            token = token.replace("Bearer ", "");
            var subject = tokenService.getSubject(token);
            var usuario = usuarioRepository.findByEmail(subject);
            var autenticationToken = new UsernamePasswordAuthenticationToken(usuario,
                    null, usuario.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(autenticationToken);
        }

        filterChain.doFilter(request, response);
    }
}
