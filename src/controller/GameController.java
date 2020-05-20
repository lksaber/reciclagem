/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import model.Classes.Armazenamento.ConjuntoImagenMe;
import model.Classes.Armazenamento.ConjuntoSonsMe;
import model.Classes.BasicTela2D;
import view.EmGame;
import view.GameOver;
import view.MenuInicial;


/**
 *
 * @author lucas
 */


////////////////////////////////////////////////////////////////////////////////
//////////CONTROLA TODOS OS ACONTECIMENTOS ENTRE AS CLASSES DO GAME/////////////
////////////////////////////////////////////////////////////////////////////////
public final class GameController {
    private final ConjuntoImagenMe conjImag; //Armazena o banco de imagesns
    private final ConjuntoSonsMe conjSong; //Armazena o banco de sons
    
    private int menuAberto;//qual menu esta aberto no momento -1 para nenhum menu
    private BasicTela2D TelaAtual;
    private boolean inFase;
    private Integer score;
    //TelaAtual;
    
    
    public GameController(){
        ///CRIANDO ARMAZENAMENTO DE IMAGENS E SONS
        conjImag = new ConjuntoImagenMe();
        conjSong = new ConjuntoSonsMe();
        
        ///CRIANDO MENU INICIAL
        menuAberto = -1;
        score = 0;
    }

    public void menuChamada(int msgMenu){ 
    //faz a troca do menu atual por outro

        switch(msgMenu){
            case 0://menu inicial
                if(menuAberto != 0){
                    //TelaAtual.destroy(imgBanco, sgBanco);
                    TelaAtual = null;
                    TelaAtual = new MenuInicial(this);  
                }
                break;
            case 1://EmGame 
                if(menuAberto != 1){
                    //MenuAtual.destroy(imgBanco, sgBanco);
                    TelaAtual = null;
                    TelaAtual = new EmGame(this);  
                }
                break;
            case 2://GameOver 
                if(menuAberto != 2){
                    //MenuAtual.destroy(imgBanco, sgBanco);
                    TelaAtual = null;
                    TelaAtual = new GameOver(this);  
                }
                break;
        }
        
        menuAberto = msgMenu;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    
    public void keyActions(KeyEvent keyTyped,KeyEvent keyPressed,KeyEvent keyReleased){
        if(TelaAtual != null){
            TelaAtual.keyActions(keyTyped,keyPressed,keyReleased);
        }
    }
    
    public void gameUpdate( Rectangle rectCamera) {
        if(TelaAtual != null){
            TelaAtual.gameUpdate(conjSong, rectCamera);
        }
    }

    public void render(Graphics2D grafic, Rectangle rectCamera) {
        if(TelaAtual != null){
            TelaAtual.render(grafic, conjImag, rectCamera);
        }
    }
    public void destroy() {
        if(TelaAtual != null){
            TelaAtual.destroy(this);
        }
    }
    
    public ConjuntoImagenMe getimgBanco(){
        return conjImag;
    }
    public ConjuntoSonsMe getSongBanco(){
        return conjSong;
    }
}
