package br.uff.questionario;

import br.uff.arquivo.PerguntasUtil;
import br.uff.arquivo.UsuarioUtil;
import br.uff.usuario.Aluno;
import br.uff.usuario.PerfomanceNivel;
import br.uff.usuario.Professor;
import br.uff.usuario.Usuario;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Sistema {

    private Nivel[] niveis;
    private Usuario[] usuarios;

    public Sistema() {
        this.niveis = PerguntasUtil.lerArquivos();
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

    public Usuario trataUsuario() {
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
                        usuario = new Aluno(login, senha, 1, new ArrayList<>());
                        break;
                    case 2:
                        usuario = new Professor(login, senha);
                        break;
                    default:
                        System.out.println("Opção inválida!");
                        return null;

                }
                UsuarioUtil.salvaUsuario(usuario); // cria arquivo com info do usuario
                break;

            case 2:
                usuario = logaUsuario(login, senha); // valida login e senha
                break;
        }

        return usuario;
    }

    private Usuario logaUsuario(String login, String senha) {
        for (Usuario usuario : usuarios) {
            if (usuario.getLogin().equals(login) && usuario.getSenha().equals(senha)) {
                return usuario;
            }
        }
        return null;
    }

    public boolean comecarQuestionario(Aluno aluno) {
        Scanner input = new Scanner(System.in);
        boolean stop = false;

        Nivel[] niveis = getNiveis();

//        if (niveis == null) {
//            System.out.println("Poxa, ainda não existem perguntas cadastradas no sistema.");
//            stop = true;
//            continue;
//        }

        for (int i = 0; i < niveis.length; i++) {
            System.out.println("\n=====Nível " + (i+1) + "======\n");

            PerfomanceNivel performance = new PerfomanceNivel(); // historico de performance do aluno

            if (aluno.getPerfomance() == null) {
                aluno.setPerfomance(List.of(performance));
            } else if (aluno.getPerfomance().get(i) == null) {
                aluno.getPerfomance().add(performance);
            } else {
                performance = aluno.getPerfomance().get(i);
            }

            for (int j = 0; j < niveis[i].getPerguntas().length; j++) {
                if (performance.getPerguntasCorretas().contains(j)) { // se a pergunta já foi respondida corretamente, passa para a proxima
                    continue;
                }

                System.out.println(niveis[i].getPerguntas()[j]);
                System.out.print("Resposta: ");

                if (input.next().equalsIgnoreCase(niveis[i].getPerguntas()[j].getResposta())) {
                    System.out.println("Correto!");
                    performance.getPerguntasCorretas().add(j);
                } else {
                    System.out.println("Que pena, você errou!");
                }
            }

            System.out.println("\n=================================");
            System.out.println("Você respondeu [" + niveis[i].getPerguntas().length + "] pergunta(s).");
            System.out.println("E acertou [" + performance.getPerguntasCorretas().size() + "].");
            System.out.println("=================================\n");

            // atualiza performance do usuario
            performance.setPorcentagemConclusao((100 * performance.getPerguntasCorretas().size()) / niveis[i].getPerguntas().length + "%");

            if (performance.getPerguntasCorretas().size() == niveis[i].getPerguntas().length) { // acertou todas as perguntas/passou de nível
                aluno.setNivel(i+1);

                if (i == niveis.length-1) {
                    System.out.println("Parabéns! Você terminou o último nível.");
                    UsuarioUtil.salvaUsuario(aluno);
                    stop = true;
                } else {
                    System.out.println("Parabéns! Você terminou o nível [" + (i+1) + "].");
                    System.out.println("Deseja continuar? (S/N): ");

                    if (input.next().equalsIgnoreCase("n")) {
                        UsuarioUtil.salvaUsuario(aluno);
                        stop = true;
                    }
                }
            } else { // errou alguma pergunta/nao passou de nivel
                System.out.println("Deseja: ");
                System.out.println("1) Retentar nível");
                System.out.println("2) Sair");

                int opcao = input.nextInt();

                if (opcao == 1) {
                    i--; // voltar nível para retentar perguntas
                } else {
                    UsuarioUtil.salvaUsuario(aluno);
                    stop = true;
                }
            }
        }
        return stop;
    }
}
