package br.uff;

import br.uff.arquivo.UsuarioUtil;
import br.uff.questionario.Nivel;
import br.uff.questionario.Pergunta;
import br.uff.questionario.Sistema;
import br.uff.usuario.Aluno;
import br.uff.usuario.Professor;
import br.uff.usuario.Usuario;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Sistema sistema = new Sistema();

        Scanner input = new Scanner(System.in);

        boolean stop = false;

        while (!stop) {
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
                            System.out.print("Opção inválida!");
                            continue;
                    }
                    UsuarioUtil.criaUsuario(usuario);
                    break;

                case 2:
                    usuario = sistema.logaUsuario(login, senha);
                    break;
            }

            if (usuario == null) {
                System.out.print("Login e/ou senha não existem: verique os dados e tente novamente.");
                stop = true;
                continue;
            }

            System.out.println("\n" + usuario);

            if (usuario instanceof Aluno) {

                System.out.print("\nDeseja começar? (S/N)");
                String comecar = input.next();

                if (comecar.equalsIgnoreCase("s")) {
                    Nivel[] niveis = sistema.getNiveis();

//                    if (niveis == null) {
//                        System.out.println("Poxa, ainda não existem perguntas cadastradas no sistema.");
//                        stop = true;
//                        continue;
//                    }

                    for (int i = 0; i < niveis.length; i++) {
                        System.out.println("\n=====Nível " + (i+1) + "======\n");

                        String resposta;
                        Pergunta[] perguntas = niveis[i].getPerguntas();
                        int acertos = 0;
                        boolean[] perguntasCorretas = new boolean[perguntas.length];

                        for (int j = 0; j < perguntas.length; j++) {
                            if (perguntasCorretas[j]) {
                                continue;
                            }

                            System.out.println(perguntas[j]);

                            System.out.print("Resposta: ");
                            resposta = input.next();

                            if (resposta.equalsIgnoreCase(perguntas[0].getResposta())) {
                                System.out.println("Correto!");
                                acertos++;
                                perguntasCorretas[j] = true;
                            } else {
                                System.out.println("Que pena, você errou!");
                                perguntasCorretas[j] = false;
                            }
                        }

                        System.out.println("\n=================================");
                        System.out.println("Você respondeu [" + perguntas.length + "] pergunta(s).");
                        System.out.println("E acertou [" + acertos + "].");
                        System.out.println("=================================\n");

                        if (i == niveis.length-1) {
                            System.out.println("Você terminou o último nível. Parabéns!");
                            UsuarioUtil.atualizaUsuario(usuario);
                            stop = true;
                        } else {
                            System.out.print("\nDeseja continuar? (S/N)");
                            String continuar = input.next();

                            if (!continuar.equalsIgnoreCase("s")) {
                                stop = true;

                                ((Aluno) usuario).setNivel(i+1);
                                UsuarioUtil.atualizaUsuario(usuario);
                            }
                        }
                    }
                } else {
                    stop = true;
                }
            }

            // como filtrar as perguntas que ele ja acertou?
        }
    }
}

//TODO: erro ao atualizar arquivo: ta concatenando ou nao sobrescreve
//TODO: exibir histórico de alguma maneira
//TODO: caso o aluno deseje parar, salvar historico (?) para que ele nao responda as mesmas perguntas várias vezes
//TODO: tem que salvar do aluno: login, senha, historico de performance, historico de perguntas respondidas e em que nivel se encontra

//TODO: caso ele ja exista, abrir apenas as perguntas da fase em que ele se encontra (salvar indice das perguntas ja respondidas)
//TODO: alunno pula de fase quando responder todas as perguntas certas daquela fase