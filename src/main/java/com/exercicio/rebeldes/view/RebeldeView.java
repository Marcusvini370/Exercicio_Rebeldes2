package com.exercicio.rebeldes.view;

import com.exercicio.rebeldes.central.IC;
import com.exercicio.rebeldes.enums.OrderBy;
import com.exercicio.rebeldes.model.Rebelde;
import com.exercicio.rebeldes.enums.MenuEnum;
import com.exercicio.rebeldes.enums.RacaEnum;
import com.exercicio.rebeldes.utils.RebeldesOrdenacao;
import com.exercicio.rebeldes.utils.ValidadorCampo;
import com.exercicio.rebeldes.utils.ValidadorCampoNumerico;
import com.exercicio.rebeldes.utils.ValidadorCampoString;
import lombok.Cleanup;

import javax.swing.*;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.InputMismatchException;

public class RebeldeView {

    private IC inteligenciaCentral = new IC();
    private Rebelde rebelde;
    private String nome;
    private Integer idade;
    private RacaEnum raca;
    private OrderBy orderBy;

    private void askNome() {
        this.nome = JOptionPane.showInputDialog("Digite o seu nome:");

        new ValidadorCampoString(nome, this::askNome).validar();
    }

    private void askIdade() {
        this.idade = Integer.parseInt(JOptionPane.showInputDialog("Qual a idade do Rebelde?"));

        new ValidadorCampoNumerico(idade, this::askIdade).validar();

    }

    private void askRaca() {

        RacaEnum[] racas = RacaEnum.values();

        this.raca = (RacaEnum) JOptionPane.showInputDialog(null, "Escolha um", "Race",
                JOptionPane.QUESTION_MESSAGE, null, racas, 3);

        new ValidadorCampo<RacaEnum>(raca, this::askRaca).validar("Uma das raças deve ser escolhida!");
    }

    private void obtemDadosRebelde() {
        askNome();
        askIdade();
        askRaca();

        this.rebelde = Rebelde.builder()
                .nome(this.nome)
                .idade(this.idade)
                .raca(this.raca)
                .build();
    }

    private void solicitaIngressoIC() {
        boolean ingressou = this.inteligenciaCentral.solicitarIngressoDeRebelde(this.rebelde);

        if (ingressou) {
            JOptionPane.showMessageDialog(null, "Rebelde '" + rebelde.getNome() + "' ingressou na Aliança!");
        } else {
            JOptionPane.showMessageDialog(null, "Rebelde '" + rebelde.getNome() + "' recusado!");
        }
    }

    private void exibirRebeldesIC() {
        StringBuilder reb = new StringBuilder();
        reb.append("Lista de rebeldes:\n");
        for (Rebelde rebelde : this.inteligenciaCentral.getRebeldes()) {
            reb.append(rebelde.toString());
            reb.append("\n");
        }

        JOptionPane.showMessageDialog(null, reb);
    }

    private void askOrdenacao() {
        OrderBy[] orders = OrderBy.values();
        this.orderBy = (OrderBy) JOptionPane.showInputDialog(null, "Escolha a ordem", "Ordenação",
                JOptionPane.QUESTION_MESSAGE, null, orders, 3);

        new ValidadorCampo<OrderBy>(orderBy, this::askOrdenacao).validar("Uma das ordenações vem ser escolhidas");
    }

    private void exibiListaEgeraRelatorio() {
        try {
            askOrdenacao();
        } catch (InputMismatchException e) {
            JOptionPane.showMessageDialog(null, "Dados incorretos! Tente novamente!");
            return;
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        RebeldesOrdenacao rebeldesOrdenacao = new RebeldesOrdenacao();
        for (Rebelde rebel :
                this.inteligenciaCentral.getRebeldes()) {
            rebeldesOrdenacao.push(rebel);
        }

        //usado para ordenar
        rebeldesOrdenacao.setOrderBy(orderBy.name());

        // Merge Sort Algoritimo
        rebeldesOrdenacao.mergeSort();

        //lista de rebeldes ordenada
        StringBuilder reb = new StringBuilder();
        reb.append("Lista de rebeldes:\n");
        reb.append(rebeldesOrdenacao.toString());
        JOptionPane.showMessageDialog(null, reb);

        //relatorio ordenado
        @Cleanup PrintWriter writer = null;
        try {
            writer = new PrintWriter("arquivo.txt", "UTF-8");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    private void gerarRelatorioRebeldesIC() {
        try {
            this.inteligenciaCentral.gerarRelatorioDeRebeldes();
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public void menuRebelde() {

        MenuEnum[] menu = MenuEnum.values();

        boolean status = true;
        do {
            MenuEnum menuEscolhido = (MenuEnum) JOptionPane.showInputDialog(null, "Escolha um", "Race",
                    JOptionPane.QUESTION_MESSAGE, null, menu, MenuEnum.SOLICITAR_INGRESSO);

            switch (menuEscolhido != null ? menuEscolhido : MenuEnum.SAIR) {
                case SOLICITAR_INGRESSO:
                    obtemDadosRebelde();
                    solicitaIngressoIC();

                    break;
                case EXIBIR_LISTA:
                    exibirRebeldesIC();

                    break;
                case GERAR_RELATORIO:
                    gerarRelatorioRebeldesIC();

                    break;
                case LISTA_E_RELATIORIO_ORDENADO:
                    exibiListaEgeraRelatorio();
                    break;

                case SAIR:
                    status = false;
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Ação inválida");
            }
        } while (status);

    }

}
