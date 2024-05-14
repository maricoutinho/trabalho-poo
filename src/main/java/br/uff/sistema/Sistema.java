package br.uff.sistema;

import java.util.StringJoiner;

public class Sistema {

    private ServicoUsuario servicoUsuario;
    private ServicoQuiz servicoQuiz;

    public Sistema() {
        this.servicoUsuario = new ServicoUsuario();
        this.servicoQuiz = new ServicoQuiz();
    }

    public String exibeDados() {
        StringJoiner joiner = new StringJoiner("\n");

        return joiner.add("====================================================").
                add("                  Dados do Sistema                  ").
                add("====================================================").
                add(servicoQuiz.exibeDados()).
                add("====================================================").
//                add(servicoUsuario.exibeDados()).
//                add("===================================================="). faz sentido exibir esse dado para o prof?
                toString();
    }

    public ServicoUsuario getServicoUsuario() {
        return servicoUsuario;
    }

    public void setServicoUsuario(ServicoUsuario servicoUsuario) {
        this.servicoUsuario = servicoUsuario;
    }

    public ServicoQuiz getServicoQuiz() {
        return servicoQuiz;
    }

    public void setServicoQuiz(ServicoQuiz servicoQuiz) {
        this.servicoQuiz = servicoQuiz;
    }
}
