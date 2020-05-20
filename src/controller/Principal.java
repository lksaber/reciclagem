/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;


import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import static java.awt.image.BufferedImage.TYPE_INT_RGB;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author lucas
 */
/////////////////////////////////////////////////////////////
//CRIAÇÃO DA JANELA // CONTROLE DO LOOPS// CONTROLE TECLADO//
/////////////////////////////////////////////////////////////

public class Principal extends JPanel{
    private JFrame gameFrame;
    private GameController gameController;
    Rectangle rectCamera;
    
    /////////CRIA A JANELA PRINCIPAL OS LOOPS E A LIGAÇÃO DO TECLADO////////
    public Principal(){
        rectCamera = new Rectangle();
        rectCamera.height = 2048;
        rectCamera.width = 1600;

        //CRIANDO A JANELA EM JFRAME
        gameFrame = new JFrame();
        gameFrame.setTitle("Exploradores da Galaxia");
        gameFrame.add(this);
        gameFrame.setSize(800, 600);
        gameFrame.setLocationRelativeTo(null);
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameFrame.setVisible(true);
        

        //ATRIBUINDO A CLASSE DE CONTROLE DO TECLADO O AS AÇOES EXECUTADAS
        gameFrame.addKeyListener(new TecladoAdapter());
        
        //CRIANDO A GAME CONTROLLER
        gameController = new GameController();
        gameController.menuChamada(0);//dando a primeira chamada
        //ATRIBUINDO LOOP DE UPDATE DO GAME
        GameLoopUpdate gameLoopUpdate = new GameLoopUpdate();
        //ATRIBUINDO LOOP DE 
        GameLoopGraphics gameLoopGraphics = new GameLoopGraphics();
    }
    
     /////////PRINTA OS OBJETOS NA TELA////////////
    public void keyActions(KeyEvent keyTyped,KeyEvent keyPressed,KeyEvent keyReleased){
        gameController.keyActions(keyTyped,keyPressed,keyReleased);
    }
    
    public void update(){
        gameController.gameUpdate(rectCamera);
    }
    
    @Override
    public void paint(Graphics g){
        BufferedImage img = new BufferedImage(2048,1600,TYPE_INT_RGB);
        if(gameController != null){
            gameController.render((Graphics2D) img.createGraphics(),rectCamera);
        }

        g.drawImage(img, 0, 0,gameFrame.getWidth(),gameFrame.getHeight(), null);
    }
    
    
    
    /////////CONTROLADOR DO TECLADO////////////
    public class TecladoAdapter implements KeyListener{
        @Override
        public void keyTyped(KeyEvent ke) {
            keyActions(ke,null,null);
        }

        @Override
        public void keyPressed(KeyEvent ke) {
            keyActions(null,ke,null);
        }

        @Override
        public void keyReleased(KeyEvent ke) {
            keyActions(null,null,ke);
        }
    }
    
   
    //////////////////////////////////////////////////
    //////////////EVITE ALTERAR OS LOOPS//////////////
    //////////////////////////////////////////////////
    
    /////////GAME LOOPS UPDATE////////////
    public class GameLoopUpdate implements ActionListener{
        public GameLoopUpdate(){
            Timer gameTimer = new Timer(30,this);
            gameTimer.start();
        }
        @Override
        public void actionPerformed(ActionEvent ae) {
            //CONTROLA PRINCIPALMENTE O MODEL
            update();
        }
    }
    
    /////////GAME LOOPS GRAPHICS////////////
    public class GameLoopGraphics implements ActionListener {
        public GameLoopGraphics(){
            Timer gameTimer = new Timer(30,this);
            gameTimer.start();       
        }
        @Override
        public void actionPerformed(ActionEvent ae) {
            //CONTROLA PRINCIPALMENTE A VIEW
            gameFrame.repaint();
        }
    }
    
    /////////MAIN////////////
    public static void main(String[]args){
        Principal principal = new Principal();
    }
}
