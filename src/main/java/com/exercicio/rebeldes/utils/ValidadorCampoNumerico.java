package com.exercicio.rebeldes.utils;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ValidadorCampoNumerico extends ValidadorCampo<Integer> {

    @Override
    public boolean validarCampo() {
        return super.validarCampo() && this.getValor() > 0;
    }

    @Override
    public String mensagemErroPadrao() {
        return "Campo numerico invalido, deve ser maior que 0.";
    }

    public ValidadorCampoNumerico(Integer valor, Runnable onInvalid) {
        super(valor, onInvalid);
    }
    
}
