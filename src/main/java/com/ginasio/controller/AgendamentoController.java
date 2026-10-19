package com.ginasio.controller;

import com.ginasio.model.Agendamento;
import com.ginasio.repository.AgendamentoRepository;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/agendamentos")
@RequestScoped
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AgendamentoController {

    @Inject
    private AgendamentoRepository repository;

    @POST
    public Response criar(Agendamento agendamento) {
        try {
            if (agendamento == null
                    || agendamento.getNome() == null
                    || agendamento.getNome().isBlank()
                    || agendamento.getAtividade() == null
                    || agendamento.getData() == null
                    || agendamento.getHora() == null) {

                return Response.status(Response.Status.BAD_REQUEST)
                        .entity("{\"erro\":\"Preenche todos os campos.\"}")
                        .build();
            }

            Agendamento guardado = repository.guardar(agendamento);

            return Response.status(Response.Status.CREATED)
                    .entity(guardado)
                    .build();

        } catch (Exception erro) {
            erro.printStackTrace();

            return Response.serverError()
                    .entity("{\"erro\":\"Não foi possível guardar o agendamento.\"}")
                    .build();
        }
    }

    @GET
    public Response listar() {
        try {
            List<Agendamento> agendamentos = repository.listarTodos();

            return Response.ok(agendamentos).build();

        } catch (Exception erro) {
            erro.printStackTrace();

            return Response.serverError()
                    .entity("{\"erro\":\"Não foi possível consultar os agendamentos.\"}")
                    .build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response eliminar(@PathParam("id") Long id) {
        try {
            boolean eliminado = repository.eliminar(id);

            if (!eliminado) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }

            return Response.noContent().build();

        } catch (Exception erro) {
            erro.printStackTrace();

            return Response.serverError().build();
        }
    }
}