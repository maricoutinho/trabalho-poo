package br.uff.telas;

import br.uff.sistema.Sistema;
import br.uff.usuario.Aluno;
import br.uff.usuario.Usuario;

import javax.swing.*;
import java.awt.*;

public class LoginPanel extends JPanel {
    private JTextField usuarioField;
    private JPasswordField senhaField;
    private JButton loginButton;
    private JButton cadastreseButton;

    public LoginPanel(Sistema sistema) {
        usuarioField = new JTextField(15);
        senhaField = new JPasswordField(15);
        loginButton = new JButton("Login");
        cadastreseButton = new JButton("Cadastre-se");

        setLayout(new GridLayout(3, 2));
        add(new JLabel("Usuário:"));
        add(usuarioField);
        add(new JLabel("Senha:"));
        add(senhaField);
        add(loginButton);
        add(cadastreseButton);

        loginButton.addActionListener(_ -> {
            String login = usuarioField.getText();
            String senha = new String(senhaField.getPassword());

            Usuario usuario = sistema.logaUsuario(login, senha);

            if (usuario == null) {
                JOptionPane.showMessageDialog(LoginPanel.this,
                        "Login e/ou senha incorretos: verifique os dados e tente novamente.",
                        "Erro de Login",
                        JOptionPane.ERROR_MESSAGE);
            } else if (usuario instanceof Aluno) {
                QuizApp.getInstance().getAlunoPanel().setAluno((Aluno) usuario);
                QuizApp.getInstance().getAlunoPanel().exibeHistorico();
                QuizApp.getInstance().mostrarTela("Aluno");
            } else {
                QuizApp.getInstance().mostrarTela("Professor");

            }
        });

        cadastreseButton.addActionListener(e -> QuizApp.getInstance().mostrarTela("Cadastro"));
    }
}
