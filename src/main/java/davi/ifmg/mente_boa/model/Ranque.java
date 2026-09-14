package davi.ifmg.mente_boa.model;

public class Ranque {
    private int quantidade;
    private float media;
    private Jogador jogador;
    private int pontos;

    public Ranque() {
        
    }

    public Ranque(int quantidade, float media, Jogador jogador, int pontos) {
        this.quantidade = quantidade;
        this.media = media;
        this.jogador = jogador;
        this.pontos = pontos;
    }

    public void setJogador(Jogador jogador) {
        this.jogador = jogador;
    }

    public void setMedia(float media) {
        this.media = media;
    }

    public void setPontos(int pontos) {
        this.pontos = pontos;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public Jogador getJogador() {
        return jogador;
    }

    public int getPontos() {
        return pontos;
    }

    public float getMedia() {
        return media;
    }
    
    @Override
    public String toString() {
        return jogador.getApelido() + "  ► Média: " + media;
    }
}
