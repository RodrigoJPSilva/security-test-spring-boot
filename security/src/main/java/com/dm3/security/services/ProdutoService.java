package com.dm3.security.services;

import com.dm3.security.dto.request.DTOProdutoRequest;
import com.dm3.security.entity.Categoria;
import com.dm3.security.entity.Produto;
import com.dm3.security.repository.CategoriaRepository;
import com.dm3.security.repository.ProdutoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository repository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    public List<Produto> findAll() {
        return repository.findAll();
    }

    @Transactional
    public Produto insert(DTOProdutoRequest dto) {
        Produto entity = new Produto();
        entity.setNome(dto.getNome());
        entity.setDescricao(dto.getDescricao());
        entity.setPreco(dto.getPreco());
        entity.setImgUrl(dto.getImgUrl());

        // Vincula as categorias
        for (UUID catId : dto.getCategoriasIds()) {
            Categoria categoria = categoriaRepository.findById(catId)
                    .orElseThrow(() -> new RuntimeException("Categoria não encontrada! ID: " + catId));
            entity.getCategorias().add(categoria);
        }

        return repository.save(entity);
    }
}