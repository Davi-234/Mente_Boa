package davi.ifmg.mente_boa.bd;

import davi.ifmg.mente_boa.model.Jogador;
import davi.ifmg.mente_boa.model.Jogo;
import davi.ifmg.mente_boa.model.Ranque;

public class BancoDeDados {

    private final int tamJogadores = 20;
    private final int tamJogos = 200;
    private final int tamRanque = 5;
    private static Jogador[] jogadores;
    private static Jogo[] jogos;
    private static Ranque[] ranqeados;
    private static BancoDeDados bancoDeDados;

    private BancoDeDados() {
        jogadores = new Jogador[tamJogadores];
        jogos = new Jogo[tamJogos];
        ranqeados = new Ranque[tamRanque];
    }

    public static BancoDeDados getInstance() {
        if (bancoDeDados == null) {
            bancoDeDados = new BancoDeDados();
        }
        
        return bancoDeDados;
    }

    public static Jogo[] getJogos() {
        return jogos;
    }

    public static Ranque[] getRanque() {
        return ranqeados;
    }

    public static Jogador[] getJogadores() {
        return jogadores;
    }

}
