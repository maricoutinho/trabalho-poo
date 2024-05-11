package br.uff.arquivo;

import br.uff.Main;
import br.uff.questionario.Nivel;
import br.uff.questionario.Pergunta;

import java.io.*;

public class PerguntasUtil {

    private static final String ARQUIVO_NIVEIS = "src/main/resources/perguntas/";

    private static final String TEXTO = "Texto: ";
    private static final String PERGUNTA = "Pergunta: ";
    private static final String RESPOSTA = "Resposta: ";
    private static final String OPCAO_A = "A: ";
    private static final String OPCAO_B = "B: ";
    private static final String OPCAO_C = "C: ";
    private static final String OPCAO_D = "D: ";


    public static Nivel[] lerArquivos() {
        Nivel[] niveis = new Nivel[4];

        File pasta = new File(ARQUIVO_NIVEIS);

        if (pasta.exists()) {
            File[] arquivos = pasta.listFiles();

            for (int i = 0; i < arquivos.length; i++) {
                if (arquivos[i].isFile()) {
                    niveis[i] = lerArquivo(arquivos[i], i);
                }
            }
        }

        return niveis;
    }

    private static Nivel lerArquivo(File arquivo, int i) {
        Pergunta[] perguntas = new Pergunta[10];

        try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {

            String pergunta = null;
            String texto = null;
            String resposta = null;
            String[] opcoes = new String[4];

            int indiceOpcoes = 0;
            int indicePerguntas = 0;

            String linha = br.readLine();

            while (linha != null) {
                if (linha.startsWith(TEXTO)) {
                    if (texto != null) {
                        perguntas[indicePerguntas++] = new Pergunta(texto, pergunta, opcoes, resposta);
                        indiceOpcoes = 0;
                    } else {
                        texto = linha.substring(TEXTO.length());
                    }

                }
                if (linha.startsWith(PERGUNTA)) {
                    pergunta = linha.substring(PERGUNTA.length());
                }
                if (linha.startsWith(OPCAO_A) || linha.startsWith(OPCAO_B) || linha.startsWith(OPCAO_C) || linha.startsWith(OPCAO_D)) {
                    opcoes[indiceOpcoes] = linha;
                    indiceOpcoes++;
                }
                if (linha.startsWith(RESPOSTA)) {
                    resposta = linha.substring(RESPOSTA.length());
                }

                linha = br.readLine();
            }

            if (texto != null) {
                perguntas[indicePerguntas] = new Pergunta(texto, pergunta, opcoes, resposta);
            }

            return  new Nivel(i+1, perguntas);
        } catch (Exception e) {
        }

        return null;
    }
}
