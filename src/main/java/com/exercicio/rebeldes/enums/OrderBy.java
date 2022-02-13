package com.exercicio.rebeldes.enums;

public enum OrderBy {
    NOME("Nome"),
    IDADE("Idade"),
    RACA("Raça");

    private String description;

    OrderBy(String description) {this.description = description;}

    public String getDescription() { return description; }
}