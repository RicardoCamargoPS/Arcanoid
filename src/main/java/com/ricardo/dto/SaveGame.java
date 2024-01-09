package com.ricardo.dto;

import java.util.List;

import com.ricardo.entidades.Bloco;
import com.ricardo.fases.Gerador_fase;
import com.ricardo.main.Game;
import com.ricardo.ui.UIScore;
import com.ricardo.ui.UIVida;
public class SaveGame {


    private int vida;
    private int pontos;
    private int playerPX, playerPY;
    private int bolaPX, bolaPY;
    private List <Bloco> blocos;

    public void getVal(){

        pontos = UIScore.getScore();
        vida = UIVida.getVida();
        playerPX = Game.player.getPx();
        playerPY = Game.player.getPy();
        bolaPX = Game.bola.getPx();
        bolaPY = Game.bola.getPy();

        for( Bloco b: Gerador_fase.blocos){
            blocos.add(b);
        }

    }
}
