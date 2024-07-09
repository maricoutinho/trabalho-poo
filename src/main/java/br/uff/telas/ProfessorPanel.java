package br.uff.telas;

import br.uff.sistema.Sistema;

import javax.swing.*;
import java.awt.*;

public class ProfessorPanel extends JPanel {

    public ProfessorPanel(Sistema sistema) {
        setLayout(new BorderLayout());

        JLabel mensagemLabel = new JLabel("Página em Construção", JLabel.CENTER);
        mensagemLabel.setFont(new Font("Arial", Font.BOLD, 24));
        mensagemLabel.setForeground(Color.RED);

        add(mensagemLabel, BorderLayout.CENTER);
    }
}
