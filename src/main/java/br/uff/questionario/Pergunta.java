package br.uff.questionario;

import java.util.Arrays;

public class Pergunta {

    private String texto;
    private String pergunta;
    private String[] opcoes;
    private String resposta;

    public Pergunta(String texto, String pergunta, String[] opcoes, String resposta) {
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

    public String[] getOpcoes() {
        return opcoes;
    }

    public void setOpcoes(String[] opcoes) {
        this.opcoes = opcoes;
    }

    public String getResposta() {
        return resposta;
    }

    public void setResposta(String resposta) {
        this.resposta = resposta;
    }

    private String opcoesToString() {
        String opcoesString = "";

        for (int i = 0; i < opcoes.length; i++) {
            opcoesString += opcoes[i] + "\n";
        }

        return opcoesString;
    }

    @Override
    public String toString() {
        return "Texto: "+ texto + "\n" +
                "Pergunta: "+ pergunta + "\n" +
                opcoesToString();
    }
}
