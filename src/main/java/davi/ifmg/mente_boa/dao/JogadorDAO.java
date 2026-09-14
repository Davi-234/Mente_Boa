package davi.ifmg.mente_boa.dao;

import davi.ifmg.mente_boa.bd.BancoDeDados;
import davi.ifmg.mente_boa.model.Jogador;
import davi.ifmg.mente_boa.utill.Util;

public class JogadorDAO {
    
    public JogadorDAO() {
        
    }
    
    public void limparDados(){
       Jogador jogadores[] = getAll();
       
        for (int i = 0; i < jogadores.length; i++) {
             jogadores[i] = null;         
        }
    }
    
    public boolean remover(String cpf){
       Jogador jogadores[] = getAll();
       
        for (int i = 0; i < jogadores.length; i++) {
            if (jogadores[i] != null && (Util.limaprCPF(jogadores[i].getCPF()).equals(Util.limaprCPF(cpf)))){
                jogadores[i] = null;
                return true;
            }
        }
        
        return false;
    }
    
    public boolean alterar(String cpf,Jogador jogador){
       Jogador jogadores[] = getAll();
       
        for (int i = 0; i < jogadores.length; i++) {
             if (jogadores[i] != null && (Util.limaprCPF(jogadores[i].getCPF()).equals(Util.limaprCPF(cpf)))){
                 jogadores[i] = jogador;
                 return true;
             }
        }
        
        return false;
    }
    
    public Jogador getJogador(String cpf){
        Jogador jogadores[] = getAll();
        
        for (int i = 0; i < jogadores.length; i++) {
             if (jogadores[i] != null && (Util.limaprCPF(jogadores[i].getCPF()).equals(Util.limaprCPF(cpf))))
                 return jogadores[i];
        }
        
        return null;
    }
    
    public Jogador[] getAll(){
       return BancoDeDados.getInstance().getJogadores();
    }
    
    public boolean inserir(Jogador jogador){
        Jogador jogadores[] = getAll();
       
        for (int i = 0; i < jogadores.length; i++) {
             if (jogadores[i] == null){
                 jogadores[i] = jogador;
                 return true;
             }
        }
        
        return false;
    }
    
}

    
