package br.uff.questionario;

public class Nivel {

    private int id;
    private Pergunta[] perguntas;

    public Nivel(int id, Pergunta[] perguntas) {
        this.id = id;
        this.perguntas = perguntas;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Pergunta[] getPerguntas() {
        return perguntas;
    }

    public void setPerguntas(Pergunta[] perguntas) {
        this.perguntas = perguntas;
    }
}
