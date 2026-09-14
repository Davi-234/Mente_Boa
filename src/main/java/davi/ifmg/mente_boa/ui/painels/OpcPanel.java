package davi.ifmg.mente_boa.ui.painels;

import davi.ifmg.mente_boa.ui.Tela;
import davi.ifmg.mente_boa.ui.Pop_ups;
import davi.ifmg.mente_boa.service.JogadorService;
import davi.ifmg.mente_boa.service.JogoService;
import davi.ifmg.mente_boa.sonoro.Sons;
import static java.awt.Component.CENTER_ALIGNMENT;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

public class OpcPanel extends JPanel {

    public OpcPanel() {
        setOpaque(false);
        initComponents();
    }

    private void initComponents() {

        titulo = new javax.swing.JLabel();
        mostrarJogadores = new javax.swing.JButton();
        maiorPont = new javax.swing.JButton();
        menorPont = new javax.swing.JButton();
        mostrarJogos = new javax.swing.JButton();
        voltarMenu = new javax.swing.JButton();
        apagarDadosBTN = new javax.swing.JButton();

        titulo.setFont(new java.awt.Font("Yu Gothic UI Semilight", 0, 60)); // NOI18N
        titulo.setForeground(new java.awt.Color(0, 0, 0));
        titulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titulo.setText("MenteBoa");

        mostrarJogadores.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        mostrarJogadores.setText("Mostrar todos os jogadores");
        mostrarJogadores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mostrarJogadores(evt);
            }
        });

        maiorPont.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        maiorPont.setText("Maior pontuação de um jogador");
        maiorPont.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                maiorPontuacao(evt);
            }
        });

        menorPont.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        menorPont.setText("Menor pontuação de um jogador");
        menorPont.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menorPontuacao(evt);
            }
        });

        mostrarJogos.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        mostrarJogos.setText("Mostrar todos os jogos");
        mostrarJogos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mostrarJogos(evt);
            }
        });

        voltarMenu.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        voltarMenu.setText("Voltar para a tela de menu");
        voltarMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                voltarMenuPrincipal(evt);
            }
        });

        apagarDadosBTN.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        apagarDadosBTN.setText("Limpar a base de dados");
        apagarDadosBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                limparDados(evt);
            }
        });

        Dimension tamanhoBotao = new Dimension(1000, 50);

        for (var b : new JButton[]{
            mostrarJogadores, voltarMenu, menorPont, mostrarJogos, maiorPont, voltarMenu, apagarDadosBTN
        }) {
            b.setPreferredSize(tamanhoBotao);
            b.setMaximumSize(tamanhoBotao);
            b.setMinimumSize(tamanhoBotao);
        }

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        titulo.setAlignmentX(CENTER_ALIGNMENT);
        mostrarJogadores.setAlignmentX(CENTER_ALIGNMENT);
        apagarDadosBTN.setAlignmentX(CENTER_ALIGNMENT);
        menorPont.setAlignmentX(CENTER_ALIGNMENT);
        voltarMenu.setAlignmentX(CENTER_ALIGNMENT);
        maiorPont.setAlignmentX(CENTER_ALIGNMENT);
        mostrarJogos.setAlignmentX(CENTER_ALIGNMENT);

        add(Box.createVerticalStrut(20));
        add(titulo);
        add(Box.createVerticalGlue());

        add(mostrarJogadores);
        add(Box.createVerticalStrut(20));

        add(mostrarJogos);
        add(Box.createVerticalStrut(20));

        add(menorPont);
        add(Box.createVerticalStrut(20));

        add(maiorPont);
        add(Box.createVerticalStrut(20));

        add(apagarDadosBTN);
        add(Box.createVerticalStrut(20));

        add(voltarMenu);
        add(Box.createVerticalGlue());

    }

    private void mostrarJogadores(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cadastroActionPerformed
        Sons.clicar();
        if (!new JogadorService().existeJogadores()) Pop_ups.telaDeErro("Não existem jogadores cadastrados.");
        else Tela.mostrar_jogadores();
    }

    private void maiorPontuacao(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sairActionPerformed
        Sons.clicar();
        if (!new JogoService().existeJogos()) Pop_ups.telaDeErro("Não existem jogadores ou jogos cadastrados.");
        else {
            String cpf = Pop_ups.pedirCPF("Qual o CPF do jogador?");
            Pop_ups.telaDeOutput("Maior Pontuação de " + cpf, new JogoService().maiorPontuacaoDeUmJogador(cpf));
        }
    }

    private void mostrarJogos(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jogar1ActionPerformed
        Sons.clicar();
        if (!new JogoService().existeJogos()) Pop_ups.telaDeErro("Não existem jogos cadastrados.");
        else Tela.mostrar_jogos();
    }

    private void limparDados(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_opcInternas2ActionPerformed
        Sons.clicar();
        new JogadorService().limparDados();
    }

    private void menorPontuacao(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rankingActionPerformed
        Sons.clicar();
        if (!new JogoService().existeJogos()) Pop_ups.telaDeErro("Não existem jogadores ou jogos cadastrados.");
        else {
            String cpf = Pop_ups.pedirCPF("Qual o CPF do jogador?");
            Pop_ups.telaDeOutput("Menor Pontuação de " + cpf, new JogoService().menorPontuacaoDeUmJogador(cpf));
        }
    }

    public void voltarMenuPrincipal(ActionEvent evt) {
        Sons.clicar();
        Tela.mudarPanel("menu");
    }

    private javax.swing.JButton mostrarJogadores;
    private javax.swing.JButton apagarDadosBTN;
    private javax.swing.JButton mostrarJogos;
    private javax.swing.JButton voltarMenu;
    private javax.swing.JButton menorPont;
    private javax.swing.JButton maiorPont;
    private javax.swing.JLabel titulo;
}