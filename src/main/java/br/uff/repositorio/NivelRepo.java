package br.uff.repositorio;

import br.uff.quiz.Nivel;
import br.uff.quiz.Pergunta;
import br.uff.usuario.Aluno;
import br.uff.usuario.Professor;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NivelRepo {

    private static final String ARQUIVO_NIVEIS = "src/main/resources/niveis/";

    private static final String TEXTO = "Texto: ";
    private static final String PERGUNTA = "Pergunta: ";
    private static final String RESPOSTA = "Resposta: ";
    private static final String OPCAO_A = "A: ";
    private static final String OPCAO_B = "B: ";
    private static final String OPCAO_C = "C: ";
    private static final String OPCAO_D = "D: ";


    public static List<Nivel> lerArquivos() {
        List<Nivel> niveis = new ArrayList<>();

        File pasta = new File(ARQUIVO_NIVEIS);

        if (pasta.exists()) {
            File[] arquivos = pasta.listFiles();

            Arrays.sort(arquivos); // para ler os arquivos em ordem alfabetica e podermos respeitar a ordem dos niveis

            for (int i = 0; i < arquivos.length; i++) {
                if (arquivos[i].isFile()) {
                    niveis.add(lerArquivo(arquivos[i], i));
                }
            }
        }

        return niveis;
    }

    private static Nivel lerArquivo(File arquivo, int i) {
        List<Pergunta> perguntas = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {

            String pergunta = null;
            String texto = null;
            String resposta = null;
            List<String> opcoes = new ArrayList<>();

            String linha = br.readLine();

            while (linha != null) {
                if (linha.startsWith(TEXTO)) {
                    if (texto != null) {
                        perguntas.add(new Pergunta(texto, pergunta, opcoes, resposta));
                        opcoes = new ArrayList<>();
                    } else {
                        texto = linha.substring(TEXTO.length());
                    }

                }
                if (linha.startsWith(PERGUNTA)) {
                    pergunta = linha.substring(PERGUNTA.length());
                }
                if (linha.startsWith(OPCAO_A) || linha.startsWith(OPCAO_B) || linha.startsWith(OPCAO_C) || linha.startsWith(OPCAO_D)) {
                    opcoes.add(linha);
                }
                if (linha.startsWith(RESPOSTA)) {
                    resposta = linha.substring(RESPOSTA.length());
                }

                linha = br.readLine();
            }

            if (texto != null) {
                perguntas.add(new Pergunta(texto, pergunta, opcoes, resposta));
            }

            return  new Nivel(i+1, perguntas);
        } catch (Exception _) {
        }

        return null;
    }

    public static void adicionaNivel(Nivel nivel) {
        try {
            FileWriter fw = new FileWriter(ARQUIVO_NIVEIS + "/nivel-" + nivel.getId());

            for (Pergunta pergunta : nivel.getPerguntas()) {
                fw.write(TEXTO + pergunta.getTexto() + "\n");
                fw.write(PERGUNTA + pergunta.getPergunta() + "\n");
                fw.write(OPCAO_A + pergunta.getOpcoes().get(0) + "\n");
                fw.write(OPCAO_B + pergunta.getOpcoes().get(1) + "\n");
                fw.write(OPCAO_C + pergunta.getOpcoes().get(2) + "\n");
                fw.write(OPCAO_D + pergunta.getOpcoes().get(3) + "\n");
                fw.write(RESPOSTA + pergunta.getResposta() + "\n");
            }

            fw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
