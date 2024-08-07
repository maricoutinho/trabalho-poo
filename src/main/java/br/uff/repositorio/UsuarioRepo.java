package br.uff.repositorio;

import br.uff.usuario.Aluno;
import br.uff.usuario.PerfomanceNivel;
import br.uff.usuario.Professor;
import br.uff.usuario.Usuario;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioRepo {

    private static final String ARQUIVO_LOGIN = "src/main/resources/usuarios/";

    private static final String TIPO = "Tipo: ";
    private static final String LOGIN = "Login: ";
    private static final String SENHA = "Senha: ";
    private static final String NIVEL_ATUAL = "Nivel Atual: ";
    private static final String NIVEL = "Nivel: ";
    private static final String PERFORMANCE = "Respondidas corretamente: ";
    private static final String CONCLUIDO = "Concluido: ";

    public static List<Usuario> lerArquivos() {
        List<Usuario> usuarios = new ArrayList<>();

        File pasta = new File(ARQUIVO_LOGIN);

        if (pasta.exists()) {
            File[] arquivos = pasta.listFiles();

            for (int i = 0; i < arquivos.length; i++) {
                if (arquivos[i].isFile()) {
                    usuarios.add(lerArquivo(arquivos[i]));
                }
            }
        }

        return usuarios;
    }

    private static Usuario lerArquivo(File arquivo) {
        Usuario usuario = null;
        List<PerfomanceNivel> perfomances = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
            String tipo = null;
            String login = null;
            String senha = null;
            int nivel = 0;
            PerfomanceNivel perfomance = null;
            String linha = br.readLine();

            while (linha != null) {
                if (linha.startsWith(TIPO)) {
                    tipo = linha.substring(TIPO.length());
                }

                if (linha.startsWith(LOGIN)) {
                    login = linha.substring(LOGIN.length());
                }
                if (linha.startsWith(SENHA)) {
                    senha = linha.substring(SENHA.length());
                }
                if (linha.startsWith(NIVEL_ATUAL)) {
                    nivel = Integer.parseInt(linha.substring(NIVEL_ATUAL.length()));
                }
                if (linha.startsWith(NIVEL)) {
                    perfomance = new PerfomanceNivel();
                }
                if (perfomance != null) {
                    if (linha.startsWith(PERFORMANCE)) {
                        String[] indices = linha.substring(PERFORMANCE.length()).split(",");
                        for (String index : indices) {
                            if (!index.trim().isEmpty()) {
                                perfomance.getPerguntasCorretas().add(Integer.parseInt(index));
                            }
                        }
                    }
                    if (linha.startsWith(CONCLUIDO)) {
                        perfomance.setPorcentagemConclusao(linha.substring(CONCLUIDO.length()));
                        perfomances.add(perfomance);
                    }
                }

                linha = br.readLine();
            }

            if (tipo != null) {
                if (tipo.equals("Aluno")) {
                    usuario = new Aluno(login, senha, nivel, perfomances);
                } else {
                    usuario = new Professor(login, senha);
                }
            }
        } catch (IOException e) {
            return null;
        }

        return usuario;
    }

    public static void salvarUsuario(Usuario usuario) {
        try {
            FileWriter fw = new FileWriter(ARQUIVO_LOGIN + "/" + usuario.getLogin());

            String tipo = "Aluno";
            if (usuario instanceof Professor) {
                tipo = "Professor";
            }

            fw.write(TIPO + tipo + "\n");
            fw.write(LOGIN + usuario.getLogin() + "\n");
            fw.write(SENHA + usuario.getSenha() + "\n");

            if (usuario instanceof Aluno) {
                fw.write(NIVEL_ATUAL + ((Aluno) usuario).getNivel() + "\n");

                if (((Aluno) usuario).getPerformance() != null) {
                    for (int i = 0; i < ((Aluno) usuario).getPerformance().size(); i++) {
                        fw.write(NIVEL + (i+1) + "\n");
                        fw.write(PERFORMANCE + ((Aluno) usuario).getPerformance().get(i).perguntasCorretasToString() + "\n");
                        fw.write(CONCLUIDO + ((Aluno) usuario).getPerformance().get(i).getPorcentagemConclusao() + "\n");
                    }
                }
            }

            fw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}