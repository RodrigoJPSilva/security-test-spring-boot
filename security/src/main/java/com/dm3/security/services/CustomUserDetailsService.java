package com.dm3.security.services;

import com.dm3.security.entity.Usuario;
import com.dm3.security.repository.UsuarioRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UsuarioRepository repository;

    public CustomUserDetailsService(UsuarioRepository usuarioRepository) {
        this.repository = usuarioRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario user = repository.findByEmail(email);
        return User.builder()
                .username(user.getEmail())
                .password(user.getSenha())
                .roles(user.getRoles().getLast().name())
                .build();
    }
}
