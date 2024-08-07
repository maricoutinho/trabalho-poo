package br.uff.sistema;

import br.uff.usuario.Aluno;
import br.uff.usuario.Usuario;

import java.util.StringJoiner;

public class Sistema {

    private ServicoUsuario servicoUsuario;
    private ServicoQuiz servicoQuiz;

    public Sistema() {
        this.servicoUsuario = new ServicoUsuario();
        this.servicoQuiz = new ServicoQuiz();
    }

    public Usuario trataUsuario() {
        return servicoUsuario.trataUsuario();
    }

    public void salvaUsuario(Usuario usuario) {
        servicoUsuario.salvaUsuario(usuario);
    }

    public Usuario logaUsuario(String login, String senha) {
        return servicoUsuario.logaUsuario(login, senha);
    }

    public void adicionaNivel() {
        servicoQuiz.adicionarNivel();
    }

    public void comecaQuiz(Aluno aluno) {
        getServicoQuiz().comecaQuiz(aluno);
    }

    public String exibeDados() {
        StringJoiner joiner = new StringJoiner("\n");

        return joiner.add("====================================================").
                add("                  Dados do Sistema                  ").
                add("====================================================").
                add(servicoQuiz.exibirDados()).
                add("====================================================").
//                add(servicoUsuario.exibirDados()).
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
