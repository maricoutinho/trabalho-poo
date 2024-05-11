package br.uff;

import br.uff.arquivo.UsuarioUtil;
import br.uff.quiz.Sistema;
import br.uff.usuario.Aluno;
import br.uff.usuario.Usuario;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Sistema sistema = new Sistema();

        Scanner input = new Scanner(System.in);

        Usuario usuario = sistema.trataUsuario();
        if (usuario == null) {
            System.out.print("Login e/ou senha incorretos: verifique os dados e tente novamente.");
            return;
        }

        System.out.println("\n" + usuario); // exibe dados do usuário

        if (usuario instanceof Aluno) { // só apresenta o quiz para o aluno
            Aluno aluno = (Aluno) usuario;

            System.out.print("\nDeseja começar? (S/N)");

            switch (input.next()) {
                case "s":
                    sistema.exibeQuiz(aluno);
                    break;
                case "n":
                    break;
                default:
                    System.out.print("Opção inválida.");
                    break;
            }
            UsuarioUtil.salvaUsuario(aluno);
        } else {
            System.out.print("\nPor enquanto o sistema não possui funcionalidades para você.");
        }
    }
}