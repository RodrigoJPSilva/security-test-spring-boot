package com.dm3.security.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class DTOPedidoRequest {

    @NotNull(message = "O ID do cliente é obrigatório")
    private UUID clienteId;

    public UUID getClienteId() {
        return clienteId;
    }

    public void setClienteId(UUID clienteId) {
        this.clienteId = clienteId;
    }
}