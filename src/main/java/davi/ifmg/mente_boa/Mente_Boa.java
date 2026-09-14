package davi.ifmg.mente_boa;

import davi.ifmg.mente_boa.ui.Tela;

public class Mente_Boa {

    public static void main(String[] args) {
        var app = new Tela();
    }
}   
    // Versão antiga do MenteBoa, que funcionava no terminal, caso faça-se necessário testa-lo por esse metódo.
    /*
                
        Scanner input = new Scanner(System.in);

        String opc;

        //178.876.456-03 , 732.630.521-87 , 050.087.768-86 , 829.768.481-49 , 290.119.571-72 , 197.780.311-34 
        JogadorService jogadSRV = new JogadorService();
        JogoService jogSRV = new JogoService();
        RanqueService rankSRV = new RanqueService();
        
        do {
            System.out.print("Bem-Vindo ao MENTEBOA\n"
                    + "============MENU============\n"
                    + "1) Cadastrar-se;\n"
                    + "2) Jogar;\n"
                    + "3) Exibir os dados de todos os jogadores;\n"
                    + "4) Exibir os dados de todos os jogos;\n"
                    + "5) Quantos jogos um determinado jogador jogou;\n"
                    + "6) Maior pontuação obtida de um determinado jogador;\n"
                    + "7) Menor pontuação obtida de um determinado jogador;\n"
                    + "8) Exibir o ranque com os 5 melhores jogadores;\n"
                    + "9) Limpar toda a base de dados;\n"
                    + "10) Sair do jogo;\n"
                    + "============================;\n"
                    + "Digite a opção desejada: ");

            opc = input.next();
            System.out.println("");

            switch (opc) {
                case "1":
                    Pop_ups.telaCadastro();
                    break;

                case "2":
                    if (!jogadSRV.existeJogadores()) {
                        Pop_ups.telaDeErro("Não existem jogadores cadastrados.");
                        break;
                    }

                    String cpf = Pop_ups.pedirCPF("Digite o seu CPF: ");

                    if (cpf == null) {
                        break;
                    }

                    if (jogadSRV.getJogador(cpf) == null) {
                        break;
                    }

                    System.out.println(
                            "\n==========================================================REGRAS============================================================="
                            + "\n1-Os números não se repetem."
                            + "\n2-Não é preciso colacar os números na ordem em que apareceram."
                            + "\n3-Dependedo da dificuldade os números que podem ser sorteados, e a sua quantidade mudam."
                            + "\n=============================================================================================================================");

                    System.out.print("====================================================================\n"
                            + "Qual a dificuldade deseja escolher:\n"
                            + "1-Facíl / 0 à 11 / 8 números;\n"
                            + "2-Médio (Padrão) / 0 à 20 / 13 númeos;\n"
                            + "3-Difícil / 0 à 25 / 18 números;\n"
                            + "====================================================================\n"
                            + "Digite aqui: ");


                    int opcaoDaDificludade;
                    try {
                         opcaoDaDificludade = input.nextInt();
                    } catch (Exception e){
                         Pop_ups.telaDeErro("Isto não é um número.");
                         continue;
                    }

                    int numerosDoJogo[] = Util.sorteaNumeros(opcaoDaDificludade);

                    Pop_ups.numerosDoJogo(numerosDoJogo);
                    
                    int acertos = jogSRV.calculaAcertos(numerosDoJogo);

                    Jogo jogo = new Jogo();
                    jogo.setCodigo(jogSRV.geraCodigo(cpf));
                    jogo.setPontos(acertos);
                    jogo.setJogador(jogadSRV.getJogador(cpf));

                    insere no BD:
                    jogSRV.inserir(jogo);

                    Pop_ups.telaDeOutput("Acertos do jogador", "Tú divestes um total de acertos de " + acertos);

                    break;

                case "3":
                    if (!jogadSRV.existeJogadores()) {
                        Pop_ups.telaDeErro("Não existem jogadores cadastrados.");
                        break;
                    }

                    if (Pop_ups.telaDeConfirmação("Deseja imprimir os dados ?.") == false) {
                        break;
                    }

                    System.out.println("========================================================================"
                            + "\n\t\tListagem dos jogadores\n========================================================================");

                    System.out.print(jogadSRV.getAll());

                    System.out.println("========================================================================\n");

                    break;

                case "4":
                    if (!jogSRV.existeJogos()) {
                        Pop_ups.telaDeErro("Não existem jogadores cadastrados.");
                        break;
                    }

                    if (Pop_ups.telaDeConfirmação("Deseja imprimir os dados ?.") == false) {
                        Pop_ups.telaDeErro("Não existem jogos cadastrados.");
                        break;
                    }

                    System.out.println("========================================================================"
                            + "\n\t\tListagem dos jogos\n========================================================================");

                    System.out.print(jogSRV.getAll());

                    System.out.println("========================================================================\n");

                    break;

                case "5":
                    if (jogadSRV.existeJogadores() == false) {
                        Pop_ups.telaDeErro("Não existem jogadores cadastrados.");
                        break;
                    }

                    if (jogSRV.existeJogos() == false) {
                        Pop_ups.telaDeErro("Não existem jogos cadastrados.");
                        break;
                    }

                    String cpf5 = Pop_ups.pedirCPF("Digite o cpf do jogador: ");
                    
                    Pop_ups.telaDeOutput("Quantidade de jogos de um jogador", jogSRV.qtDeJogosDeUmJogador(cpf5));

                    break;

                case "6":
                    if (jogadSRV.existeJogadores() == false) {
                        Pop_ups.telaDeErro("Não existem jogadores cadastrados.");
                        break;
                    }

                    if (jogSRV.existeJogos() == false) {
                        Pop_ups.telaDeErro("Não existem jogos cadastrados.");
                        break;
                    }

                    String cpf6 = Pop_ups.pedirCPF("Digite o cpf do jogador: ");
                    
                    Pop_ups.telaDeOutput("Maior Pontuação", jogSRV.maiorPontuacaoDeUmJogador(cpf6));

                    break;

                case "7":
                    if (jogadSRV.existeJogadores() == false) {
                        Pop_ups.telaDeErro("Não existem jogadores cadastrados.");
                        break;
                    }

                    if (jogSRV.existeJogos() == false) {
                        Pop_ups.telaDeErro("Não existem jogos cadastrados.");
                        break;
                    }

                    String cpf7 = Pop_ups.pedirCPF("Digite o cpf do jogador: ");
                    
                    Pop_ups.telaDeOutput("Menor Pontuação", jogSRV.menorPontuacaoDeUmJogador(cpf7));

                    break;

                case "8":
                    if (jogadSRV.existeJogadores() == false) {
                        Pop_ups.telaDeErro("Não existem jogadores cadastrados.");
                        break;
                    }

                    if (jogSRV.existeJogos() == false) {
                        Pop_ups.telaDeErro("Não existem jogos cadastrados.");
                        break;
                    }

                    System.out.println("========================================================================"
                            + "\n\t\tRanking dos 5 Mestres:\n========================================================================");

                    System.out.print(new RanqueService().calcularRanque());

                    System.out.println("========================================================================\n");
                    break;

                case "9":
                    if (Pop_ups.telaDeConfirmação("Deseja limpar os dados da aplicação ?") == false) {
                        opc = "";
                        break;
                    }
                    
                    jogadSRV.limparDados();
                    jogSRV.limparDados();
                    rankSRV.limparDados();

                    break;

                case "10":
                    if (Pop_ups.telaDeConfirmação("Deseja encerrar a aplicação?") == false) {
                        opc = "";
                        break;
                    }
                     
                    Pop_ups.finaliza();
                    Thread.sleep(16000); 
                    break;

                default:
                    Pop_ups.telaDeErro("Esta opção não existe.");
                    break;
            }

        } while (true);
        
    }
     */
