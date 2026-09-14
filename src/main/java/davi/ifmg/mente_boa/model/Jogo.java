package davi.ifmg.mente_boa.model;

public class Jogo {
    
    private String codigo;
    private int pontos;
    private Jogador jogador;
    private String data;

    public Jogo(String codigo, int pontos, Jogador jogador, String data) {
        this.codigo = codigo;
        this.pontos = pontos;
        this.jogador = jogador;
        this.data = data;
    }
    
    public void setPontos(int pts){
        pontos = pts;
    }
    
    public void setCodigo(String codigo){
        this.codigo = codigo;
    }
    
    public void setJogador(Jogador jogador){
        this.jogador = jogador;
    }
    
    public int getPontos(){
        return pontos;
    }
    
    public String getCodigo(){
        return codigo;
    }
    
    public Jogador getJogador(){
        return jogador;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
    
    public String toString(){
       return "Jogo: " + codigo + "\tCPF: " + jogador.getCPF() + "\tPontos: " + pontos + " Pts.";
    }
    
}
