package davi.ifmg.mente_boa.ui.painels.jogar;

import davi.ifmg.mente_boa.ui.Pop_ups;
import davi.ifmg.mente_boa.ui.Tela;
import davi.ifmg.mente_boa.utill.TimerJogo;
import davi.ifmg.mente_boa.model.Jogador;
import davi.ifmg.mente_boa.model.Jogo;
import davi.ifmg.mente_boa.service.JogadorService;
import davi.ifmg.mente_boa.service.JogoService;
import davi.ifmg.mente_boa.sonoro.Sons;
import davi.ifmg.mente_boa.utill.Util;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.time.LocalDate;
import java.util.Arrays;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.text.NumberFormatter;

public class JogarPanel extends JPanel {

    public static JPanel cards;

    public JogarPanel(String cpf_jogador_atual) {
        Jogador jogador_atual = new JogadorService().getJogador(cpf_jogador_atual);

        setLayout(new BorderLayout());

        cards = new JPanel(new java.awt.CardLayout());

        cards.add(
                new SelecionarDificuldade(jogador_atual),
                "selecionarDificuldade"
        );

        add(cards, BorderLayout.CENTER);
        cards.setOpaque(false);

        mudarPanel("selecionarDificuldade");
        setOpaque(false);
    }

    // Todos os métodos abaixo são estáticos porque operam sempre sobre o
    // MESMO "cards" que já está de fato visível na tela (criado uma única
    // vez, no construtor acima). Criar "new JogarPanel(...)" só para trocar
    // de card cria uma instância órfã, com o SEU PRÓPRIO "cards" novo, que
    // nunca é adicionada a lugar nenhum visível — por isso a tela não mudava.
    public static void mudarPanel(String nome) {
        java.awt.CardLayout cl = (java.awt.CardLayout) cards.getLayout();
        cl.show(cards, nome);
    }

    public static void mostrar_numeros_sorteados(int nivel_dificuldade, Jogador jogador_atual) {
        cards.add(
                new NumerosSorteados(Util.sorteaNumeros(nivel_dificuldade), nivel_dificuldade, jogador_atual),
                "telaNumeros"
        );
        mudarPanel("telaNumeros");
    }

    public static void reninciar() {
        mudarPanel("selecionarDificuldade");
    }

    public static void jogar(int nivel_dificuldade, int numeros[], Jogador jogador_atual) {
        cards.add(
                new Jogar(nivel_dificuldade, jogador_atual, numeros),
                "jogar"
        );
        mudarPanel("jogar");
    }
}

class NumerosSorteados extends JPanel {

    private final javax.swing.JButton saida;
    private final javax.swing.JLabel titulo;
    private final TimerJogo timer;
    private final Jogador jogador_atual;

    public NumerosSorteados(int[] numeros, int nivel_dificuldade, Jogador jogador_atual) {
        this.jogador_atual = jogador_atual;
        setOpaque(false);
        this.saida = new javax.swing.JButton();
        this.titulo = new javax.swing.JLabel();

        saida.setFont(new java.awt.Font("Segoe UI", 0, 22)); // NOI18N
        saida.setText("Voltar ao Menu");
        saida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saida_ActionPerformed(evt);
            }
        });
        saida.setPreferredSize(new Dimension(900, 50));
        saida.setMaximumSize(new Dimension(1000, 60));
        saida.setMinimumSize(new Dimension(800, 40));

        titulo.setFont(new java.awt.Font("Yu Gothic UI Semilight", 0, 60)); // NOI18N
        titulo.setForeground(new java.awt.Color(0, 0, 0));
        titulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titulo.setText("MenteBoa");

        // ------------------------------------------------------------------ //
        this.setLayout(new BorderLayout());

        // Título
        JPanel painelTitulo = new JPanel();
        painelTitulo.setOpaque(false);
        painelTitulo.add(titulo);

        add(painelTitulo, BorderLayout.NORTH);

        // Display números
        JPanel painelCentro = new JPanel(new BorderLayout());

        JPanel container_display_numbers_e_contador = new JPanel(new BorderLayout());

        // Container auxilíar:
        JPanel container_display_numbers_e_contador_aux
                = new JPanel();

        container_display_numbers_e_contador_aux.setLayout(
                new BoxLayout(
                        container_display_numbers_e_contador_aux,
                        BoxLayout.Y_AXIS
                )
        );
        JLabel display_numbers = new JLabel(Arrays.toString(numeros));
        display_numbers.setFont(new Font("SansSerif", Font.BOLD, 34));
        display_numbers.setOpaque(true);
        display_numbers.setBackground(Color.white);
        display_numbers.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel display_contador = new JLabel("Você tem 10 segundos para decorar os números...");
        display_contador.setFont(new Font("SansSerif", Font.ITALIC, 28));
        display_contador.setOpaque(true);
        display_contador.setBackground(Color.white);
        display_contador.setHorizontalAlignment(SwingConstants.CENTER);

        timer = new TimerJogo(10);

        if (nivel_dificuldade == 3) {
            timer.setSegundos(20);
        }

        timer.setAoAtualizar(segundos -> {

            display_contador.setText(
                    "Você tem " + segundos
                    + " segundos para decorar os números..."
            );

        });

        timer.setAoTerminar(() -> {

            display_contador.setText(
                    "Tempo esgotado!"
            );
            JogarPanel.jogar(nivel_dificuldade, numeros, this.jogador_atual);
        });

        timer.iniciar();

        display_numbers.setAlignmentX(CENTER_ALIGNMENT);
        display_contador.setAlignmentX(CENTER_ALIGNMENT);

        container_display_numbers_e_contador_aux.add(Box.createVerticalGlue());
        container_display_numbers_e_contador_aux.add(display_numbers);
        container_display_numbers_e_contador_aux.add(Box.createVerticalStrut(20));
        container_display_numbers_e_contador_aux.add(display_contador);
        container_display_numbers_e_contador_aux.add(Box.createVerticalGlue());
        container_display_numbers_e_contador_aux.setOpaque(false);

        //
        container_display_numbers_e_contador.add(container_display_numbers_e_contador_aux, BorderLayout.CENTER);
        container_display_numbers_e_contador.setOpaque(false);

        painelCentro.add(container_display_numbers_e_contador, BorderLayout.CENTER);
        painelCentro.setOpaque(false);

        add(painelCentro, BorderLayout.CENTER);

        // Botão de saída
        JPanel painelSaida = new JPanel();
        painelSaida.setOpaque(false);
        painelSaida.add(saida);

        add(painelSaida, BorderLayout.SOUTH);
    }

    private void saida_ActionPerformed(java.awt.event.ActionEvent evt) {
        Sons.clicar();
        if (Pop_ups.telaDeConfirmação("Deseja sair do jogo?")) {
            Tela.mudarPanel("menu");
            JogarPanel.reninciar();
        }
    }
}

class Jogar extends JPanel {

    private final javax.swing.JButton saida;
    private final javax.swing.JButton confirmar;
    private final javax.swing.JLabel titulo;
    private final Jogador jogador_atual;

    private int[] numerosDoJogo;
    private int[] numerosDigitados;

    private JFormattedTextField[] inputs;

    public Jogar(int dificuldade, Jogador jogador_atual, int[] numerosDoJogo) {
        this.numerosDoJogo = numerosDoJogo;
        this.jogador_atual = jogador_atual;
        setOpaque(false);
        this.saida = new javax.swing.JButton();
        this.confirmar = new javax.swing.JButton();
        this.titulo = new javax.swing.JLabel();

        saida.setFont(new java.awt.Font("Segoe UI", 0, 22)); // NOI18N
        saida.setText("Voltar ao Menu");
        saida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saida_ActionPerformed(evt);
            }
        });
        saida.setPreferredSize(new Dimension(900, 50));
        saida.setMaximumSize(new Dimension(1000, 60));
        saida.setMinimumSize(new Dimension(800, 40));

        confirmar.setFont(new java.awt.Font("Segoe UI", 0, 22)); // NOI18N
        confirmar.setText("Confirmar");
        confirmar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                confirmar_numeros_ActionPerformed(evt);
            }
        });
        confirmar.setPreferredSize(new Dimension(900, 50));
        confirmar.setMaximumSize(new Dimension(1000, 60));
        confirmar.setMinimumSize(new Dimension(800, 40));

        titulo.setFont(new java.awt.Font("Yu Gothic UI Semilight", 0, 60)); // NOI18N
        titulo.setForeground(new java.awt.Color(0, 0, 0));
        titulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titulo.setText("MenteBoa");

        // ------------------------------------------------------------------ //
        this.setLayout(new BorderLayout());

        // Título
        JPanel painelTitulo = new JPanel();
        painelTitulo.setOpaque(false);
        painelTitulo.add(titulo);

        add(painelTitulo, BorderLayout.NORTH);

        // Display números
        JPanel painelCentro = new JPanel(new GridBagLayout());
        painelCentro.setOpaque(false);

        JPanel grid_numbers = new JPanel();

        int numero_inputs;

        final int tamanho_inputs = 130;
        int colunas;
        int linhas;

        final int espacamento_entre_inputs = 20;
        int maximo_valor_input; // valor máximo dos inputs, deriva da função com.mycompany.menteboa.utill.Util.sorteaNumeros()

        switch (dificuldade) {

            case 1:
                colunas = 3;
                linhas = 3;
                numero_inputs = 9;
                maximo_valor_input = 12;
                break;

            case 2:
                colunas = 4;
                linhas = 3;
                numero_inputs = 12;
                maximo_valor_input = 21;
                break;

            case 3:
                colunas = 6;
                linhas = 3;
                numero_inputs = 18;
                maximo_valor_input = 30;
                break;

            default:
                throw new IllegalArgumentException(
                        "Dificuldade inexistente"
                );
        }

        grid_numbers.setLayout(
                new GridLayout(
                        linhas,
                        colunas,
                        espacamento_entre_inputs,
                        espacamento_entre_inputs
                )
        );

        int largura
                = colunas * tamanho_inputs
                + (colunas - 1) * espacamento_entre_inputs;

        int altura
                = linhas * tamanho_inputs
                + (linhas - 1) * espacamento_entre_inputs;

        grid_numbers.setPreferredSize(
                new Dimension(largura, altura)
        );

        NumberFormatter formatter = new NumberFormatter();
        formatter.setValueClass(Integer.class);
        formatter.setMinimum(0);
        formatter.setMaximum(maximo_valor_input);
        formatter.setAllowsInvalid(false);

        inputs = new JFormattedTextField[numero_inputs];
        for (int i = 0; i < numero_inputs; i++) {

            inputs[i] = new JFormattedTextField(formatter);

            inputs[i].setFont(
                    new Font("Segoe UI", Font.PLAIN, 40)
            );

            inputs[i].setHorizontalAlignment(
                    SwingConstants.CENTER
            );

            grid_numbers.add(inputs[i]);
        }

        grid_numbers.setOpaque(false);
        painelCentro.add(grid_numbers);
        painelCentro.setBackground(new Color(128, 128, 128, 100));
        add(painelCentro, BorderLayout.CENTER);

        JPanel painelBotoes = new JPanel();
        painelBotoes.setOpaque(false);
        painelBotoes.setLayout(new BoxLayout(painelBotoes, BoxLayout.Y_AXIS));

        confirmar.setPreferredSize(new Dimension(300, 50));
        saida.setPreferredSize(new Dimension(300, 50));

        confirmar.setAlignmentX(Component.CENTER_ALIGNMENT);
        saida.setAlignmentX(Component.CENTER_ALIGNMENT);

        painelBotoes.add(confirmar);
        painelBotoes.add(Box.createVerticalStrut(10));
        painelBotoes.add(saida);

        add(painelBotoes, BorderLayout.SOUTH);
    }

    private int[] preencher_numerosDigitados(JFormattedTextField[] inputs) {
        int numeros[] = new int[inputs.length];

        for (int i = 0; i < numeros.length; i++) {
            numeros[i] = Integer.parseInt(inputs[i].getText());
        }

        return numeros;
    }

    private void saida_ActionPerformed(java.awt.event.ActionEvent evt) {
        Sons.clicar();
        if (Pop_ups.telaDeConfirmação("Deseja sair do jogo?")) {
            Tela.mudarPanel("menu");
            JogarPanel.reninciar();
        }
    }

    private void confirmar_numeros_ActionPerformed(java.awt.event.ActionEvent evt) {
        Sons.clicar();
        if (Pop_ups.telaDeConfirmação("Deseja confirmar os números?")) {
            try {
                numerosDigitados = preencher_numerosDigitados(inputs);
            } catch (NumberFormatException e) {
                Pop_ups.telaDeErro("Preencha todos os números antes de confirmar.");
                return;
            }

            String codigoJogo = new JogoService().gerarCodigo(jogador_atual.getCPF());
            int acertos = Util.calcularAcertos(numerosDoJogo, numerosDigitados);
            new JogoService().inserir(new Jogo(codigoJogo, acertos, jogador_atual, LocalDate.now().toString()));

            Tela.mudarPanel("menu");
            JogarPanel.reninciar();
        }
    }
}

class SelecionarDificuldade extends JPanel {

    private final javax.swing.JButton btn_easy_difficult;
    private final javax.swing.JButton btn_normal_difficult;
    private final javax.swing.JButton btn_hard_difficult;
    private final javax.swing.JButton saida;
    private final javax.swing.JLabel titulo;
    private final Jogador jogador_atual;

    public SelecionarDificuldade(Jogador jogador_atual) {
        this.jogador_atual = jogador_atual;
        setOpaque(false);
        this.btn_easy_difficult = new javax.swing.JButton();
        this.btn_normal_difficult = new javax.swing.JButton();
        this.btn_hard_difficult = new javax.swing.JButton();
        this.saida = new javax.swing.JButton();
        this.titulo = new javax.swing.JLabel();

        // ------------------------------------------------------------------ //
        btn_easy_difficult.setFont(new java.awt.Font("Segoe UI", 0, 22)); // NOI18N
        btn_easy_difficult.setText("Fácil");
        btn_easy_difficult.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                facil_ActionPerformed(evt);
            }
        });

        btn_normal_difficult.setFont(new java.awt.Font("Segoe UI", 0, 22)); // NOI18N
        btn_normal_difficult.setText("Médio");
        btn_normal_difficult.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                medio_ActionPerformed(evt);
            }
        });

        btn_hard_difficult.setFont(new java.awt.Font("Segoe UI", 0, 22)); // NOI18N
        btn_hard_difficult.setText("Difícil");
        btn_hard_difficult.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dificil_ActionPerformed(evt);
            }
        });

        saida.setFont(new java.awt.Font("Segoe UI", 0, 22)); // NOI18N
        saida.setText("Voltar ao Menu");
        saida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saida_ActionPerformed(evt);
            }
        });
        saida.setPreferredSize(new Dimension(900, 50));
        saida.setMaximumSize(new Dimension(1000, 60));
        saida.setMinimumSize(new Dimension(800, 40));

        titulo.setFont(new java.awt.Font("Yu Gothic UI Semilight", 0, 60)); // NOI18N
        titulo.setForeground(new java.awt.Color(0, 0, 0));
        titulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titulo.setText("MenteBoa");

        // ------------------------------------------------------------------ //
        Dimension tamanhoBotao = new Dimension(400, 60);

        for (var btn : new JButton[]{
            btn_easy_difficult, btn_hard_difficult, btn_normal_difficult
        }) {
            btn.setPreferredSize(tamanhoBotao);
            btn.setMaximumSize(tamanhoBotao);
            btn.setMinimumSize(tamanhoBotao);
        }
        this.setLayout(new BorderLayout());

        JPanel conjuntoButtons = new JPanel();
        conjuntoButtons.setLayout(new BoxLayout(conjuntoButtons, BoxLayout.X_AXIS));
        conjuntoButtons.setOpaque(false);

        conjuntoButtons.add(Box.createHorizontalGlue());
        conjuntoButtons.add(btn_easy_difficult);
        conjuntoButtons.add(Box.createHorizontalStrut(20));
        conjuntoButtons.add(btn_normal_difficult);
        conjuntoButtons.add(Box.createHorizontalStrut(20));
        conjuntoButtons.add(btn_hard_difficult);
        conjuntoButtons.add(Box.createHorizontalGlue());

        // Título
        JPanel painelTitulo = new JPanel();
        painelTitulo.setOpaque(false);
        painelTitulo.add(titulo);

        add(painelTitulo, BorderLayout.NORTH);

        // Botões
        JPanel painelCentro = new JPanel();
        painelCentro.setOpaque(false);
        painelCentro.setLayout(new BorderLayout());
        painelCentro.add(conjuntoButtons, BorderLayout.CENTER);
        add(painelCentro, BorderLayout.CENTER);

        // Botão de saída
        JPanel painelSaida = new JPanel();
        painelSaida.setOpaque(false);
        painelSaida.add(saida);

        add(painelSaida, BorderLayout.SOUTH);
    }

    private void dificil_ActionPerformed(java.awt.event.ActionEvent evt) {
        Sons.clicar();
        if (Pop_ups.telaDeConfirmação("São 18 números;\nDe 0 à 30\nDeseja selecionar a dificuldade \"Difícil\"?")) {
            JogarPanel.mostrar_numeros_sorteados(3, jogador_atual);
        }
    }

    private void medio_ActionPerformed(java.awt.event.ActionEvent evt) {
        Sons.clicar();
        if (Pop_ups.telaDeConfirmação("São 12 números;\nDe 0 à 21\nDeseja selecionar a dificuldade \"Normal\"?")) {
            JogarPanel.mostrar_numeros_sorteados(2, jogador_atual);
        }
    }

    private void facil_ActionPerformed(java.awt.event.ActionEvent evt) {
        Sons.clicar();
        if (Pop_ups.telaDeConfirmação("São 9 números;\nDe 0 à 12\nDeseja selecionar a dificuldade \"Facíl?\"")) {
            JogarPanel.mostrar_numeros_sorteados(1, jogador_atual);
        }
    }

    private void saida_ActionPerformed(java.awt.event.ActionEvent evt) {
        Sons.clicar();
        if (Pop_ups.telaDeConfirmação("Deseja sair do jogo?")) {
            Tela.mudarPanel("menu");
            JogarPanel.reninciar();
        }
    }
}
