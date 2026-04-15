package com.dm3.security.entity;

import com.dm3.security.enums.StatusDoPedido;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "tb_pedido")
public class Pedido {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private Instant momento;
    private StatusDoPedido status;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Usuario cliente;

    @OneToOne(mappedBy = "pedido", cascade = CascadeType.ALL)
    private  Pagamento pagamento;

    @OneToMany(mappedBy = "id.pedido")
    private Set<ItemDoPedido> items = new HashSet<>();

    public void setItems(Set<ItemDoPedido> items) {
        this.items = items;
    }

    public List<Produto> getProdutos() {
        return items.stream().map(x -> x.getProduto()).toList();
    }
    }
