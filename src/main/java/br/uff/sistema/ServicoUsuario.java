package br.uff.sistema;

import br.uff.repositorio.UsuarioRepo;
import br.uff.usuario.Usuario;

import java.util.List;

public class ServicoUsuario {

    private List<Usuario> usuarios;

    public ServicoUsuario() {
        this.usuarios = UsuarioRepo.lerArquivos();
    }

    public Usuario logaUsuario(String login, String senha) {
        for (Usuario usuario : usuarios) {
            if (usuario.getLogin().equals(login) && usuario.getSenha().equals(senha)) {
                return usuario;
            }
        }
        return null;
    }

    public void salvaUsuario(Usuario usuario) {
        UsuarioRepo.salvarUsuario(usuario);
    }

    public String exibirDados() {
        return "Usuários: Existe(m) [" + usuarios.size() + "] cadastrado(s) no sistema.";
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }
}
