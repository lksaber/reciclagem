/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.Classes;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import model.Classes.Armazenamento.ConjuntoImagenMe;
import model.Classes.Armazenamento.ConjuntoSonsMe;

/**
 *
 * @author lucas
 */
public abstract class MetodosImpostoObj {
    public abstract void gameUpdate(ConjuntoSonsMe sgBanco,Rectangle rectCamera);
    public abstract void render(Graphics2D grafic,ConjuntoImagenMe imgBanco,Rectangle rectCamera);
    /*
    //////////////////////////////////////////////////////////
    /////METODOS OBRIGATORIOS DA CLASSE MetodosImpostoObj/////
    //////////////////////////////////////////////////////////
    
    //ATUALIZAÇÃO DOS QUADROS E EXECUÇÃO DOS SONS
    @Override
    public void gameUpdate(ConjuntoSonsMe sgBanco){
    
    }
    
    //EXIBIÇÃO DAS IMAGENS
    @Override
    public void render(Graphics2D grafic,ConjuntoImagenMe imgBanco,Rectangle rectCamera){
    
    }
    */
}
