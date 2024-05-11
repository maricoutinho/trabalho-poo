package br.uff.quiz;

import br.uff.arquivo.PerguntasUtil;
import br.uff.arquivo.UsuarioUtil;
import br.uff.usuario.Aluno;
import br.uff.usuario.PerfomanceNivel;
import br.uff.usuario.Professor;
import br.uff.usuario.Usuario;

import java.util.List;
import java.util.Scanner;

public class Sistema {

    private List<Nivel> niveis;
    private List<Usuario> usuarios;

    public Sistema() {
        this.niveis = PerguntasUtil.lerArquivos();
        this.usuarios = UsuarioUtil.lerArquivos();
    }

    public List<Nivel> getNiveis() {
        return niveis;
    }

    public void setNiveis(List<Nivel> niveis) {
        this.niveis = niveis;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
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

    public void exibeQuiz(Aluno aluno) {
        Scanner input = new Scanner(System.in);
        List<Nivel> niveis = getNiveis();

        if (niveis == null || niveis.isEmpty()) {
            System.out.println("Poxa, ainda não existem perguntas cadastradas no sistema.");
            return;
        }

        if (aluno.getNivel() == niveis.size()+1) {
            System.out.println("Você já chegou no nível máximo do quiz.");
            return;
        }


        for (int i = aluno.getNivel()-1; i < niveis.size(); i++) { // comeca pelo nivel atual do aluno
            Nivel nivel = niveis.get(i);

            System.out.println("\n=====Nível " + nivel.getId() + "======\n");

            // historico de performance do aluno
            PerfomanceNivel performance = new PerfomanceNivel();

            if (aluno.getPerformance() == null || aluno.getPerformance().size() <= i) { // se nao existir performance para aquele nivel, adiciona a referencia
                aluno.addPerformance(performance);
            } else {
                performance = aluno.getPerformance().get(i); // pega performance referente ao nivel caso o aluno tenha
            }

            // exibe perguntas por nivel
            for (int j = 0; j < nivel.getPerguntas().size(); j++) {
                Pergunta pergunta = nivel.getPerguntas().get(j);

                if (performance.getPerguntasCorretas().contains(j)) { // se a pergunta já foi respondida corretamente, passa para a proxima
                    continue;
                }

                System.out.println(pergunta);
                System.out.print("Resposta: ");

                if (input.next().equalsIgnoreCase(pergunta.getResposta())) {
                    System.out.println("---------------------------------");

                    System.out.println(">>>>> Correto!");
                    performance.getPerguntasCorretas().add(j);
                } else {
                    System.out.println("---------------------------------");
                    System.out.println(">>>>> Que pena, você errou!");
                }

                System.out.println("=================================");
            }

            System.out.println("Esse nível possui [" + nivel.getPerguntas().size() + "] pergunta(s)");
            System.out.println("E você acertou [" + performance.getPerguntasCorretas().size() + "].");
            System.out.println("=================================\n");

            // atualiza performance do usuario
            int corretas = performance.getPerguntasCorretas().size();
            int total = nivel.getPerguntas().size();

            performance.setPorcentagemConclusao((100 * corretas) / total + "%");

            if (corretas == total) { // acertou todas as perguntas/passou de nível
                aluno.sobeNivel();

                if (i == niveis.size()-1) {
                    System.out.println("Parabéns! Você terminou o último nível.");
                    return;
                } else {
                    System.out.println("Parabéns! Você terminou o nível [" + (i+1) + "].");
                    System.out.println("\n" + "Deseja continuar? (S/N): ");

                    if (input.next().equalsIgnoreCase("n")) {
                        return;
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
                   return;
                }
            }
        }
    }
}
