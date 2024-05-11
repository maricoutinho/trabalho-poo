package br.uff.usuario;

import java.util.ArrayList;
import java.util.List;

public class Aluno extends Usuario {

    private int nivel;
    private List<PerfomanceNivel> perfomance;

    public Aluno(String login, String senha, int nivel, List<PerfomanceNivel> perfomances) {
        super(login, senha);
        this.nivel = nivel;
        this.perfomance = perfomances;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public List<PerfomanceNivel> getPerfomance() {
        return perfomance;
    }

    public void setPerfomance(List<PerfomanceNivel> perfomance) {
        this.perfomance = perfomance;
    }

    private String toStringPerformance() {
        String perf = "";
        for (int i = 0; i < perfomance.size(); i++) {
            perf += "Nivel " + (i+1) + "\n" + perfomance.get(i).toString()+ "\n";
        }
        return perf;
    }

    @Override
    public String toString() {
        return "Usuário: " + getLogin() + "\n" +
                "Nível:" + getNivel() + "\n" + toStringPerformance();
    }
}