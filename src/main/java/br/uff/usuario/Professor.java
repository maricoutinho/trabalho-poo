package br.uff.usuario;

public class Professor extends Usuario {

    public Professor(String login, String senha) {
        super(login, senha);
    }

    @Override
    public String toString() {
        return "Usuário: " + getLogin();
    }
}
