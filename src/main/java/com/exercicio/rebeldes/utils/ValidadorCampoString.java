package com.exercicio.rebeldes.utils;

import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

@NoArgsConstructor
public class ValidadorCampoString extends ValidadorCampo<String> {

    @Override
    public boolean validarCampo() {
        return super.validarCampo() && StringUtils.isNotBlank(this.getValor());
    }

    @Override
    public String mensagemErroPadrao() {
        return "Campo de texto invalido, não pode ser vazio.";
    }

    public ValidadorCampoString(String valor, Runnable onInvalid) {
        super(valor, onInvalid);
    }
    
}
