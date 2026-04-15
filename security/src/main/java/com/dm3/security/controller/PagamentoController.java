package com.dm3.security.controller;

import com.dm3.security.dto.request.DTOPagamentoRequest;
import com.dm3.security.dto.response.DTOPagamentoResponse;
import com.dm3.security.entity.Pagamento;
import com.dm3.security.services.PagamentoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/pagamentos")
public class PagamentoController {

    @Autowired
    private PagamentoService service;

    @PostMapping
    public ResponseEntity<DTOPagamentoResponse> registrarPagamento(@Valid @RequestBody DTOPagamentoRequest dto) {

        Pagamento obj = service.pagarPedido(dto.getPedidoId());

        DTOPagamentoResponse responseDto = new DTOPagamentoResponse(obj);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(obj.getId()).toUri();

        return ResponseEntity.created(uri).body(responseDto);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<DTOPagamentoResponse> encontrarPorId(@PathVariable UUID id) {
        Optional<Pagamento> obj = service.findById(id);

        return obj.isPresent()
                ? ResponseEntity.ok(new DTOPagamentoResponse(obj.get()))
                : ResponseEntity.notFound().build();
    }
}