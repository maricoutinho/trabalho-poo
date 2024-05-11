package br.uff.quiz;

import java.util.List;

public class Nivel {

    private int id;
    private List<Pergunta> perguntas;

    public Nivel(int id, List<Pergunta> perguntas) {
        this.id = id;
        this.perguntas = perguntas;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Pergunta> getPerguntas() {
        return perguntas;
    }

    public void setPerguntas(List<Pergunta> perguntas) {
        this.perguntas = perguntas;
    }
}
