package br.uff.arquivo;

import br.uff.questionario.Nivel;
import br.uff.questionario.Pergunta;

import java.io.*;

public class PerguntasUtil {

    private static final String ARQUIVO_FASE_1 = "src/main/resources/perguntas/fase-1.txt";
    private static final String ARQUIVO_FASE_2 = "src/main/resources/perguntas/fase-2.txt";
    private static final String ARQUIVO_FASE_3 = "src/main/resources/perguntas/fase-3.txt";
    private static final String ARQUIVO_FASE_4 = "src/main/resources/perguntas/fase-4.txt";
    private static final String[] ARQUIVOS_FASES = new String[]{ARQUIVO_FASE_1, ARQUIVO_FASE_2, ARQUIVO_FASE_3, ARQUIVO_FASE_4};

    private static final String TEXTO = "Texto: ";
    private static final String PERGUNTA = "Pergunta: ";
    private static final String RESPOSTA = "Resposta: ";
    private static final String OPCAO_A = "A: ";
    private static final String OPCAO_B = "B: ";
    private static final String OPCAO_C = "C: ";
    private static final String OPCAO_D = "D: ";


    public static Nivel[] lerArquivo() {
        Nivel[] niveis = new Nivel[4];

        for (int i = 0; i < ARQUIVOS_FASES.length; i++) {
            Pergunta[] perguntas = new Pergunta[1];

            try (BufferedReader br = new BufferedReader(new FileReader(ARQUIVOS_FASES[i]))) {
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

                niveis[i] = new Nivel(i+1, perguntas);

            } catch (IOException e) {
                return null;
            }
        }
        return niveis;
    }
}
