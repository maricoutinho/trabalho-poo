package br.uff.sistema;

import br.uff.repositorio.UsuarioRepo;
import br.uff.usuario.Aluno;
import br.uff.usuario.Professor;
import br.uff.usuario.Usuario;

import java.util.List;
import java.util.Scanner;

public class ServicoUsuario {

    private List<Usuario> usuarios;

    public ServicoUsuario() {
        this.usuarios = UsuarioRepo.lerArquivos();
    }

    public Usuario tratarUsuario() {
        Scanner input = new Scanner(System.in);

        System.out.println("Olá, você deseja: ");
        System.out.println("1) Cadastrar usuário");
        System.out.println("2) Fazer login");

        int opcao = input.nextInt();

        System.out.print("Informe o login: ");
        String login = input.next();
        System.out.print("Informe a senha: ");
        String senha = input.next();

        Usuario usuario = null;

        switch (opcao) {
            case 1:
                System.out.println("Você é: ");
                System.out.println("1) Aluno");
                System.out.println("2) Professor");

                int tipo = input.nextInt();

                switch (tipo) {
                    case 1:
                        usuario = new Aluno(login, senha, 1);
                        break;
                    case 2:
                        usuario = new Professor(login, senha);
                        break;
                    default:
                        System.out.println("Opção inválida!");
                        return null;

                }
                break;

            case 2:
                usuario = logarUsuario(login, senha); // valida login e senha
                break;
        }

        return usuario;
    }

    private Usuario logarUsuario(String login, String senha) {
        for (Usuario usuario : usuarios) {
            if (usuario.getLogin().equals(login) && usuario.getSenha().equals(senha)) {
                return usuario;
            }
        }
        return null;
    }

    public void salvarUsuario(Usuario usuario) {
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
