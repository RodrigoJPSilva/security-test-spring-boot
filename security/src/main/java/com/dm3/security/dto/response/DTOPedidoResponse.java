package com.dm3.security.dto.response;

import com.dm3.security.entity.Pedido;
import com.dm3.security.enums.StatusDoPedido;
import lombok.Getter;

import java.time.Instant;
import java.util.UUID;

@Getter
public class DTOPedidoResponse {

    private UUID id;
    private Instant momento;
    private StatusDoPedido status;
    private UUID clienteId;
    private String nomeCliente;

    public DTOPedidoResponse() {}

    public DTOPedidoResponse(Pedido entity) {
        this.id = entity.getId();
        this.momento = entity.getMomento();
        this.status = entity.getStatus();
        // Acessamos o relacionamento de forma segura
        if (entity.getCliente() != null) {
            this.clienteId = entity.getCliente().getId();
            this.nomeCliente = entity.getCliente().getNome();
        }
    }
}