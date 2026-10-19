package com.ginasio.controller;

import com.ginasio.model.Cliente;
import com.ginasio.service.ClienteService;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/clientes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ClienteController {

    @Inject
    private ClienteService service;

    @GET
    public List<Cliente> listarClientes() {
        return service.listarTodos();
    }

    @GET
    @Path("/{id}")
    public Response procurarCliente(@PathParam("id") Long id) {

        Cliente cliente = service.procurarPorId(id);

        if (cliente == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.ok(cliente).build();
    }

    @POST
    public Response criarCliente(Cliente cliente) {

        Cliente novoCliente = service.criar(cliente);

        return Response
                .status(Response.Status.CREATED)
                .entity(novoCliente)
                .build();
    }

    @PUT
    @Path("/{id}")
    public Response atualizarCliente(
            @PathParam("id") Long id,
            Cliente cliente) {

        Cliente atualizado = service.atualizar(id, cliente);

        if (atualizado == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.ok(atualizado).build();
    }

    @DELETE
    @Path("/{id}")
    public Response apagarCliente(@PathParam("id") Long id) {

        boolean apagado = service.apagar(id);

        if (!apagado) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.noContent().build();
    }
}