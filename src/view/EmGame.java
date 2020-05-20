/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.GameController;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import static java.awt.event.KeyEvent.VK_ENTER;
import static java.awt.event.KeyEvent.VK_K;
import static java.awt.event.KeyEvent.VK_LEFT;
import static java.awt.event.KeyEvent.VK_RIGHT;
import java.util.ArrayList;
import java.util.Random;
import model.Classes.Armazenamento.ConjuntoImagenMe;
import model.Classes.Armazenamento.ConjuntoSonsMe;
import model.Classes.BasicTela2D;
import model.Classes.MetodosImpostoObj;
import model.Classes.ObjetosAuxiliares.AnimaObj2D;
import model.GameClass.BackGround;

/**
 *
 * @author lucas
 */
public class EmGame extends BasicTela2D{
    private final int METAL = 0;
    private final int PAPEL = 1;
    private final int PLASTICO = 2;
    private final int VIDRO = 3;
    
    private int imageConjunto = 0;//usado para referencia no banco
    private int songConjunto = 0;//usado para referencia no banco
    private int imageConMetal = 0;
    private int imageConPapel = 0;
    private int imageConPlastico = 0;
    private int imageConVidro = 0;
    private GameController gameCtr;//objeto Gamecontroller para ter acesso a outras telas
    
    private int vidas;//quantidade de vida
    private int aniPosXinicial; //usado para definir aonde está a posição do primeiro elemento vida
    private AnimaObj2D vidaImg; //usado para armazenar info da vidaImg

    LixoElement lixo;
    private int caixaSel = 0;
    private int randonAuxi; //sempre que der cinco a velocidade dos objetos aumenta

   
    public EmGame(GameController gameCtr) {
        //recebe a game controler permitindo enviar mensagens para o objeto que a criou 
        if(gameCtr != null){
            this.gameCtr = gameCtr;
            this.loader(gameCtr);
            this.create(gameCtr);
            vidas = 5;
            gameCtr.setScore(0);
        }
        else{
            throw new IllegalArgumentException("Menu Inicial: Game Controle não pode ser vazio");
        }
    }

    
    //////////////////////CARREGANDO ARQUIVOS NOS BANCOS////////////////////////
    @Override
    public void loader(GameController gameCtr) {
        imageConjunto = gameCtr.getimgBanco().procurarConjunto("InicialBanners");
        //carregando elementos do jogo
        
        ////////////////////////////////METAL///////////////////////////////////
        if(gameCtr.getimgBanco().procurarConjunto("ConjuntoMetal") == -1){
            //CARREGA OS OBJETOS QUE COPONHEM O BACKGROUND//
            imageConMetal = gameCtr.getimgBanco().addNovoConjunto("ConjuntoMetal");
            for(int i = 0; i < 11;i++){//CARREGANDO METAIS
                gameCtr.getimgBanco().addImage(imageConMetal,
                    "/model/Arquivos/imagens/GameElement/metal_"+i+".png");
            }
        }else{
             imageConMetal = gameCtr.getimgBanco().procurarConjunto("ConjuntoMetal");
        }
        
        /////////////////////////////PAPEL//////////////////////////////////////
        if(gameCtr.getimgBanco().procurarConjunto("ConjuntoPapel") == -1){
            //CARREGA OS OBJETOS QUE COPONHEM O BACKGROUND//
            imageConPapel = gameCtr.getimgBanco().addNovoConjunto("ConjuntoPapel");
            for(int i = 0; i < 8;i++){//CARREGANDO PAPEL
                gameCtr.getimgBanco().addImage(imageConPapel,
                    "/model/Arquivos/imagens/GameElement/papel_"+i+".png");
            }
        }else{
             imageConPapel = gameCtr.getimgBanco().procurarConjunto("ConjuntoPapel");
        }
        
        //////////////////////////PLASTICO//////////////////////////////////////
        if(gameCtr.getimgBanco().procurarConjunto("ConjuntoPlastico") == -1){
            //CARREGA OS OBJETOS QUE COPONHEM O BACKGROUND//
            imageConPlastico = gameCtr.getimgBanco().addNovoConjunto("ConjuntoPlastico");
            for(int i = 0; i < 11;i++){//CARREGANDO PLASTICOS
                gameCtr.getimgBanco().addImage(imageConPlastico,
                    "/model/Arquivos/imagens/GameElement/plastico_"+i+".png");
            }
        }else{
             imageConPlastico = gameCtr.getimgBanco().procurarConjunto("ConjuntoPlastico");
        }
        
        /////////////////////////////VIDRO/////////////////////////////////////
        if(gameCtr.getimgBanco().procurarConjunto("ConjuntoVidro") == -1){
            //CARREGA OS OBJETOS QUE COPONHEM O BACKGROUND//
            imageConVidro = gameCtr.getimgBanco().addNovoConjunto("ConjuntoVidro");
            for(int i = 0; i < 4;i++){//CARREGANDO VIDRO
                gameCtr.getimgBanco().addImage(imageConVidro,
                    "/model/Arquivos/imagens/GameElement/vidro_"+i+".png");
            }
        }else{
             imageConVidro = gameCtr.getimgBanco().procurarConjunto("ConjuntoVidro");
        }
        
        
        songConjunto = gameCtr.getSongBanco().procurarConjunto("InicialSongbu");
    }
    
    //////////////////////CARREGANDO ARQUIVOS NOS BANCOS////////////////////////
    @Override
    public void create(GameController gameCtr) {
        BackGround background = new BackGround(gameCtr);
        addBackGround(background);
        aniPosXinicial = 88;
        vidaImg = new AnimaObj2D(0,20,imageConjunto,3,false);//VIDAS
        randomLixo();
    }

    @Override
    public void keyActions(KeyEvent keyTyped, KeyEvent keyPressed, KeyEvent keyReleased) {
        if(keyPressed != null){
            switch(keyPressed.getKeyCode()){
                case VK_RIGHT:
                    if(caixaSel < 3){
                        caixaSel++;
                    }
                    break;
                case VK_LEFT:
                    if(caixaSel > 0){
                        caixaSel--;
                    }
                    break;
                case VK_ENTER:
                    int score = gameCtr.getScore();
                    gameCtr.setScore(score + 10);
                    System.out.println("Score: "+ score);
                    gameCtr.menuChamada(2);
                    break;
            }
            if(keyPressed.getKeyCode() == VK_ENTER){
                gameCtr.menuChamada(2);
            }
        }
    }
    ////////////////////////////////////////////////////////////////////////////
    /////////////////ESPAÇO PARA CODIGOS EXTRAS/////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////
    private void randomLixo(){
        int veloAtual = 10;
        if(lixo != null){
            veloAtual = lixo.getVelocidade();
            if(randonAuxi >= 5){
                veloAtual += 5;
                randonAuxi = 0;
            }
            lixo.destroy();
        }
        lixo = null;
        
       //////CRIANDO UM ELEMENTO RANDONICO///////
        Random random = new Random();
        int primeroRamdom = random.nextInt(4);
        int segundoRandom = 0;
        int imagemRefer = 0;
        boolean notGo = false;
        switch(primeroRamdom){
            case METAL : 
                segundoRandom = random.nextInt(11); 
                imagemRefer = imageConMetal;
                break;
            case PAPEL :  
                segundoRandom = random.nextInt(8); 
                imagemRefer = imageConPapel;
                break;
            case PLASTICO :  
                segundoRandom = random.nextInt(11); 
                imagemRefer = imageConPlastico;
                break;
            case VIDRO :  
                segundoRandom = random.nextInt(4); 
                imagemRefer = imageConVidro;
                break;
            default:
                notGo = true;  
        }
  
        caixaSel = random.nextInt(4);
                
        if(notGo != true){
            lixo = new LixoElement(primeroRamdom,veloAtual,new AnimaObj2D(0,-500,imagemRefer,segundoRandom,false),gameCtr.getimgBanco());
            randonAuxi++;
        }else{
            randomLixo();
        }
    }
    
    
    ////////////////////////////////////////////////////////////////////////////
    @Override
    public void logica(ConjuntoSonsMe sgBanco, Rectangle rectCamera) {
        if(vidas == 0){
            gameCtr.menuChamada(2);
        }
        if(lixo != null){
            lixo.gameUpdate(sgBanco, rectCamera);
            
            if(lixo.getTamanhoy() + lixo.anima.Tranform().getPosy() > 1056){
                if(caixaSel != lixo.tipo){
                    gameCtr.getSongBanco().startSound(songConjunto, 1);
                    vidas--;
                }else{
                    int score = gameCtr.getScore();
                    gameCtr.setScore(score + 10);
                    gameCtr.getSongBanco().startSound(songConjunto, 2);
                }
                randomLixo();
            }
        }
    }

    @Override
    public void grafic(Graphics2D grafic, ConjuntoImagenMe imgBanco, Rectangle rectCamera) {
        if(vidaImg!=null){
            for(int i = 0; i < vidas; i++){
                vidaImg.Tranform().setPosx(aniPosXinicial + (150*i));
                vidaImg.render(grafic, imgBanco, rectCamera);
            }
        }
        if(lixo != null){
            int line = 0;
            switch(caixaSel){
                case METAL : line = 331; break;
                case PAPEL : line = 793; break;
                case PLASTICO : line = 1254; break;
                case VIDRO : line = 1716; break;
            }
            lixo.CenterLineX(line, gameCtr.getimgBanco());
            lixo.render(grafic, imgBanco, rectCamera);
        }
    }

    
    @Override
    public void destroy(GameController gameCtr) {
        vidaImg.destroy(); //usado para armazenar info da vidaImg
        vidaImg = null;
        lixo.destroy();
        lixo = null;
    }
    
    ////////////////////////////////////////////////////////////////////////////
    ////////////////////ESPAÇO PARA CLASSES INTERNAS////////////////////////////
    ////////////////////////////////////////////////////////////////////////////
   
    
    
    private class LixoElement extends MetodosImpostoObj{
        private int tipo;//usar o enum
        private int velocidade;//velocidade que o objeto percorre o eixo y
        private AnimaObj2D anima; //imagem do elemento
        private int tamanhox;
        private int tamanhoy;

        public LixoElement(int tipo,int velocidade,AnimaObj2D anima,ConjuntoImagenMe imgBanco){
            if(anima != null){
                if(tipo < 4){
                    this.tipo = tipo;
                    this.velocidade = velocidade;
                    this.anima = anima;
                    tamanhox = this.anima.getTamPrimeiraImagen(imgBanco).x;
                    tamanhoy = this.anima.getTamPrimeiraImagen(imgBanco).y;
                }else{
                    throw new IllegalArgumentException("EmGame: LixoElement: tipo inexistente");        
                } 
            }else{
                throw new NullPointerException("EmGame: LixoElement: anima não pode ser null");
            }
        }

        public int getTamanhox() {
            return tamanhox;
        }

        public int getTamanhoy() {
            return tamanhoy;
        }

        public int getVelocidade() {
            return velocidade;
        }

        public void CenterLineX(int linha,ConjuntoImagenMe imgBanco){
            if(anima != null){
                Point tam = anima.getTamPrimeiraImagen(imgBanco);
                if(tam.x > 0){
                    anima.Tranform().setPosx(linha - (tam.x/2));
                }
            }
        }

        @Override
        public void gameUpdate(ConjuntoSonsMe sgBanco, Rectangle rectCamera) {
            if(anima != null){
                anima.Tranform().setPosy(anima.Tranform().getPosy() + velocidade);
                anima.gameUpdate(sgBanco, rectCamera);
            }
        }

        @Override
        public void render(Graphics2D grafic, ConjuntoImagenMe imgBanco, Rectangle rectCamera) {
            if(anima != null){
                anima.render(grafic, imgBanco, rectCamera);
            }
        }
        
        public void destroy(){
            anima.destroy();
            anima = null;
        }
    }
    ////////////////////////////////////////////////////////////////////////////
}
