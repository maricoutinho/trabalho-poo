package br.uff.telas;

import br.uff.quiz.Nivel;
import br.uff.quiz.Pergunta;
import br.uff.usuario.Aluno;
import br.uff.usuario.PerfomanceNivel;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class QuizPanel extends JPanel {
    private Aluno aluno;

    private List<Nivel> niveis;
    private int indiceNivelAtual;
    private List<Pergunta> perguntas;
    private int indicePerguntaAtual;

    private JLabel textoLabel;
    private JLabel perguntaLabel;

    private JRadioButton opcaoARadioButton;
    private JRadioButton opcaoBRadioButton;
    private JRadioButton opcaoCRadioButton;
    private JRadioButton opcaoDRadioButton;

    private JButton responderButton;

    public QuizPanel(Aluno aluno, List<Nivel> niveis) {
        this.aluno = aluno;
        this.niveis = niveis;
        this.indiceNivelAtual = aluno.getNivel() - 1; // inicia no nível atual do aluno

        setLayout(new BorderLayout());
        inicializarComponentes();
        iniciarNivel();
    }

    private void inicializarComponentes() {
        JPanel textoPanel = new JPanel(new BorderLayout());
        textoLabel = new JLabel();
        textoPanel.add(textoLabel, BorderLayout.CENTER);
        add(textoPanel, BorderLayout.NORTH);

        JPanel perguntaPanel = new JPanel(new BorderLayout());
        perguntaLabel = new JLabel();
        perguntaPanel.add(perguntaLabel, BorderLayout.NORTH);

        JPanel opcoesPanel = new JPanel(new GridLayout(4, 1));
        opcaoARadioButton = new JRadioButton("A");
        opcaoBRadioButton = new JRadioButton("B");
        opcaoCRadioButton = new JRadioButton("C");
        opcaoDRadioButton = new JRadioButton("D");
        ButtonGroup opcoesGroup = new ButtonGroup();
        opcoesGroup.add(opcaoARadioButton);
        opcoesGroup.add(opcaoBRadioButton);
        opcoesGroup.add(opcaoCRadioButton);
        opcoesGroup.add(opcaoDRadioButton);
        opcoesPanel.add(opcaoARadioButton);
        opcoesPanel.add(opcaoBRadioButton);
        opcoesPanel.add(opcaoCRadioButton);
        opcoesPanel.add(opcaoDRadioButton);
        perguntaPanel.add(opcoesPanel, BorderLayout.CENTER);

        add(perguntaPanel, BorderLayout.CENTER);

        responderButton = new JButton("Responder");
        add(responderButton, BorderLayout.SOUTH);
        responderButton.addActionListener(e -> verificarResposta());
    }

    private void iniciarNivel() {
        if (indiceNivelAtual >= niveis.size()) {
            JOptionPane.showMessageDialog(this, "Você completou todos os níveis disponíveis.", "Fim dos Níveis", JOptionPane.INFORMATION_MESSAGE);
            QuizApp.getInstance().mostrarTela("Aluno");
            return;
        }

        Nivel nivel = niveis.get(indiceNivelAtual);
        JOptionPane.showMessageDialog(null, "Iniciando Nível " + nivel.getId(), "Início do Nível", JOptionPane.INFORMATION_MESSAGE);

        PerfomanceNivel performance = getPerfomanceNivel();

        perguntas = nivel.getPerguntas();
        indicePerguntaAtual = 0;
        mostrarPergunta(performance);
    }

    private PerfomanceNivel getPerfomanceNivel() {
        PerfomanceNivel performance;
        if (aluno.getPerformance() == null || aluno.getPerformance().size() <= indiceNivelAtual) {
            performance = new PerfomanceNivel(); // se nao existir performance para aquele nivel, adiciona a referencia
            aluno.addPerformance(performance);
        } else {
            performance = aluno.getPerformance().get(indiceNivelAtual); // pega performance referente ao nivel caso o aluno tenha
        }
        return performance;
    }

    private void mostrarPergunta(PerfomanceNivel performance) {
        if (performance.getPerguntasCorretas().contains(indicePerguntaAtual)) { // se a pergunta já foi respondida corretamente, passa para a proxima
            proximaPergunta(performance);
            return;
        }

        Pergunta perguntaAtual = perguntas.get(indicePerguntaAtual);

        textoLabel.setText((indicePerguntaAtual + 1) + ". " + perguntaAtual.getTexto());
        perguntaLabel.setText(perguntaAtual.getPergunta());
        opcaoARadioButton.setText(perguntaAtual.getOpcoes().get(0));
        opcaoBRadioButton.setText(perguntaAtual.getOpcoes().get(1));
        opcaoCRadioButton.setText(perguntaAtual.getOpcoes().get(2));
        opcaoDRadioButton.setText(perguntaAtual.getOpcoes().get(3));

        opcaoARadioButton.setSelected(false);
        opcaoBRadioButton.setSelected(false);
        opcaoCRadioButton.setSelected(false);
        opcaoDRadioButton.setSelected(false);
    }

    private void verificarResposta() {
        Pergunta perguntaAtual = perguntas.get(indicePerguntaAtual);
        String respostaUsuario = getRespostaUsuario();
        PerfomanceNivel performance = aluno.getPerformance().get(indiceNivelAtual);

        if (respostaUsuario.equals(perguntaAtual.getResposta())) {
            performance.getPerguntasCorretas().add(indicePerguntaAtual);
            JOptionPane.showMessageDialog(this, "Resposta correta!");
        } else {
            JOptionPane.showMessageDialog(this, "Resposta incorreta.");
        }

        proximaPergunta(performance);
    }

    private String getRespostaUsuario() {
        if (opcaoARadioButton.isSelected()) {
            return "A";
        } else if (opcaoBRadioButton.isSelected()) {
            return "B";
        } else if (opcaoCRadioButton.isSelected()) {
            return "C";
        } else if (opcaoDRadioButton.isSelected()) {
            return "D";
        } else {
            return "";
        }
    }

    private void proximaPergunta(PerfomanceNivel performance) {
        indicePerguntaAtual++;

        if (indicePerguntaAtual < perguntas.size()) {
            mostrarPergunta(performance);
        } else {
            finalizarNivel(performance);
        }
    }

    private void finalizarNivel(PerfomanceNivel performance) {
        JOptionPane.showMessageDialog(this, "Nível finalizado!");

        // atualiza performance do usuario
        int corretas = performance.getPerguntasCorretas().size();
        performance.setPorcentagemConclusao((100 * corretas) / perguntas.size() + "%");

        if (corretas == perguntas.size()) {
            aluno.sobeNivel();
            JOptionPane.showMessageDialog(this, "Parabéns! Todas as perguntas foram respondidas corretamente.");

            // Avança para o próximo nível
            indiceNivelAtual++;
            iniciarNivel();
        } else {
            int opcao = JOptionPane.showConfirmDialog(this,
                    "Você acertou " + corretas + "/" + perguntas.size() + ". Deseja retentar o nível?",
                    "Nível não concluído",
                    JOptionPane.YES_NO_OPTION);

            if (opcao == JOptionPane.YES_OPTION) {
                indicePerguntaAtual = 0;
                mostrarPergunta(performance);
            } else {
                QuizApp.getInstance().getAlunoPanel().exibeHistorico();
                QuizApp.getInstance().mostrarTela("Aluno");
            }
        }
    }
}
