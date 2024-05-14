package br.uff.sistema;

import br.uff.quiz.Nivel;
import br.uff.repositorio.NivelRepo;
import br.uff.quiz.Pergunta;
import br.uff.usuario.Aluno;
import br.uff.usuario.PerfomanceNivel;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringJoiner;

public class ServicoQuiz {

    private List<Nivel> niveis;

    public ServicoQuiz() {
        this.niveis = NivelRepo.lerArquivos();
    }

    public void adicionaNivel() {
        Scanner input = new Scanner(System.in);

        boolean continuaNivel = true;

        while (continuaNivel) {
            List<Pergunta> perguntas = new ArrayList<>();
            boolean continuaPergunta = true;

            while (continuaPergunta) {
                perguntas.add(criaPergunta());

                System.out.print("\nDeseja adicionar uma nova pergunta? (S/N)");
                continuaPergunta = input.next().equalsIgnoreCase("s");
            }

            Nivel nivel = new Nivel((niveis.size()+1), perguntas);

            NivelRepo.adicionaNivel(nivel);
            niveis.add(nivel); // tratar de uma melhor maneira

            System.out.println("\n--- Nivel [" + nivel.getId() + "] adicionado com sucesso!");

            System.out.print("\nDeseja adicionar um novo nível? (S/N)");
            continuaNivel = input.next().equalsIgnoreCase("s");
        }
    }

    private Pergunta criaPergunta() {
        Scanner input = new Scanner(System.in);

        List<String> opcoes = new ArrayList<>();

        System.out.println("\nFavor inserir os dados da pergunta a seguir.");

        System.out.print("Digite o texto: ");
        String texto = input.nextLine();
        System.out.print("Digite a pergunta: ");
        String pergunta = input.nextLine();
        System.out.print("Digite a opção A: ");
        opcoes.add(input.nextLine());
        System.out.print("Digite a opção B: ");
        opcoes.add(input.nextLine());
        System.out.print("Digite a opção C: ");
        opcoes.add(input.nextLine());
        System.out.print("Digite a opção D: ");
        opcoes.add(input.nextLine());
        System.out.print("Digite a resposta correta: ");
        String resposta = input.nextLine();

        return new Pergunta(texto, pergunta, opcoes, resposta);
    }

    public void comecaQuiz(Aluno aluno) {
        Scanner input = new Scanner(System.in);
        List<Nivel> niveis = getNiveis();

        if (niveis == null || niveis.isEmpty()) {
            System.out.println("Poxa, ainda não existem niveis cadastradas no sistema.");
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

            // exibe niveis por nivel
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

            if (corretas == total) { // acertou todas as niveis/passou de nível
                aluno.sobeNivel();

                if (i == niveis.size()-1) {
                    System.out.println("Parabéns! Você terminou o último nível.");
                    return;
                } else {
                    System.out.println("Parabéns! Você terminou o nível [" + (i+1) + "].");
                    System.out.println("\n" + "Passar para o próximo nível? (S/N): ");

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
                    i--; // voltar nível para retentar niveis
                } else {
                    return;
                }
            }
        }
    }

    public String exibeDados() {
        StringJoiner joiner = new StringJoiner("\n");

        if (niveis == null || niveis.isEmpty()) {
            joiner.add("Não existem níveis cadastrados no sistema.");
        }

        for (Nivel nivel : niveis) {
            joiner.add("Nivel " + nivel.getId() + ": " + nivel.getPerguntas().size() + " pergunta(s) cadastradas.");
        }

        return joiner.toString();
    }

    public List<Nivel> getNiveis() {
        return niveis;
    }

    public void setNiveis(List<Nivel> niveis) {
        this.niveis = niveis;
    }
}
