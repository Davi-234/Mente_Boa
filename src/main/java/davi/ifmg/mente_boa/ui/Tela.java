package davi.ifmg.mente_boa.ui;

import davi.ifmg.mente_boa.ui.painels.JogadoresPanel;
import davi.ifmg.mente_boa.ui.painels.OpcPanel;
import davi.ifmg.mente_boa.ui.painels.MenuPanel;
import davi.ifmg.mente_boa.ui.painels.JogosPanel;
import davi.ifmg.mente_boa.ui.painels.RanquePanel;
import davi.ifmg.mente_boa.ui.painels.jogar.JogarPanel;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Tela extends javax.swing.JFrame {

    public static JPanel cards;

    public Tela() {

        setUndecorated(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("MenteBoa");

        try {
            setIconImage(
                    new ImageIcon(
                            getClass().getResource("/imagens/icone.png")
                    ).getImage()
            );
        } catch (Exception e) {
            System.err.println("Não foi possível carregar o icone da janela.\n" + e);
        }

        Fundo fundo = new Fundo();
        fundo.setLayout(new BorderLayout());

        cards = new JPanel(new CardLayout());
        cards.setOpaque(false);

        cards.add(new MenuPanel(), "menu");
        cards.add(new OpcPanel(), "opc");

        fundo.add(cards, BorderLayout.CENTER);

        setContentPane(fundo);

        CardLayout cl = (CardLayout) cards.getLayout();
        cl.show(cards, "menu");

        GraphicsDevice device
                = GraphicsEnvironment
                        .getLocalGraphicsEnvironment()
                        .getDefaultScreenDevice();

        device.setFullScreenWindow(this);
        
        setVisible(true);
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1490, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 602, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public static void mudarPanel(String nome) {
        java.awt.CardLayout cl = (java.awt.CardLayout) cards.getLayout();
        cl.show(cards, nome);
    }

    public static void jogar(String cpf) {
        cards.add(
                new JogarPanel(cpf),
                "jogar"
        );
        java.awt.CardLayout cl
                = (java.awt.CardLayout) cards.getLayout();

        cl.show(cards, "jogar");
    }

    public static void mostrar_ranque() {
        for (java.awt.Component comp : cards.getComponents()) {
            if (comp instanceof RanquePanel) {
                cards.remove(comp);
            }
        }

        cards.add(new RanquePanel(), "ranque");
        cards.revalidate();
        cards.repaint();

        java.awt.CardLayout cl = (java.awt.CardLayout) cards.getLayout();
        cl.show(cards, "ranque");
    }

    public static void mostrar_jogadores() {
        for (java.awt.Component comp : cards.getComponents()) {
            if (comp instanceof JogadoresPanel) {
                cards.remove(comp);
            }
        }

        cards.add(new JogadoresPanel(), "mostrarJogadores");
        cards.revalidate();
        cards.repaint();

        java.awt.CardLayout cl = (java.awt.CardLayout) cards.getLayout();
        cl.show(cards, "mostrarJogadores");
    }

    public static void mostrar_jogos() {
        for (java.awt.Component comp : cards.getComponents()) {
            if (comp instanceof JogosPanel) {
                cards.remove(comp);
            }
        }

        cards.add(new JogosPanel(), "mostrarJogos");
        cards.revalidate();
        cards.repaint();

        java.awt.CardLayout cl = (java.awt.CardLayout) cards.getLayout();
        cl.show(cards, "mostrarJogos");
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}

class Fundo extends JPanel {

    private final java.awt.Image fundo = new javax.swing.ImageIcon(getClass().getResource("/imagens/fundo.jpg")).getImage();

    @Override
    protected void paintComponent(java.awt.Graphics g) {
        super.paintComponent(g);
        g.drawImage(fundo, 0, 0, getWidth(), getHeight(), this);
    }
}
