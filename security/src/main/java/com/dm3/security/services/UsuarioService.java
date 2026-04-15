package com.dm3.security.services;

import com.dm3.security.entity.Usuario;
import com.dm3.security.enums.Role;
import com.dm3.security.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    public List<Usuario> findAll() {
        return repository.findAll();
    }

    public Usuario findById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado! ID: " + id));
    }

    @Transactional
    public Usuario insert(String nome, String email, String telefone, String senha, List<Role> roles) {
        Usuario usuario = new Usuario();
        usuario.setNome(nome);
        usuario.setEmail(email);
        usuario.setTelefone(telefone);
        usuario.setSenha(senha);

        // Define "USER" como padrão se a requisição vier sem nenhuma role
        if (roles != null && !roles.isEmpty()) {
            usuario.getRoles().addAll(roles);
        } else {
            usuario.getRoles().add(Role.USER);
        }

        return repository.save(usuario);
    }

    @Transactional
    public Optional<Usuario> update(UUID id, String nome, String email, String telefone) {
        Optional<Usuario> usuarioExistente = repository.findById(id);

        if (usuarioExistente.isPresent()) {
            Usuario usuario = usuarioExistente.get();
            usuario.setNome(nome);
            usuario.setEmail(email);
            usuario.setTelefone(telefone);

            return Optional.of(repository.save(usuario));
        }

        return Optional.empty();
    }

    public boolean deleteById(UUID id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }
}