package com.exercicio.rebeldes.utils;

import javax.swing.JOptionPane;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ValidadorCampo<T> {

    private T valor;
    private Runnable onInvalid;

    public String mensagemErroPadrao() {
        return "Campo invalido, não pode ser null.";
    }

    public boolean validarCampo() {
        return valor != null;
    }

    public final void validar() {
        validar(mensagemErroPadrao());
    }

    public final void validar(String mensagem) {
        if (!validarCampo()) {
            JOptionPane.showMessageDialog(null, mensagem);
            onInvalid.run();
        }
    }

}
