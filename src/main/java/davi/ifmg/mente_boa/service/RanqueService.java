
package davi.ifmg.mente_boa.service;

import davi.ifmg.mente_boa.dao.JogadorDAO;
import davi.ifmg.mente_boa.dao.JogoDAO;
import davi.ifmg.mente_boa.dao.RanqueDAO;
import davi.ifmg.mente_boa.model.Jogador;
import davi.ifmg.mente_boa.model.Jogo;
import davi.ifmg.mente_boa.model.Ranque;
import davi.ifmg.mente_boa.utill.Util;
import java.util.ArrayList;
import java.util.List;

public class RanqueService {
    private JogoDAO jogoDao;
    private JogadorDAO jogadorDao;
    private RanqueDAO ranqueDao;

    public RanqueService() {
        ranqueDao = new RanqueDAO();
        jogadorDao = new JogadorDAO();
        jogoDao = new JogoDAO();
    }
    
    public String calcularRanque(){
        Jogador jogadores[] = jogadorDao.getAll();
        
        //Salva em uma list, pois essa não precisa setar um tamanho.
        List <Jogador> jogadorAUX = new ArrayList<>();
        
        //Coloca os jogadores que não estão nulos
        for (int i = 0; i < jogadores.length; i++) {
             if (jogadores[i] != null)
                 jogadorAUX.add(jogadores[i]);
        }
        
        Jogo jogos[] = jogoDao.getAll();
        
        List <Jogo> jogosAUX = new ArrayList<>();

        for (int i = 0; i < jogos.length; i++) {
             if (jogos[i] != null && jogos[i].getCodigo() != null)
                 jogosAUX.add(jogos[i]);
        }
                        
        List <Ranque> ranqueAUX = new ArrayList<>();
                
        //Cada vez que o valor de 'a' muda está em outro jogador do sistema
        for (int a = 0; a < jogadorAUX.size(); a++){
            //Zera os dados que devem ser colados no vetor auxiliar de 'ranque';
            int quantJogos = 0;
            int totalDePontosDeJogad = 0;
            float media = 0;
            
            for (int i = 0; i < jogosAUX.size(); i++){
                //identifica quais são os jogos do jogador, procurando pelo CPF do jogador no vetor de jogos.
                if (jogadorAUX.get(a).getCPF().equals(jogosAUX.get(i).getJogador().getCPF())) {
                    //Faça o calculo da média de cada jogador.
                    quantJogos++;
                    
                    totalDePontosDeJogad += jogosAUX.get(i).getPontos();                   
                }
            }

            //Calcula a média
            if (quantJogos == 0) {
                media = 0;
            } else {
                media = (float) totalDePontosDeJogad / quantJogos;
            }

            //Adiciona os dados do ranque a uma variável do tipo ranque
            Ranque rank = new Ranque();
            rank.setMedia(media);

            //Adiciona o jogador correspondente a média;
            rank.setJogador(jogadorAUX.get(a));
            
            //Adiciona a quantidade de jogod, e os ponotos do jogador
            rank.setPontos(totalDePontosDeJogad);
            rank.setQuantidade(quantJogos);
            
            //Por fim adiciona o ranque com os dados no ranque Auxiliar (Solução para um problema com as propriedades do Array List)
            ranqueAUX.add(rank);
        } 
            
        Ranque ranqueAux2[] = new Ranque[ranqueAUX.size()];
        
        //Converte o list para um vetor comum, para facilitar a organização;
        for (int i = 0; i < ranqueAux2.length; i++)
            ranqueAux2[i] = ranqueAUX.get(i);
        
        //Chama o método que organizara as médias em ordem decrescente, em um vetor auxiliar;
        Util.mangeSortDivide(ranqueAux2);
        
        //Limpa os dados, para poder inserir novos valores;
        ranqueDao.limparDados();
        
        //Manda os 5 maiores no ranque para o dao;
        for (int i = 0, cout = 0; i < ranqueAux2.length && cout < 5; i++){
           if (ranqueAux2[i] != null){
               ranqueDao.inserir(ranqueAux2[i]); //Erro aqui !!
               cout++;
           }
        }
            
        return imprimirRanque(ranqueDao.getAll());
    }
 
    private static String imprimirRanque(Ranque[] ranque) {
        StringBuffer ranqueTexto = new StringBuffer();

        for (int i = 0; i < ranque.length; i++) {
            if (ranque[i] != null) {
                switch (i) {
                    case 0:
                        ranqueTexto.append("🥇 ");
                        break;
                    case 1:
                        ranqueTexto.append("🥈 ");
                        break;
                    case 2:
                        ranqueTexto.append("🥉 ");
                        break;
                    case 3:
                        ranqueTexto.append("🏅 ");
                        break;
                    case 4:
                        ranqueTexto.append("🎖 ");
                        break;
                }

                ranqueTexto.append(
                        (i + 1) + "°) " + ranque[i].getJogador().getCPF() + " \t Media: " + ranque[i].getMedia()
                        + " Pts\t\t Numero de jogos: " + ranque[i].getQuantidade() + ".\n");
            }
            //A formatação deve ser assim: x°) Jogador: cpf \t Media: mesiax Pts \t Quantidade Jogos: quantJogos;  
        }
        
        return ranqueTexto.toString();
    }
    
    public void limparDados(){
        ranqueDao.limparDados();
    }
    
}
