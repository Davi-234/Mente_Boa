package davi.ifmg.mente_boa.ui;

import davi.ifmg.mente_boa.utill.Util;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.Timer;

public class Pop_ups {

    public static String telaCadastro() {
        while (true) {

            JLabel labelNome = new JLabel("Digite o nome: ");
            JTextField campoNome = new JTextField(30);

            JLabel labelApelido = new JLabel("Digite o Apelido: ");
            JTextField campoApelido = new JTextField(30);

            JLabel labelCPF = new JLabel("Digite o CPF: ");
            JTextField campoCPF = new JTextField(13);

            var painel = new JPanel();
            painel.setLayout(new GridLayout(7, 2, 3, 3));
            painel.setOpaque(false);

            painel.add(labelNome);
            painel.add(campoNome);

            painel.add(labelApelido);
            painel.add(campoApelido);

            painel.add(labelCPF);
            painel.add(campoCPF);

            JPanel conteinerPrincipal = new JPanel(new BorderLayout());
            conteinerPrincipal.setOpaque(false);
            conteinerPrincipal.add(painel, BorderLayout.CENTER);

            JOptionPane option = new JOptionPane(
                    conteinerPrincipal,
                    JOptionPane.PLAIN_MESSAGE,
                    JOptionPane.OK_CANCEL_OPTION,
                    null,
                    new Object[]{"Confirmar", "Cancelar"},
                    "Confirmar"
            );

            JDialog tela = option.createDialog("Cadastro");

            tela.setModal(true);
            tela.pack();
            tela.setLocationRelativeTo(null);
            tela.setAlwaysOnTop(false);
            tela.setVisible(true);

            Object opcaoEscolhida = option.getValue();

            if ("Confirmar".equals(opcaoEscolhida)) {
                tela.dispose();
                return Util.processamentoDosDados(campoNome, campoCPF, campoApelido);
            }

            tela.dispose();

            if (telaDeEncerrar("Deseja sair do cadastro ?.")) {
                return "Cadastro cancelado.";
            }
        }
    }

    public static boolean telaDeEncerrar(String palavra) {
        int resposta = JOptionPane.showConfirmDialog(
                null,
                palavra,
                "Aviso!",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE
        );
        return resposta == JOptionPane.YES_OPTION;
    }

    public static boolean telaDeConfirmação(String frase) {
        int resposta = JOptionPane.showConfirmDialog(
                null,
                frase,
                null,
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);

        if (resposta == JOptionPane.YES_OPTION) {
            return true;
        } else {
            return false;
        }
    }

    public static void telaDeErro(String fraseDeErro) {
        JOptionPane.showMessageDialog(
                null,
                fraseDeErro,
                "Erro",
                JOptionPane.ERROR_MESSAGE);
    }

    public static void telaParaConfirmacoes(String frase) {
        JOptionPane.showMessageDialog(
                null,
                frase,
                "",
                JOptionPane.INFORMATION_MESSAGE);
    }

    public static String pedirCPF(String frase) {

        while (true) {
            JLabel campoCPF = new JLabel(frase);
            JTextField digiteCPF = new JTextField(20);

            JPanel conteiner = new JPanel(new GridLayout(3, 3, 3, 3));
            conteiner.add(campoCPF);
            conteiner.add(digiteCPF);

            JOptionPane option = new JOptionPane(
                    conteiner,
                    JOptionPane.PLAIN_MESSAGE,
                    JOptionPane.OK_CANCEL_OPTION,
                    null,
                    new Object[]{"Confirmar", "Cancelar"},
                    "Confirmar");

            JDialog dialog = option.createDialog("Inserir CPF: ");
            dialog.setModal(true);
            dialog.pack();
            dialog.setLocationRelativeTo(null);
            dialog.setAlwaysOnTop(false);
            dialog.setVisible(true);

            Object opcaoEscolhida = option.getValue();
            String cpf = digiteCPF.getText();

            if (opcaoEscolhida != null && opcaoEscolhida.equals("Confirmar")) {
                dialog.dispose();
                return cpf;

            } else {

                dialog.setVisible(false);
                dialog.dispose();

                if (telaDeEncerrar("Deseja encerrar a função jogar ?")) {
                    return null;
                }
            }
        }
    }

    public static void telaDeOutput(String titulo, String conteudo) {
        JLabel texto = new JLabel(conteudo);

        JPanel painel = new JPanel(new BorderLayout());
        painel.add(texto, BorderLayout.CENTER);

        JOptionPane option = new JOptionPane(
                painel,
                JOptionPane.PLAIN_MESSAGE,
                JOptionPane.DEFAULT_OPTION,
                null,
                new Object[]{"OK"},
                "OK"
        );

        JDialog dialog = option.createDialog(titulo);
        dialog.setModal(true);
        dialog.pack();
        dialog.setLocationRelativeTo(null);
        dialog.setAlwaysOnTop(false);
        dialog.setVisible(true);
    }

    public static void finalizar() {

        JLabel label = new JLabel("Encerrando", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.PLAIN, 32));
        label.setForeground(Color.BLACK);

        JPanel p = new JPanel(new BorderLayout());
        p.add(label);

        JOptionPane op = new JOptionPane(
                p,
                JOptionPane.PLAIN_MESSAGE,
                JOptionPane.DEFAULT_OPTION,
                null,
                new Object[]{},
                null);

        JDialog d = op.createDialog("Sair");
        d.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        d.setAlwaysOnTop(true);
        d.setLocationRelativeTo(null);
        d.setModal(false);
        d.pack();
        d.setVisible(true);

        final int estado[] = {0};
        final int tempo[] = {20};

        Timer t = new Timer(800, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (tempo[0] <= 5) {
                    label.setText("Encerrado");
                    ((Timer) e.getSource()).stop();
                    d.dispose();
                    System.exit(0);
                }

                tempo[0]--;

                StringBuilder text = new StringBuilder("Encerrando");

                for (int i = 0; i < estado[0]; i++) {
                    text.append(".");
                }

                label.setText(text.toString());

                estado[0] = (estado[0] + 1) % 4;
            }
        });

        t.start();
    }
}