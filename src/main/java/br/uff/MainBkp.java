package br.uff;

import br.uff.sistema.Sistema;
import br.uff.usuario.Aluno;
import br.uff.usuario.Usuario;

import java.util.Scanner;

public class MainBkp {

    public static void main(String[] args) {
        Sistema sistema = new Sistema();

        Scanner input = new Scanner(System.in);

        Usuario usuario = null;

        if (usuario instanceof Aluno) { // só apresenta o quiz para o aluno
            Aluno aluno = (Aluno) usuario;

            System.out.println("\n" + aluno); // exibe dados do usuário
            System.out.print("\nDeseja começar? (S/N)");

            switch (input.next()) {
                case "s":
                    sistema.comecaQuiz(aluno);
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
                        sistema.adicionaNivel();
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

        System.out.print("\nAté logo!");
    }
}