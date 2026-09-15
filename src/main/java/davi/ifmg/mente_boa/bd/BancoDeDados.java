package davi.ifmg.mente_boa.bd;

import davi.ifmg.mente_boa.model.Jogador;
import davi.ifmg.mente_boa.model.Jogo;
import davi.ifmg.mente_boa.model.Ranque;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BancoDeDados {

    private final static int SIZE_JOGADORES = 40;
    private final static int SIZE_JOGOS = 200;
    private final static int SIZE_RANQUE = 5;
    private static Jogador[] jogadores;
    private static Jogo[] jogos;
    private static Ranque[] ranqeados;
    private static BancoDeDados bancoDeDados;

    private BancoDeDados() {
        inicializar();
        jogadores = new Jogador[SIZE_JOGADORES];
        jogos = new Jogo[SIZE_JOGOS];
        ranqeados = new Ranque[SIZE_RANQUE];
        carregarDados();
    }

    private static void inicializar() {
        String[] comandos = {
            """
            CREATE TABLE IF NOT EXISTS jogador (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                nome TEXT NOT NULL,
                cpf TEXT NOT NULL,
                apelido TEXT NOT NULL
            )
            """,
            """
            CREATE TABLE IF NOT EXISTS jogo (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                pontuacao INTEGER NOT NULL,
                codigo TEXT NOT NULL,
                horario TEXT NOT NULL,
                jogador_id INTEGER NOT NULL,

                CONSTRAINT fk_jogo_jogador
                    FOREIGN KEY (jogador_id)
                    REFERENCES jogador(id)
                    ON UPDATE NO ACTION
                    ON DELETE NO ACTION
            )
            """,
            """
            CREATE INDEX IF NOT EXISTS fk_jogo_jogador_idx
            ON jogo (jogador_id)
            """
        };

        try (Connection conexao = Conexao.conectar(); Statement stmt = conexao.createStatement()) {

            stmt.execute("PRAGMA foreign_keys = ON");

            for (String comando : comandos) {
                stmt.executeUpdate(comando);
            }

            System.out.println("Banco de dados inicializado.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void carregarDados() {
        carregarJogadores();
        carregarJogos();
    }

    private static void carregarJogadores() {
        String sql = "SELECT id, nome, cpf, apelido FROM jogador";

        try (
                Connection conexao = Conexao.conectar(); PreparedStatement stmt = conexao.prepareStatement(sql); ResultSet resultado = stmt.executeQuery()) {

            int indice = 0;
            while (resultado.next() && indice < jogadores.length) {

                int id = resultado.getInt("id");
                String nome = resultado.getString("nome");
                String cpf = resultado.getString("cpf");
                String apelido = resultado.getString("apelido");

                jogadores[indice]
                        = new Jogador(id, nome, cpf, apelido);

                indice++;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void carregarJogos() {
        String sql = "SELECT id, codigo, horario, pontuacao, jogador_id FROM jogo";

        try (
                Connection conexao = Conexao.conectar(); PreparedStatement stmt = conexao.prepareStatement(sql); ResultSet resultado = stmt.executeQuery()) {

            int indice = 0;
            while (resultado.next() && indice < jogos.length) {

                int id = resultado.getInt("id");
                String codigo = resultado.getString("codigo");
                String cpf = resultado.getString("horario");
                int pontos = (resultado.getInt("pontuacao"));
                int jogador_id = (resultado.getInt("jogador_id"));

                Jogador jogador = null;

                for (Jogador j : jogadores) {
                    if (j != null && j.getId() == jogador_id) {
                        jogador = j;
                        break;
                    }
                }
                
                jogos[indice]
                        = new Jogo(id, codigo, pontos, jogador, cpf);

                indice++;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
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

    public static void salvar_dados() {
        salvar_dados_jogadores();
        salvar_dados_jogos();
    }
    
    private static void salvar_dados_jogadores() {
        
    }
    
    private static void salvar_dados_jogos() {
        
    }    
}
