package com.example.cp1Java.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;


@Entity
@Table(name = "TB_CP_USUARIO")
@Getter
@Setter
@NoArgsConstructor
public class Usuario implements UserDetails {


    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "nome_usuario", nullable = false, length = 255)
    private String name;

    @Column(name = "email_usuario", nullable = false, length = 255, unique = true)
    private String email;

    @Column (name = "password_usuario", nullable = false, length = 255)
    private String password;


    public Usuario(@NotBlank String name, @NotBlank String email, String encode) {
        this.name = name;
        this.email = email;
        this.password = encode;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
