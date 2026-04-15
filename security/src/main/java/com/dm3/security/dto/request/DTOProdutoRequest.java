package com.dm3.security.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class DTOProdutoRequest {

    @NotBlank(message = "Nome é obrigatório")
    private String nome;
    private String descricao;
    private String imgUrl;

    @Positive(message = "O preço deve ser positivo")
    private Double preco;

    private List<UUID> categoriasIds = new ArrayList<>();
}