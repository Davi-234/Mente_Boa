package davi.ifmg.mente_boa.service;

import davi.ifmg.mente_boa.dao.JogadorDAO;
import davi.ifmg.mente_boa.model.Jogador;
import davi.ifmg.mente_boa.utill.Util;

public class JogadorService {

    private JogadorDAO dao;

    public JogadorService() {
        dao = new JogadorDAO();
    }

    public String getAll() {
        StringBuilder sb = new StringBuilder();
        
        Jogador jogadores[] = dao.getAll();
        
        for (int i = 0; i < jogadores.length; i++) {
            if (jogadores[i] != null)
               sb.append((i+1) + "°) " + jogadores[i].toString()  + "\n");
        }
        
        return sb.toString();
        
    }

    public String inserir(Jogador jogador) {
        if (!Util.isCPFValido(jogador.getCPF())) {
            return "CPF inválido";
        }

        Jogador jogadores[] = dao.getAll();
        
        for (Jogador j : jogadores) { //for-each , o jsvs peg o primero valor do vetor e coloca na variável J (as duas são do mesmo tipo) e depois faz o que está no corpo
            if (j != null && (Util.limaprCPF(j.getCPF()).equals(Util.limaprCPF(jogador.getCPF())))) {
                return "Jogador já existe.";
            }

            if (j != null && jogador.getApelido().equals(j.getApelido())) {
                return "Apelido já existe.";
            }

        }

        String cpfFormatado = Util.formatarCPF(jogador.getCPF());
        jogador.setCPF(cpfFormatado);
        
        if (dao.inserir(jogador)) return "O cadastro foi feito corretamente";
        else return "Algo deu Errado";
    }

    public boolean alterar(String cpf /*cpf do jogador que vai ser alterado*/, Jogador jogador/*Novo jogador*/) {
        if (!Util.isCPFValido(jogador.getCPF())) {
            return false;
        }

        if (!Util.isCPFValido(cpf)) {
            return false;
        }

        Jogador jogadores[] = dao.getAll();
        
        for (Jogador j : jogadores) { //for-each , o jsvs peg o primero valor do vetor e coloca na variável J (as duas são do mesmo tipo) e depois faz o que está no corpo
            //garante que o cpf não é repetido
            if (Util.limaprCPF(j.getCPF()).equals(Util.limaprCPF(jogador.getCPF()))) {
                return false;
            }

            //garante que o apelio não é repetido
            if (jogador.getApelido().equals(j.getApelido())) {
                return false;
            }

        }
        
        String cpfFormatado = Util.formatarCPF(jogador.getCPF());
        jogador.setCPF(cpfFormatado);

        dao.alterar(cpf, jogador);
        return true;
    }

    public boolean remover(String cpf) {
        if (Util.isCPFValido(cpf) == false) {
            return false;
        }

        Jogador jogadores[] = dao.getAll();

        for (int i = 0; i < jogadores.length; i++) {
            if (Util.limaprCPF(jogadores[i].getCPF()).equals(Util.limaprCPF(cpf))) {
                dao.remover(cpf);
            }
        }

        return true;
    }

    public Jogador getJogador(String cpf) {
        if (cpf == null) {
            return null;
        }
        
        if (!Util.isCPFValido(cpf)) {
            return null;
        }
        
        Jogador jogadores[] = dao.getAll();

        for (int i = 0; i < jogadores.length; i++) {
            if (Util.limaprCPF(jogadores[i].getCPF()).equals(Util.limaprCPF(cpf))) {
                return jogadores[i];
            }
        }

        return null;
    }

    public void limparDados() {
        dao.limparDados();
    }
    
    public boolean existeJogadores() {
        Jogador[] jogadores = dao.getAll();
        
        for (int i = 0; i < jogadores.length; i++) {
            if (jogadores[i] != null)
               return true;
        }
        
        return false;
    }
    
}
