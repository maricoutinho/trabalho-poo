package br.uff.questionario;

import br.uff.arquivo.PerguntasUtil;
import br.uff.arquivo.UsuarioUtil;
import br.uff.usuario.Aluno;
import br.uff.usuario.Professor;
import br.uff.usuario.Usuario;

public class Sistema {

    private Nivel[] niveis;
    private Usuario[] usuarios;

    public Sistema() {
        this.niveis = PerguntasUtil.lerArquivo();
        this.usuarios = UsuarioUtil.lerArquivos();
    }

    public Nivel[] getNiveis() {
        return niveis;
    }

    public void setNiveis(Nivel[] fases) {
        this.niveis = fases;
    }

    public Usuario[] getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(Usuario[] usuarios) {
        this.usuarios = usuarios;
    }

    public Usuario logaUsuario(String login, String senha) {
        for (Usuario usuario : usuarios) {
            if (usuario.getLogin().equals(login) && usuario.getSenha().equals(senha)) {
                return usuario;
            }
        }
        return null;
    }

    private void adicionaUsuario(Usuario usuario) {
        for (int i = 0; i < usuarios.length; i++) {
            if (usuarios[i] == null) {
                usuarios[i] = usuario;
            }
        }
    }

    public void criaAluno(String login, String senha, int nivel) {
        adicionaUsuario(new Aluno(login, senha, nivel));
    }

    public void criaProfessor(String login, String senha) {
        adicionaUsuario(new Professor(login, senha));
    }
}
