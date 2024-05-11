package br.uff.usuario;

import java.util.ArrayList;
import java.util.List;

public class PerfomanceNivel {
    private List<Integer> perguntasCorretas;
    private String porcentagemConclusao;

    public PerfomanceNivel() {
        this.perguntasCorretas = new ArrayList<>();
    }

    public List<Integer> getPerguntasCorretas() {
        return perguntasCorretas;
    }

    public void setPerguntasCorretas(List<Integer> perguntasCorretas) {
        this.perguntasCorretas = perguntasCorretas;
    }

    public String getPorcentagemConclusao() {
        return porcentagemConclusao;
    }

    public void setPorcentagemConclusao(String porcentagemConclusao) {
        this.porcentagemConclusao = porcentagemConclusao;
    }

    private String perguntasCorretasToString() {
        String perguntasCorretasString = "";
        for (int i = 0; i < perguntasCorretas.size(); i++) {
            perguntasCorretasString += perguntasCorretas.get(i);

            if (i != perguntasCorretas.size() - 1) {
                perguntasCorretasString += ",";
            }
        }
        return perguntasCorretasString;
    }

    @Override
    public String toString() {
        return "Respondidas corretamente: " + perguntasCorretasToString() + "\n"+
                "Concluido: " + porcentagemConclusao + "%";
    }
}