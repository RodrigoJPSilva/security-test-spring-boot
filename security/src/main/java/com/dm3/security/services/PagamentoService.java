package com.dm3.security.services;

import com.dm3.security.entity.Pagamento;
import com.dm3.security.entity.Pedido;
import com.dm3.security.enums.StatusDoPedido;
import com.dm3.security.repository.PagamentoRepository;
import com.dm3.security.repository.PedidoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
public class PagamentoService {

    @Autowired
    private PagamentoRepository repository;

    @Autowired
    private PedidoRepository pedidoRepository;

    public Optional<Pagamento> findById(UUID id) {
        return repository.findById(id);
    }

    @Transactional
    public Pagamento pagarPedido(UUID pedidoId) {
        // 1. Busca o pedido no banco
        Pedido pedido = pedidoRepository.findById(pedidoId)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado! ID: " + pedidoId));

        // 2. Regra de Negócio: Evita pagar um pedido que já tem pagamento
        if (pedido.getPagamento() != null) {
            throw new RuntimeException("Este pedido já encontra-se pago!");
        }

        // 3. Registra o pagamento
        Pagamento pagamento = new Pagamento();
        pagamento.setMomento(Instant.now());
        pagamento.setPedido(pedido);

        // 4. Regra de Negócio: Atualiza o status do Pedido automaticamente
        pedido.setStatus(StatusDoPedido.PAGO);

        // Salva o pagamento (como o Pedido tem CascadeType.ALL, o JPA é inteligente
        // e já atualiza o status do pedido no banco de dados para nós também!)
        return repository.save(pagamento);
    }
}