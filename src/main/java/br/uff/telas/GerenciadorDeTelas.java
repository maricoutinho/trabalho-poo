package br.uff.telas;

import br.uff.sistema.Sistema;
import br.uff.usuario.Aluno;

import javax.swing.*;
import java.awt.*;

public class GerenciadorDeTelas extends JFrame {
    private static GerenciadorDeTelas instance;
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private LoginPanel loginPanel;
    private CadastroPanel cadastroPanel;
    private AlunoPanel alunoPanel;

    private GerenciadorDeTelas() {
        Sistema sistema = new Sistema();

        setTitle("Login e Cadastro");
        setSize(300, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        loginPanel = new LoginPanel(sistema);
        cadastroPanel = new CadastroPanel(sistema);
        alunoPanel = new AlunoPanel(sistema);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        mainPanel.add(loginPanel, "Login");
        mainPanel.add(cadastroPanel, "Cadastro");
        mainPanel.add(alunoPanel, "Aluno");


        add(mainPanel);
    }

    public static GerenciadorDeTelas getInstance() {
        if (instance == null) {
            instance = new GerenciadorDeTelas();
        }
        return instance;
    }

    public void mostrarTela(String nomeTela) {
        cardLayout.show(mainPanel, nomeTela);
    }

    public AlunoPanel getAlunoPanel() {
        return alunoPanel;
    }
}
