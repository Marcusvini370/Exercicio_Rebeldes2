package com.exercicio.rebeldes.central;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.exercicio.rebeldes.model.Rebelde;
import lombok.Cleanup;
import lombok.Getter;

public class IC {

    @Getter
    List<Rebelde> rebeldes;

    public IC() {
        this.rebeldes = new ArrayList<>();
    }

    public boolean solicitarIngressoDeRebelde(Rebelde rebelde) {
        int numberRebelde = new Random().nextInt(20);

        if (numberRebelde <= 10) {
            this.rebeldes.add(rebelde);
            return true;
        } else {
            return false;
        }
    }

    public void gerarRelatorioDeRebeldes() throws FileNotFoundException, UnsupportedEncodingException {
        @Cleanup
        PrintWriter writer = new PrintWriter("rebeldes.txt", "UTF-8");
        writer.println("Lista de Rebeldes:");
        for (Rebelde rebelde : this.rebeldes) {
            writer.println(rebelde.toString());
        }
    }

}
