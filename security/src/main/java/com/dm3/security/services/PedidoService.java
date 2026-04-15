package com.dm3.security.services;

import com.dm3.security.entity.Pedido;
import com.dm3.security.entity.Usuario;
import com.dm3.security.enums.StatusDoPedido;
import com.dm3.security.repository.PedidoRepository;
import com.dm3.security.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository repository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Pedido> findAll() {
        return repository.findAll();
    }

    public Optional<Pedido> findById(UUID id) {
        return repository.findById(id);
    }

    @Transactional
    public Pedido insert(UUID clienteId) {
        Usuario cliente = usuarioRepository.findById(clienteId)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado! ID: " + clienteId));

        Pedido pedido = new Pedido();
        pedido.setMomento(Instant.now());
        pedido.setStatus(StatusDoPedido.AGUARDANDO_PAGAMENTO);
        pedido.setCliente(cliente);

        return repository.save(pedido);
    }

    @Transactional
    public Optional<Pedido> atualizarStatus(UUID id, StatusDoPedido novoStatus) {
        Optional<Pedido> pedidoExistente = repository.findById(id);

        if (pedidoExistente.isPresent()) {
            Pedido pedido = pedidoExistente.get();
            pedido.setStatus(novoStatus);
            return Optional.of(repository.save(pedido));
        }
        return Optional.empty();
    }

    public boolean deleteById(UUID id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }
}