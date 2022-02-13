package com.exercicio.rebeldes.model;

import com.exercicio.rebeldes.enums.RacaEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class Rebelde {

    @NotBlank
    private String nome;

    @NotNull
    private Integer idade;

    @NotNull
    private RacaEnum raca;

}
