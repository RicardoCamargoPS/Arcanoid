package com.ricardo.dto;

import java.util.List;

import com.ricardo.entidades.Bloco;
import com.ricardo.fases.Gerador_fase;
import com.ricardo.main.Game;
import com.ricardo.ui.UIScore;
import com.ricardo.ui.UIVida;

public class RestartGame {

    private int vida;
    private int pontos;
    private List <Bloco> blocos;

    public RestartGame(){
        
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

    public void getVal(){

        pontos = UIScore.getScore();
        vida = UIVida.getVida();

        for( Bloco b: Gerador_fase.blocos){
            blocos.add(b);
        }

    }

}
