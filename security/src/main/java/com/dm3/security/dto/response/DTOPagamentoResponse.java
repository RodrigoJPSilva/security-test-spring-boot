package com.dm3.security.dto.response;

import com.dm3.security.entity.Pagamento;
import lombok.Getter;

import java.time.Instant;
import java.util.UUID;

@Getter
public class DTOPagamentoResponse {

    private UUID id;
    private Instant momento;
    private UUID pedidoId;

    public DTOPagamentoResponse() {}

    public DTOPagamentoResponse(Pagamento entity) {
        this.id = entity.getId();
        this.momento = entity.getMomento();
        this.pedidoId = entity.getPedido().getId();
    }

}