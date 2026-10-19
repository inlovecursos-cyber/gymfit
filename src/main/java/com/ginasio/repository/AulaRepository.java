package com.ginasio.repository;

import com.ginasio.model.Aula;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class AulaRepository {

    private final List<Aula> aulas = new ArrayList<>();

    private Long proximoId = 1L;

    public AulaRepository() {

        aulas.add(new Aula(
                proximoId++,
                "Musculação",
                "08:00",
                "Carlos",
                10
        ));

        aulas.add(new Aula(
                proximoId++,
                "Yoga",
                "10:00",
                "Ana",
                8
        ));

        aulas.add(new Aula(
                proximoId++,
                "Cycling",
                "18:00",
                "Pedro",
                12
        ));

        aulas.add(new Aula(
                proximoId++,
                "HIIT",
                "19:30",
                "Marta",
                10
        ));
    }

    public List<Aula> listarTodas() {
        return aulas;
    }

    public Aula procurarPorId(Long id) {

        for (Aula aula : aulas) {

            if (aula.getId().equals(id)) {
                return aula;
            }
        }

        return null;
    }
}