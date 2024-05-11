package br.uff.usuario;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class Aluno extends Usuario {

    private int nivel;
    private List<PerfomanceNivel> performance;

    public Aluno(String login, String senha, int nivel) {
        super(login, senha);
        this.nivel = nivel;
    }

    public Aluno(String login, String senha, int nivel, List<PerfomanceNivel> performance) {
        this(login, senha, nivel);
        this.performance = performance;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public List<PerfomanceNivel> getPerformance() {
        return performance;
    }

    public void setPerformance(List<PerfomanceNivel> performance) {
        this.performance = performance;
    }

    public void addPerformance(PerfomanceNivel performance) {
        if (this.performance == null) {
            this.performance = new ArrayList<>();
        }
        this.performance.add(performance);
    }

    public String toStringPerformance() {
        StringJoiner joiner = new StringJoiner("\n");

        joiner.add("==================").
                add("Progresso").
                add("==================");

        if (performance == null || performance.isEmpty()) {
            return joiner.add("Concluído: 0%").
                    add("------------------").
                    add("==================").toString();
        }

        for (int i = 0; i < performance.size(); i++) {
            joiner.add("Nível " + (i + 1)).
                    add(performance.get(i).toString()).
                    add("------------------");
        }

        return joiner.add("==================").toString();
    }

    public void sobeNivel() {
        this.nivel += 1;
    }

    @Override
    public String toString() {
        StringJoiner joiner = new StringJoiner("\n");

        joiner.add("==================").
                add("------------------").
                add("Usuário: " + getLogin()).
                add(toStringPerformance());

        return joiner.toString();
    }
}