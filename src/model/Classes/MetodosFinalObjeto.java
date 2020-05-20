/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.Classes;

import controller.GameController;
import java.awt.event.KeyEvent;
import model.Classes.Armazenamento.ConjuntoImagenMe;
import model.Classes.Armazenamento.ConjuntoSonsMe;
import model.Classes.MetodosImpostoObj;

/**
 *
 * @author lucas
 */

/*
    serve para a criação final dos objetos do jogo classe que terão contato direto com
    o banco de imagens e sons afim de armazer imagens e sons deletalos e etc...
*/

public abstract class MetodosFinalObjeto extends MetodosImpostoObj{
    //loader: usar para armazer na memoria imagens e sons
    public abstract void loader(GameController gameCtr);
    
    //create: depois de dar o loader o criate cria os objetos internos da classe
    //objetos pré estabelecidos ou não com o na classe menu que ja tem arrays internos
    //de botoes imagens animaçoes texto
    public abstract void create(GameController gameCtr);
    
    //mensagens do teclado
    public abstract void keyActions(KeyEvent keyTyped,KeyEvent keyPressed,KeyEvent keyReleased);
    
    //destroy: retira da memoria as imagens e sons armazenadas deixando o local ao
    //qual estava alocado nulo assim podendo ser usado por outro elemento premitindo
    //um melhor uso do banco
    public abstract void destroy(GameController gameCtr);
}
