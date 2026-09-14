package davi.ifmg.mente_boa.service;

import davi.ifmg.mente_boa.dao.JogoDAO;
import davi.ifmg.mente_boa.model.Jogo;
import davi.ifmg.mente_boa.utill.Util;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class JogoService {

    private JogoDAO dao;

    public JogoService() {
        dao = new JogoDAO();
    }

    public String getAll() {
        StringBuilder sb = new StringBuilder();

        Jogo jogos[] = dao.getAll();

        for (int i = 0; i < jogos.length; i++) {
            if (jogos[i] != null) {
                sb.append((i + 1) + "°) " + jogos[i].toString() + "\n");
            }
        }

        return sb.toString();
    }

    public String inserir(Jogo jogo) {
        Jogo jogod[] = dao.getAll();

        for (Jogo j : jogod) { //for-each , o jsvs peg o primero valor do vetor e coloca na variável J (as duas são do mesmo tipo) e depois faz o que está no corpo
            if (j != null && jogo.getCodigo().equals(j.getCodigo())) {
                return "Jogo já existe.";
            }

        }

        dao.inserir(jogo);
        return "Jogo inserido.";
    }

    public boolean alterar(String codigo, Jogo jogo) {
        Jogo jogos[] = dao.getAll();

        for (int i = 0; i < jogos.length; i++) {
            //garante que o codigo não é repetido. 
            if (jogos[i].getCodigo().equals(jogo.getCodigo())) {
                return false;
            }
        }

        dao.alterar(codigo, jogo);
        return true;
    }

    public boolean remover(String codigo) {
        if (codigo == null) {
            return false;
        }

        Jogo jogos[] = dao.getAll();

        for (int i = 0; i < jogos.length; i++) {
            if (jogos[i] != null && jogos[i].getCodigo().equals(codigo)) {
                dao.remover(codigo);
            }
        }

        return true;
    }

    public Jogo getJogo(String codigo) {
        Jogo jogos[] = dao.getAll();

        for (int i = 0; i < jogos.length; i++) {
            if (jogos[i].getCodigo().equals(codigo)) {
                return jogos[i];
            }
        }

        return null;
    }

    public String qtDeJogosDeUmJogador(String cpf) {
        Jogo jogos[] = dao.getAll();
        int contador = 0;

        if (Util.isCPFValido(cpf) == false) {
            return "Este CPF é inválido";
        }

        for (int i = 0; i < jogos.length; i++) {
            if (jogos[i] != null) {
                if (Util.limaprCPF(jogos[i].getJogador().getCPF()).equals(Util.limaprCPF(cpf))) {
                    contador++;
                }
            }
        }

        if (new JogadorService().getJogador(cpf) != null) {
            String cpfFormatado = Util.formatarCPF(cpf);
            return "O jogador CPF: " + cpfFormatado + " têm um total de " + contador + " jogos.";
        } else {
            return "O CPF está vazio.";
        }
    }

    public String maiorPontuacaoDeUmJogador(String cpf) {
        int maiorPontuacao = 0;

        Jogo jogos[] = dao.getAll();

        if (Util.isCPFValido(cpf) == false) {
            return ("Este CPF é inválido");
        }

        if (new JogadorService().getJogador(cpf) == null) {

            return "O CPF dado não existe no cadastro.";
        }

        for (int i = 0; i < jogos.length; i++) {
            if (jogos[i] != null) {
                if (Util.limaprCPF(jogos[i].getJogador().getCPF()).equals(Util.limaprCPF(cpf))) {
                    if (jogos[i].getPontos() > maiorPontuacao) {
                        maiorPontuacao = jogos[i].getPontos();
                    }
                }
            }
        }

        if (new JogadorService().getJogador(cpf) != null) {
            String cpfFormatado = Util.formatarCPF(cpf);

            return "A maior pontuação do jogador do CPF " + cpfFormatado + " é de " + maiorPontuacao + " Pts.";
        } else {
            return null;
        }
    }

    public String menorPontuacaoDeUmJogador(String cpf) {
        int menorPontuacao = 100;

        Jogo jogos[] = dao.getAll();

        if (Util.isCPFValido(cpf) == false) {
            return ("Este CPF é inválido");
        }

        if (new JogadorService().getJogador(cpf) == null) {
            return ("O CPF dado não existe no cadastro.");
            
        }

        for (int i = 0; i < jogos.length; i++) {
            if (jogos[i] != null) {
                if (Util.limaprCPF(jogos[i].getJogador().getCPF()).equals(Util.limaprCPF(cpf))) {
                    if (jogos[i].getPontos() < menorPontuacao) {
                        menorPontuacao = jogos[i].getPontos();
                    }
                }
            }
        }

        if (new JogadorService().getJogador(cpf) != null) {
            String cpfFormatado = Util.formatarCPF(cpf);

            return "A menor pontuação do jogador do CPF " + cpfFormatado + " é de " + menorPontuacao + " Pts.";
        } else {
            return "O CPF está vazio.";
        }
    }

    public void limparDados() {
        dao.limparDados();
    }

    public boolean existeJogos() {
        Jogo[] jogos = dao.getAll();

        for (int i = 0; i < jogos.length; i++) {
            if (jogos[i] != null) {
                return true;
            }
        }

        return false;
    }

    public String gerarCodigo(String cpf) {
        String codigo;
        JogadorService jogadSRV = new JogadorService();

        //Cria um código para o jogo/-------------------------------
        LocalDateTime localTime = LocalDateTime.now();

        DateTimeFormatter formatado = DateTimeFormatter.ofPattern("dd-MM-yyyy - HH:mm:ss /");

        //Formata os dados para formartar o código;
        codigo = jogadSRV.getJogador(cpf).getApelido() + " /";
        codigo += formatado.format(localTime);
        codigo += " - " + Locale.getDefault().getCountry();
        //----------------------------------------------------------

        return codigo;
    }

    public static int calcularAcertos(int numerosDoJogo[], int numeroDigitado[]) {
        int acertos = 0;
        
        for (int i = 0; i < numerosDoJogo.length; i++) {
            //Calcula o número de acertos
            int j = 0;
            while (j < numerosDoJogo.length) {
                if (numerosDoJogo[i] == numeroDigitado[j++]) {
                    acertos++;
                    break;
                }
            }
        }

        return acertos;
    }
}
