package br.uff;

import br.uff.repositorio.UsuarioRepo;
import br.uff.sistema.Sistema;
import br.uff.usuario.Aluno;
import br.uff.usuario.Professor;
import br.uff.usuario.Usuario;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Sistema sistema = new Sistema();

        Scanner input = new Scanner(System.in);

        Usuario usuario = sistema.getServicoUsuario().trataUsuario();
        if (usuario == null) {
            System.out.print("Login e/ou senha incorretos: verifique os dados e tente novamente.");
            return;
        }

        if (usuario instanceof Aluno) { // só apresenta o quiz para o aluno
            Aluno aluno = (Aluno) usuario;

            System.out.println("\n" + aluno); // exibe dados do usuário
            System.out.print("\nDeseja começar? (S/N)");

            switch (input.next()) {
                case "s":
                    sistema.getServicoQuiz().comecaQuiz(aluno);
                    break;
                case "n":
                    break;
                default:
                    System.out.print("Opção inválida.");
                    break;
            }

        } else {
            System.out.println("Olá, " + usuario.getLogin());

            boolean continuar = true;

            while (continuar) {
                System.out.println(sistema.exibeDados()); // exibe dados dos níveis
                System.out.print("\nDeseja incluir nível? (S/N)");

                switch (input.next()) {
                    case "s":
                        sistema.getServicoQuiz().adicionaNivel();
                        break;
                    case "n":
                        continuar = false;
                        break;
                    default:
                        continuar = false;
                        System.out.print("Opção inválida.");
                        break;
                }
            }
        }

        UsuarioRepo.salvaUsuario(usuario);
        input.close();

        System.out.print("\nAté logo!");
    }
}