/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.GameController;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import static java.awt.event.KeyEvent.VK_ENTER;
import model.Classes.Armazenamento.ConjuntoImagenMe;
import model.Classes.Armazenamento.ConjuntoSonsMe;
import model.Classes.BasicTela2D;
import model.Classes.ObjetosAuxiliares.AnimaObj2D;
import model.GameClass.BackGround;

/**
 *
 * @author lucas
 */
public final class MenuInicial extends BasicTela2D{
    private int imageConjunto = 0;
    private int songConjunto = 0;
    private GameController gameCtr;
            
    public MenuInicial(GameController gameCtr){
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
        if(gameCtr.getimgBanco().procurarConjunto("InicialBanners") == -1){
            //CARREGA OS OBJETOS QUE COPONHEM O BACKGROUND//
            imageConjunto = gameCtr.getimgBanco().addNovoConjunto("InicialBanners");
            gameCtr.getimgBanco().addImage(imageConjunto,"/model/Arquivos/imagens/Titulo.png");
            gameCtr.getimgBanco().addImage(imageConjunto,"/model/Arquivos/imagens/Button_01.png");
            gameCtr.getimgBanco().addImage(imageConjunto,"/model/Arquivos/imagens/GameOver.png");
            gameCtr.getimgBanco().addImage(imageConjunto,"/model/Arquivos/imagens/Vidas.png");
        }else{
             imageConjunto = gameCtr.getimgBanco().procurarConjunto("InicialBanners");
        }
        if(gameCtr.getSongBanco().procurarConjunto("InicialSongbu") == -1){
            //CARREGA OS OBJETOS QUE COPONHEM O BACKGROUND//
            songConjunto = gameCtr.getSongBanco().addNovoConjunto("InicialSongbu");
            gameCtr.getSongBanco().addSong(songConjunto,"/model/Arquivos/sons/Button01.wav");
            gameCtr.getSongBanco().addSong(songConjunto,"/model/Arquivos/sons/error.wav");
            gameCtr.getSongBanco().addSong(songConjunto,"/model/Arquivos/sons/Correct.wav");
            gameCtr.getSongBanco().addSong(songConjunto,"/model/Arquivos/sons/Music.wav");
        }else{
             songConjunto = gameCtr.getSongBanco().procurarConjunto("InicialSongbu");
        }
    }
    
    //////////////////////CARREGANDO ARQUIVOS NOS BANCOS////////////////////////
    @Override
    public void create(GameController gameCtr) {
        BackGround background = new BackGround(gameCtr);
        addBackGround(background);
        addGameObj(new AnimaObj2D(233,250,imageConjunto,0,false),"Titulo");
        addGameObj(new AnimaObj2D(622,1330,imageConjunto,1,false),"Button");
        gameCtr.getSongBanco().startSoundLoop(songConjunto, 3);
    }

    @Override
    public void keyActions(KeyEvent keyTyped, KeyEvent keyPressed, KeyEvent keyReleased) {
        if(keyPressed != null){
            if(keyPressed.getKeyCode() == VK_ENTER){
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
