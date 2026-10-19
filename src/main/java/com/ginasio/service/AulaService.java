package com.ginasio.service;

import com.ginasio.model.Aula;
import com.ginasio.repository.AulaRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class AulaService {

    @Inject
    private AulaRepository repository;

    public List<Aula> listarTodas() {
        return repository.listarTodas();
    }

    public Aula procurarPorId(Long id) {
        return repository.procurarPorId(id);
    }
}