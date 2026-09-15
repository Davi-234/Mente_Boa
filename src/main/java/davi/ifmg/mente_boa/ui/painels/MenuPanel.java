package davi.ifmg.mente_boa.ui.painels;

import davi.ifmg.mente_boa.bd.BancoDeDados;
import davi.ifmg.mente_boa.ui.Tela;
import davi.ifmg.mente_boa.ui.Pop_ups;
import davi.ifmg.mente_boa.service.JogadorService;
import davi.ifmg.mente_boa.service.JogoService;
import davi.ifmg.mente_boa.sonoro.Sons;
import davi.ifmg.mente_boa.utill.Util;
import java.awt.Dimension;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

public class MenuPanel extends JPanel {

    public MenuPanel() {
        setOpaque(false);
        initComponents();
    }

    private void initComponents() {
        titulo = new javax.swing.JLabel();
        cadastro = new javax.swing.JButton();
        sair = new javax.swing.JButton();
        ranking = new javax.swing.JButton();
        jogar = new javax.swing.JButton();
        opc = new javax.swing.JButton();

        titulo.setFont(new java.awt.Font("Yu Gothic UI Semilight", 0, 60)); // NOI18N
        titulo.setForeground(new java.awt.Color(0, 0, 0));
        titulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titulo.setText("MenteBoa");

        cadastro.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        cadastro.setText("Cadastro");
        cadastro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cadastroActionPerformed(evt);
            }
        });

        sair.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        sair.setText("Sair");
        sair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sair(evt);
            }
        });

        ranking.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        ranking.setText("Ranking");
        ranking.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rakingAction(evt);
            }
        });

        jogar.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jogar.setText("Jogar");
        jogar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jogarAction(evt);
            }
        });

        opc.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        opc.setText("Opções");
        opc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                opcoes(evt);
            }
        });

        Dimension tamanhoBotao = new Dimension(1000, 50);

        for (var b : new JButton[]{
            cadastro, jogar, ranking, opc, sair
        }) {
            b.setPreferredSize(tamanhoBotao);
            b.setMaximumSize(tamanhoBotao);
            b.setMinimumSize(tamanhoBotao);
        }

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        titulo.setAlignmentX(CENTER_ALIGNMENT);
        cadastro.setAlignmentX(CENTER_ALIGNMENT);
        jogar.setAlignmentX(CENTER_ALIGNMENT);
        ranking.setAlignmentX(CENTER_ALIGNMENT);
        opc.setAlignmentX(CENTER_ALIGNMENT);
        sair.setAlignmentX(CENTER_ALIGNMENT);

        add(Box.createVerticalStrut(20));
        add(titulo);
        add(Box.createVerticalGlue());

        add(cadastro);
        add(Box.createVerticalStrut(20));

        add(jogar);
        add(Box.createVerticalStrut(20));

        add(ranking);
        add(Box.createVerticalStrut(20));

        add(opc);
        add(Box.createVerticalStrut(20));

        add(sair);
        add(Box.createVerticalGlue());
    }

    private void cadastroActionPerformed(java.awt.event.ActionEvent evt) {
        Sons.clicar();
        Pop_ups.telaDeOutput("Mensagem Cadastro", Pop_ups.telaCadastro());
    }

    private void sair(java.awt.event.ActionEvent evt) {
        Sons.clicar();
        if (Pop_ups.telaDeConfirmação("Deseja sair do jogo?")) {
            BancoDeDados.getInstance().salvar_dados();
            System.exit(0);
        }
    }

    private void jogarAction(java.awt.event.ActionEvent evt) {
        if (!new JogadorService().existeJogadores()) {
            Pop_ups.telaDeErro("Não existem jogadores cadastrados.");
            return;
        }

        Sons.clicar();

        String cpf = Pop_ups.pedirCPF("Digite o seu CPF: ");
        if (new JogadorService().getJogador(cpf) == null) {
            if (!Util.isCPFValido(cpf)) Pop_ups.telaDeErro("O CPF é inválido");
            else Pop_ups.telaDeErro("O CPF não está cadastrado");
            return;
        }

        Tela.jogar(cpf);
    }

    private void opcoes(java.awt.event.ActionEvent evt) {
        Sons.clicar();
        Tela.mudarPanel("opc");
    }

    public void rakingAction(java.awt.event.ActionEvent evt) {
        Sons.clicar();
        if (!new JogoService().existeJogos()) {
            Pop_ups.telaDeErro("Não existem jogadores ou jogos cadastrados.");
        } else {
            Tela.mostrar_ranque();
        }
    }

    private javax.swing.JButton cadastro;
    private javax.swing.JButton jogar;
    private javax.swing.JButton opc;
    private javax.swing.JButton ranking;
    private javax.swing.JButton sair;
    private javax.swing.JLabel titulo;

}
