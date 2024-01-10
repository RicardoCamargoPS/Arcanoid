package com.ricardo.dao;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.List;

import com.ricardo.entidades.Bloco;
import com.ricardo.entidades.Bola;
import com.ricardo.entidades.Player;
import com.ricardo.main.Game;
import com.ricardo.ui.UIScore;
import com.ricardo.ui.UIVida;

public class GameDao implements Serializable {

    private UIVida vidas;
    private UIScore score;
    private int pontos;
    private int vida;
    private List <Bloco> blocos;
    private Player player;
    private Bola bola;

    public void restartGame(){
        
        Game.player.setPx(61);
        Game.player.setPy(280);

        Game.bola.setPx(93);
        Game.bola.setPy(270);

        Game.PlayerScore.resetScore();
        Game.PlayerScore.increaseScore(pontos);

        Game.PlayerVida.setVidas(vida);
        Game.fase.limpaFase();
        Game.fase.setFase(blocos);

    }   

    public void getVal(Bola bola, Player player, List <Bloco> blocos, UIVida vidas, UIScore score){

        this.player = player;
        this.bola = bola;

        score = score;
        vidas = vidas;

        for( Bloco b: blocos){
            this.blocos.add(b);
        }

        StringBuilder dados = new StringBuilder();

        dados.append(player).append("\n");
        dados.append(bola).append("\n");
        for( Bloco b: blocos){
            dados.append(b).append("\n");
        }     

    }

    // Método para salvar o jogo
    private static void saveGame(String playerName, int playerScore, String filePath) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(filePath))) {
            GameDao gameData = new GameDao();
            outputStream.writeObject(gameData);
            System.out.println("Jogo salvo com sucesso.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Método para carregar o jogo
    private static GameDao loadGame(String filePath) {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(filePath))) {
            GameDao loadedData = (GameDao) inputStream.readObject();
            System.out.println("Jogo carregado com sucesso.");
            return loadedData;
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Erro ao carregar o jogo: " + e.getMessage());
            return null;
        }
    }
}


