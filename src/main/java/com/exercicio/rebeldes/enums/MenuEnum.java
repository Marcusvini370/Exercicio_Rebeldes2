package com.exercicio.rebeldes.enums;

import lombok.Getter;

@Getter
public enum MenuEnum {

    SOLICITAR_INGRESSO("Solicitar Ingresso na Aliança"),
    EXIBIR_LISTA("Exibir Lista de Rebeldes"),
    GERAR_RELATORIO("Gerar Relatório de Rebeldes"),
    LISTA_E_RELATIORIO_ORDENADO("Exibir lista de Rebelde por ordem"),
    SAIR("Sair");

    private String menu;

    MenuEnum(String string) {
        this.menu = string;
    }
}
