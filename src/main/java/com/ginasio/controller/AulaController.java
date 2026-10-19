package com.ginasio.controller;

import com.ginasio.model.Aula;
import com.ginasio.service.AulaService;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/aulas")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AulaController {

    @Inject
    private AulaService service;

    @GET
    public List<Aula> listarAulas() {
        return service.listarTodas();
    }

    @GET
    @Path("/{id}")
    public Response procurarAula(@PathParam("id") Long id) {

        Aula aula = service.procurarPorId(id);

        if (aula == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.ok(aula).build();
    }
}