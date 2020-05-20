/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.GameController;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import static java.awt.event.KeyEvent.VK_ENTER;
import model.Classes.Armazenamento.ConjuntoImagenMe;
import model.Classes.Armazenamento.ConjuntoSonsMe;
import model.Classes.BasicTela2D;
import model.Classes.ObjetosAuxiliares.AnimaObj2D;
import model.Classes.ObjetosAuxiliares.TextObj;
import model.GameClass.BackGround;

/**
 *
 * @author lucas
 */
public class GameOver  extends BasicTela2D{
    private int imageConjunto = 0;
    private int songConjunto = 0;
    private GameController gameCtr;
            
    public GameOver(GameController gameCtr){
        //recebe a game controler permitindo enviar mensagens para o objeto que a criou 
        if(gameCtr != null){
            this.loader(gameCtr);
            this.create(gameCtr);
            this.gameCtr = gameCtr;
        }
        else{
            throw new IllegalArgumentException("Menu Inicial: Game Controle não pode ser vazio");
        }
    }
    
    //////////////////////CARREGANDO ARQUIVOS NOS BANCOS////////////////////////
    @Override
    public void loader(GameController gameCtr) {
        imageConjunto = gameCtr.getimgBanco().procurarConjunto("InicialBanners");
        songConjunto = gameCtr.getSongBanco().procurarConjunto("InicialSongbu");
    }
    
    //////////////////////CARREGANDO ARQUIVOS NOS BANCOS////////////////////////
    @Override
    public void create(GameController gameCtr) {
        Color corPreta = new Color(0,0,0); // cor pra o texto
        Rectangle rect = new Rectangle(0,500,2048,200);
        Font font = new Font("Agency FB", Font.BOLD, 20); 
        
        BackGround background = new BackGround(gameCtr);
        addBackGround(background);
        addGameObj(new AnimaObj2D(285,150,imageConjunto,2,false),"GameOver");
        addGameObj(new AnimaObj2D(622,1330,imageConjunto,1,false),"Button");
        addGameObj(new TextObj(rect,"PONTUAÇÃO: "+ gameCtr.getScore(),font,80,corPreta),"TextPont");
    }

    @Override
    public void keyActions(KeyEvent keyTyped, KeyEvent keyPressed, KeyEvent keyReleased) {
        if(keyPressed != null){
            if(keyPressed.getKeyCode() == VK_ENTER){
                System.out.println("score: " + gameCtr.getScore());
                gameCtr.getSongBanco().startSound(songConjunto, 0);
                gameCtr.menuChamada(1);
            }
        }
    }


    @Override
    public void logica(ConjuntoSonsMe sgBanco, Rectangle rectCamera) {
        
    }

    @Override
    public void grafic(Graphics2D grafic, ConjuntoImagenMe imgBanco, Rectangle rectCamera) {

    }

    
    @Override
    public void destroy(GameController gameCtr) {
        
    }
}
