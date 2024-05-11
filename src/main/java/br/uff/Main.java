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
        }

        System.out.println("\n" + usuario); // exibe dados do usuário

        if (usuario instanceof Aluno) { // só apresenta as perguntas para o aluno
            System.out.print("\nDeseja começar? (S/N)");

            if (input.next().equalsIgnoreCase("s")) {
                sistema.exibeQuiz((Aluno) usuario);
            }
            UsuarioUtil.salvaUsuario(usuario);
        } else {
            System.out.print("\nPor enquanto o sistema não possui funcionalidades para você.");
        }
    }
}

//TODO: caso ele ja exista, abrir apenas as perguntas da fase em que ele se encontra (salvar indice das perguntas ja respondidas)
//TODO: precisa começar do nivel que o usuario está
