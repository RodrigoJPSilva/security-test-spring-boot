package com.dm3.security.services;
import com.dm3.security.entity.ItemDoPedido;
import com.dm3.security.entity.Pedido;
import com.dm3.security.entity.Produto;
import com.dm3.security.repository.ItemDoPedidoRepository;
import com.dm3.security.repository.PedidoRepository;
import com.dm3.security.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class ItemDoPedidoService {

    @Autowired
    private ItemDoPedidoRepository repository;

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Transactional
    public ItemDoPedido adicionarItemAoPedido(UUID pedidoId, UUID produtoId, Integer quantidade) {

        Pedido pedido = pedidoRepository.findById(pedidoId)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado! ID: " + pedidoId));
        Produto produto = produtoRepository.findById(produtoId)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado! ID: " + produtoId));
        ItemDoPedido item = new ItemDoPedido(pedido, produto, quantidade, produto.getPreco());
        return repository.save(item);
    }
}
