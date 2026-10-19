package com.ginasio.repository;

import com.ginasio.model.Cliente;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class ClienteRepository {

    private final List<Cliente> clientes = new ArrayList<>();

    private Long proximoId = 1L;

    public ClienteRepository() {

        clientes.add(new Cliente(
                proximoId++,
                "João Silva",
                "joao@gmail.com",
                "912345678",
                "Perder peso"
        ));

        clientes.add(new Cliente(
                proximoId++,
                "Maria Santos",
                "maria@gmail.com",
                "913456789",
                "Ganhar massa muscular"
        ));
    }

    public List<Cliente> listarTodos() {
        return clientes;
    }

    public Cliente procurarPorId(Long id) {

        for (Cliente cliente : clientes) {

            if (cliente.getId().equals(id)) {
                return cliente;
            }
        }

        return null;
    }

    public Cliente guardar(Cliente cliente) {

        cliente.setId(proximoId++);

        clientes.add(cliente);

        return cliente;
    }

    public Cliente atualizar(Long id, Cliente clienteAtualizado) {

        Cliente cliente = procurarPorId(id);

        if (cliente == null) {
            return null;
        }

        cliente.setNome(clienteAtualizado.getNome());
        cliente.setEmail(clienteAtualizado.getEmail());
        cliente.setTelefone(clienteAtualizado.getTelefone());
        cliente.setObjetivo(clienteAtualizado.getObjetivo());

        return cliente;
    }

    public boolean apagar(Long id) {

        Cliente cliente = procurarPorId(id);

        if (cliente == null) {
            return false;
        }

        clientes.remove(cliente);

        return true;
    }
}