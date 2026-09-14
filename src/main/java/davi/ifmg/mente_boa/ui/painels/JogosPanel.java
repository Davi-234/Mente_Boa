package davi.ifmg.mente_boa.ui.painels;

import davi.ifmg.mente_boa.dao.JogoDAO;
import davi.ifmg.mente_boa.ui.Tela;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class JogosPanel extends JPanel {

    private final JScrollPane scrollPainel;
    private final JButton voltarMenu;

    public JogosPanel() {

        setLayout(new BorderLayout());

        JTable tabela = new JTable(criarTabela());
        tabela.setAutoCreateRowSorter(true);
        tabela.setAutoCreateRowSorter(true);
        tabela.setRowHeight(30);

        tabela.getTableHeader()
                .setReorderingAllowed(false);

        tabela.setFillsViewportHeight(true);

        scrollPainel
                = new JScrollPane(tabela);

        scrollPainel.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS
        );

        add(scrollPainel, BorderLayout.CENTER);

        voltarMenu = new JButton();
        voltarMenu.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        voltarMenu.setText("Voltar para o Menu");
        voltarMenu.setPreferredSize(new java.awt.Dimension(100, 30));
        voltarMenu.setMaximumSize(new java.awt.Dimension(100, 30));
        voltarMenu.setMinimumSize(new java.awt.Dimension(100, 30));
        voltarMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                voltarMenuAction(evt);
            }
        });

        add(voltarMenu, BorderLayout.SOUTH);
        setBorder(new EmptyBorder(0, 5, 0, 5));
    }

    public DefaultTableModel criarTabela() {
        DefaultTableModel modelo = new DefaultTableModel();

        modelo.addColumn("Código");
        modelo.addColumn("Apelido");
        modelo.addColumn("Data");
        modelo.addColumn("Pontos");

        for (var j : new JogoDAO().getAll()) {
            if (j != null) {
                modelo.addRow(
                        new Object[]{
                            j.getCodigo(),
                            j.getJogador().getApelido(),
                            j.getData(),
                            j.getPontos()
                        }
                );
            }
        }

        return modelo;
    }

    public void voltarMenuAction(ActionEvent evet) {
        Tela.mudarPanel("opc");
    }
}
