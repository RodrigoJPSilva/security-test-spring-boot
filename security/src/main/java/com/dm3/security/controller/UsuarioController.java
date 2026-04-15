package com.dm3.security.controller;

import com.dm3.security.dto.request.DTOUsuarioRequest;
import com.dm3.security.dto.response.DTOUsuarioResponse;
import com.dm3.security.entity.Usuario;
import com.dm3.security.services.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @GetMapping
    public ResponseEntity<List<DTOUsuarioResponse>> encontrarTodos() {
        List<Usuario> list = service.findAll();
        // Convertendo a lista de Entidades para lista de DTO Responses (esconde a senha de todo mundo)
        List<DTOUsuarioResponse> listDto = list.stream()
                .map(DTOUsuarioResponse::new).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDto);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<DTOUsuarioResponse> encontrarPorId(@PathVariable UUID id) {
        Usuario obj = service.findById(id);
        return ResponseEntity.ok().body(new DTOUsuarioResponse(obj));
    }

    @PostMapping
    public ResponseEntity<DTOUsuarioResponse> insert(@Valid @RequestBody DTOUsuarioRequest dto) {
        Usuario obj = service.insert(
                dto.getNome(),
                dto.getEmail(),
                dto.getTelefone(),
                dto.getSenha(),
                dto.getRoles()
        );

        DTOUsuarioResponse responseDto = new DTOUsuarioResponse(obj);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).body(responseDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DTOUsuarioResponse> alterarPorId(@PathVariable UUID id, @RequestBody @Valid DTOUsuarioRequest dtoRequest) {
        Optional<Usuario> usuario = service.update(
                id,
                dtoRequest.getNome(),
                dtoRequest.getEmail(),
                dtoRequest.getTelefone()
        );

        return usuario.isPresent()
                ? ResponseEntity.ok(new DTOUsuarioResponse(usuario.get()))
                : ResponseEntity.notFound().build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deletar(@PathVariable UUID id) {
        boolean deletado = service.deleteById(id);

        return deletado
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}