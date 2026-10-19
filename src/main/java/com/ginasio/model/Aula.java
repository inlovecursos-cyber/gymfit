package com.ginasio.model;

public class Aula {

    private Long id;
    private String nome;
    private String horario;
    private String instrutor;
    private int capacidade;

    public Aula() {
    }

    public Aula(Long id, String nome, String horario, String instrutor, int capacidade) {
        this.id = id;
        this.nome = nome;
        this.horario = horario;
        this.instrutor = instrutor;
        this.capacidade = capacidade;
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

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public String getInstrutor() {
        return instrutor;
    }

    public void setInstrutor(String instrutor) {
        this.instrutor = instrutor;
    }

    public int getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(int capacidade) {
        this.capacidade = capacidade;
    }
}