package davi.ifmg.mente_boa.dao;

import davi.ifmg.mente_boa.bd.BancoDeDados;
import davi.ifmg.mente_boa.model.Jogo;

public class JogoDAO {

    public JogoDAO() {  
        
    }
    
    public void limparDados(){
       Jogo jogos[] = getAll();
       
        for (int i = 0; i < jogos.length; i++) {
             jogos[i] = null;         
        }
    }
    
    public boolean inserir(Jogo jogo){
        Jogo jogos[] = getAll();
        
        for (int i = 0; i < jogos.length; i++) {
            if (jogos[i] == null){
                jogos[i] = jogo;
                return true;
            }
        }
        return false;
    }
    
    public boolean remover(String codigo){
        Jogo jogos[] = getAll();
       
        for (int i = 0; i < jogos.length; i++) {
             if (jogos[i] != null && jogos[i].getCodigo().equals(codigo)){
                 jogos[i] = null;
                 return true;
             }
        }
        
        return false;
    }
    
    public boolean alterar(String codigo,Jogo jogo){
       Jogo jogos[] = getAll();
       
        for (int i = 0; i < jogos.length; i++) {
             if (jogos[i] != null && jogos[i].getCodigo().equals(codigo)){
                 jogos[i] = jogo;
                 return true;
             }
        }
        
        return false;
    }
    
    public Jogo getJogo(String codigo){
        Jogo jogos[] = getAll();
        
        for (int i = 0; i < jogos.length; i++) {
             if (jogos[i] != null && jogos[i].getCodigo().equals(codigo))
                 return jogos[i];
        }
        
        return null;
    }
    
    public Jogo[] getAll(){
       return BancoDeDados.getInstance().getJogos();
    }
}