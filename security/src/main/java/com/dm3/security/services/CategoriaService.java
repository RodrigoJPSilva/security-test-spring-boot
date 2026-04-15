package com.dm3.security.services;

import java.util.List;

import com.dm3.security.entity.Categoria;
import com.dm3.security.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository repository; // <-- A correção está aqui!

    public List<Categoria> findAll() {
        return repository.findAll();
    }

    public Categoria insert(String nome) {
        Categoria cat = new Categoria();
        cat.setNome(nome);
        return repository.save(cat);
    }
}