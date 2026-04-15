package com.dm3.security.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DTOItemDoPedidoResponse {
    private UUID produtoId;
    private String nomeProduto;
    private Integer quantidade;
    private Double precoUnitario;
    private Double subTotal;
}