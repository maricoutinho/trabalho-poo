package br.uff.telas;

import br.uff.quiz.Nivel;
import br.uff.sistema.Sistema;
import br.uff.usuario.Aluno;
import br.uff.usuario.PerfomanceNivel;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class AlunoPanel extends JPanel {
    private JLabel saudacaoLabel;
    private JLabel nivelAtualLabel;
    private JTextArea historicoArea;
    private JButton quizButton;
    private JButton sairButton;

    private Aluno aluno;

    public AlunoPanel(Sistema sistema) {
        saudacaoLabel = new JLabel();
        nivelAtualLabel = new JLabel();
        historicoArea = new JTextArea(5, 20);
        historicoArea.setEditable(false);
        quizButton = new JButton("Começar Quiz");
        sairButton = new JButton("Sair");

        setLayout(new BorderLayout());
        JPanel topPanel = new JPanel(new GridLayout(2, 1));
        topPanel.add(saudacaoLabel);
        topPanel.add(nivelAtualLabel);
        add(topPanel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.add(new JLabel("Histórico:"), BorderLayout.NORTH);
        centerPanel.add(new JScrollPane(historicoArea), BorderLayout.CENTER);
        add(centerPanel, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel();
        bottomPanel.add(quizButton);
        bottomPanel.add(sairButton);
        add(bottomPanel, BorderLayout.SOUTH);

        quizButton.addActionListener(e -> {
            List<Nivel> niveis = sistema.getServicoQuiz().getNiveis();

            if (niveis == null || niveis.isEmpty()) {
                JOptionPane.showMessageDialog(AlunoPanel.this,
                        "Poxa, ainda não existem niveis cadastradas no sistema.",
                        "Erro",
                        JOptionPane.ERROR_MESSAGE);
                System.exit(0);
            }

            if (aluno.getNivel() == niveis.size()+1) {
                JOptionPane.showMessageDialog(AlunoPanel.this,
                        "Você já chegou no nível máximo do quiz.",
                        "Erro",
                        JOptionPane.ERROR_MESSAGE);
                System.exit(0);
            }

            QuizPanel quizPanel = new QuizPanel(aluno, niveis);
            JOptionPane.showMessageDialog(AlunoPanel.this, quizPanel, "Quiz", JOptionPane.PLAIN_MESSAGE);
        });

        sairButton.addActionListener(e -> {
            sistema.salvaUsuario(aluno);
            System.exit(0);
        });
    }

    public void exibeHistorico() {
        saudacaoLabel.setText("Olá, " + aluno.getLogin() + "!");
        nivelAtualLabel.setText("Nível Atual: " + aluno.getNivel());
        historicoArea.setText(aluno.toStringPerformance());
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }
}
