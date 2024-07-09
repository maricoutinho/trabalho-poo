package br.uff.telas;

import br.uff.sistema.Sistema;
import br.uff.usuario.Aluno;
import br.uff.usuario.Professor;
import br.uff.usuario.Usuario;

import javax.swing.*;
import java.awt.*;

class CadastroPanel extends JPanel {
    private JTextField usuarioField;
    private JPasswordField senhaField;
    private JRadioButton alunoButton;
    private JRadioButton professorButton;
    private JButton cadastrarButton;
    private JButton voltarButton;

    public CadastroPanel(Sistema sistema) {
        usuarioField = new JTextField(15);
        senhaField = new JPasswordField(15);
        alunoButton = new JRadioButton("Aluno");
        professorButton = new JRadioButton("Professor");
        ButtonGroup perfilGroup = new ButtonGroup();
        perfilGroup.add(alunoButton);
        perfilGroup.add(professorButton);
        cadastrarButton = new JButton("Cadastrar");
        voltarButton = new JButton("Voltar");

        setLayout(new GridLayout(6, 2));
        add(new JLabel("Usuário:"));
        add(usuarioField);
        add(new JLabel("Senha:"));
        add(senhaField);
        add(new JLabel("Perfil:"));
        JPanel perfilPanel = new JPanel();
        perfilPanel.add(alunoButton);
        perfilPanel.add(professorButton);
        add(perfilPanel);
        add(cadastrarButton);
        add(voltarButton);

        cadastrarButton.addActionListener(e -> {
            String login = usuarioField.getText();
            String senha = new String(senhaField.getPassword());
            String perfil = alunoButton.isSelected() ? "Aluno" : "Professor";

            Usuario usuario;

            if (perfil.equals("Aluno")) {
                usuario = new Aluno(login, senha, 1);
            } else {
                usuario = new Professor(login, senha);
            }

            sistema.salvaUsuario(usuario);

            JOptionPane.showMessageDialog(CadastroPanel.this,
                    "Usuário cadastrado com sucesso!",
                    "Cadastro",
                    JOptionPane.INFORMATION_MESSAGE);

            if (perfil.equals("Aluno")) {
                GerenciadorDeTelas.getInstance().getAlunoPanel().setAluno((Aluno) usuario);
                GerenciadorDeTelas.getInstance().getAlunoPanel().exibeHistorico();
                GerenciadorDeTelas.getInstance().mostrarTela("Aluno");
            } else {
                GerenciadorDeTelas.getInstance().mostrarTela("Login");
            }
        });

        voltarButton.addActionListener(e -> GerenciadorDeTelas.getInstance().mostrarTela("Login"));
    }
}