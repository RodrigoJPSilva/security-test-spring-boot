package com.dm3.security.controller;

import com.dm3.security.dto.request.DTOItemDoPedidoRequest;
import com.dm3.security.dto.response.DTOItemDoPedidoResponse;
import com.dm3.security.entity.ItemDoPedido;
import com.dm3.security.services.ItemDoPedidoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/pedidos")
public class ItemDoPedidoController {

    @Autowired
    private ItemDoPedidoService service;

    @PostMapping("/{pedidoId}/itens")
    public ResponseEntity<DTOItemDoPedidoResponse> adicionarItem(
            @PathVariable UUID pedidoId,
            @Valid @RequestBody DTOItemDoPedidoRequest dto) {

        // Chama o service que criamos para processar a regra de negócio
        ItemDoPedido obj = service.adicionarItemAoPedido(
                pedidoId,
                dto.getProdutoId(),
                dto.getQuantidade()
        );

        // Converte a entidade salva para o DTO de resposta
        DTOItemDoPedidoResponse responseDto = new DTOItemDoPedidoResponse();

        // Retorna o status 201 (Created) e o corpo com os dados do item
        return ResponseEntity.status(201).body(responseDto);
    }
}