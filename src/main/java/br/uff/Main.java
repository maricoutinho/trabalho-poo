package br.uff;

import br.uff.telas.GerenciadorDeTelas;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> GerenciadorDeTelas.getInstance().setVisible(true));
    }
}