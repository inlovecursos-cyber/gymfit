package com.ginasio.model;

public class Agendamento {

    private Long id;
    private String nome;
    private String atividade;
    private String data;
    private String hora;

    public Agendamento() {
    }

    public Agendamento(Long id, String nome, String atividade,
                       String data, String hora) {
        this.id = id;
        this.nome = nome;
        this.atividade = atividade;
        this.data = data;
        this.hora = hora;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getAtividade() {
        return atividade;
    }

    public void setAtividade(String atividade) {
        this.atividade = atividade;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }
}