package br.uff;

import br.uff.questionario.Sistema;
import br.uff.usuario.Aluno;
import br.uff.usuario.Usuario;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Sistema sistema = new Sistema();

        Scanner input = new Scanner(System.in);

        boolean stop = false;

        while (!stop) {
            Usuario usuario = sistema.trataUsuario();
            if (usuario == null) {
                System.out.print("Login e/ou senha incorretos: verifique os dados e tente novamente.");
                stop = true;
                continue;
            }

            System.out.println("\n" + usuario); // exibe dados do usuário

            if (usuario instanceof Aluno) { // só apresenta as perguntas para o aluno
                System.out.print("\nDeseja começar? (S/N)");

                if (input.next().equalsIgnoreCase("s")) {
                    stop = sistema.comecarQuestionario((Aluno) usuario);

                    if (stop) {
                        // salva usuario com atualizacao de perfomance
                    }
                } else {
                    System.out.print("\nPor enquanto o sistema não possui funcionalidades para você.");
                    stop = true;
                }
            }
        }
    }
}

//TODO: erro ao atualizar arquivo: ta concatenando ou nao sobrescreve
//TODO: caso o aluno deseje parar, salvar historico (?) para que ele nao responda as mesmas perguntas várias vezes
//TODO: caso ele ja exista, abrir apenas as perguntas da fase em que ele se encontra (salvar indice das perguntas ja respondidas)