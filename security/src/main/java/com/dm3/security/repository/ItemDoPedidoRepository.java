package com.dm3.security.repository;

import com.dm3.security.entity.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ItemDoPedidoRepository extends JpaRepository<Categoria, UUID> {
}
