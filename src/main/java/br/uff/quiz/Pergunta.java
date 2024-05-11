package br.uff.quiz;

import java.util.List;
import java.util.StringJoiner;

public class Pergunta {

    private String texto;
    private String pergunta;
    private List<String> opcoes;
    private String resposta;

    public Pergunta(String texto, String pergunta, List<String> opcoes, String resposta) {
        this.texto = texto;
        this.pergunta = pergunta;
        this.opcoes = opcoes;
        this.resposta = resposta;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String getPergunta() {
        return pergunta;
    }

    public void setPergunta(String pergunta) {
        this.pergunta = pergunta;
    }

    public List<String> getOpcoes() {
        return opcoes;
    }

    public void setOpcoes(List<String> opcoes) {
        this.opcoes = opcoes;
    }

    public String getResposta() {
        return resposta;
    }

    public void setResposta(String resposta) {
        this.resposta = resposta;
    }

    private String opcoesToString() {
        StringJoiner joiner = new StringJoiner("\n");

        opcoes.forEach(joiner::add);

        return joiner.toString();
    }

    @Override
    public String toString() {
        StringJoiner joiner = new StringJoiner("\n");

        joiner.add("Texto: " + texto)
                .add("Pergunta: " + pergunta)
                .add(opcoesToString());

        return joiner.toString();
    }
}
