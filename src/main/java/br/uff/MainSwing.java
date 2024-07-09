package br.uff;

import br.uff.telas.QuizApp;

import javax.swing.*;

public class MainSwing {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> QuizApp.getInstance().setVisible(true));
    }
}