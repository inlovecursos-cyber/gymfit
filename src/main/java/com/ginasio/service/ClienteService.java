package com.ginasio.service;

import com.ginasio.model.Cliente;
import com.ginasio.repository.ClienteRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class ClienteService {

    @Inject
    private ClienteRepository repository;

    public List<Cliente> listarTodos() {
        return repository.listarTodos();
    }

    public Cliente procurarPorId(Long id) {
        return repository.procurarPorId(id);
    }

    public Cliente criar(Cliente cliente) {

        // Lógica de negócio:
        // se o cliente não indicar um objetivo,
        // colocamos automaticamente "Manutenção".
        if (cliente.getObjetivo() == null ||
                cliente.getObjetivo().isBlank()) {

            cliente.setObjetivo("Manutenção");
        }

        return repository.guardar(cliente);
    }

    public Cliente atualizar(Long id, Cliente cliente) {

        if (cliente.getObjetivo() == null ||
                cliente.getObjetivo().isBlank()) {

            cliente.setObjetivo("Manutenção");
        }

        return repository.atualizar(id, cliente);
    }

    public boolean apagar(Long id) {
        return repository.apagar(id);
    }
}