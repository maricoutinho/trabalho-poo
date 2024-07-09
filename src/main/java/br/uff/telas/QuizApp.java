package br.uff.telas;

import br.uff.sistema.Sistema;

import javax.swing.*;
import java.awt.*;

public class QuizApp extends JFrame {
    private static QuizApp instance;

    private CardLayout cardLayout;
    private JPanel mainPanel;
    private LoginPanel loginPanel;
    private CadastroPanel cadastroPanel;
    private AlunoPanel alunoPanel;
    private ProfessorPanel professorPanel;

    private QuizApp() {
        Sistema sistema = new Sistema();

        setTitle("Quiz App");
        setSize(600, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        loginPanel = new LoginPanel(sistema);
        cadastroPanel = new CadastroPanel(sistema);
        alunoPanel = new AlunoPanel(sistema);
        professorPanel = new ProfessorPanel(sistema);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        mainPanel.add(loginPanel, "Login");
        mainPanel.add(cadastroPanel, "Cadastro");
        mainPanel.add(alunoPanel, "Aluno");
        mainPanel.add(professorPanel, "Professor");

        add(mainPanel);
    }

    public static QuizApp getInstance() {
        if (instance == null) {
            instance = new QuizApp();
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
