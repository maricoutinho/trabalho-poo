package br.uff.usuario;

public class Aluno extends Usuario {

    private int nivel;

    public Aluno(String login, String senha, int nivel) {
        super(login, senha);
        this.nivel = nivel;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    @Override
    public String toString() {
        return "Usuário: " + getLogin() + "\n" + "Nível:" + getNivel();
    }
}