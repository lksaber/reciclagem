/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.Classes;

import controller.GameController;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;
import model.Classes.Armazenamento.ConjuntoImagenMe;
import model.Classes.Armazenamento.ConjuntoSonsMe;
import model.Classes.ObjetosAuxiliares.AnimaObj2D;
import model.Classes.ObjetosAuxiliares.BackGroundObject;
import model.Classes.ObjetosAuxiliares.LoaderReturn;

/**
 *
 * @author lucas
 */
/*
    auxilia na criação de planos de fundo para a fase
*/

public abstract class BasicFundoObj2D extends MetodosImpostoObj{
    private  ArrayList<BackGroundObject> backgroObj = null;
    
    //adiciona um novo elemento ao final do array
    public void addNewBackGround(AnimaObj2D animaObj){
            addNewBackGround(animaObj,0,0,false,false,0,0);
    }

    
    public void addNewBackGround(AnimaObj2D animaObj, int speedX, int speedY,
        boolean subjectCamera,boolean repetir,int sangria){
            addNewBackGround(animaObj,speedX,speedY,subjectCamera,repetir,sangria,0);
    }

    
    //adiciona um novo elemento na posição escolhida pelo progamador
    public void addNewBackGround(AnimaObj2D animaObj, int speedX, int speedY,
        boolean subjectCamera,boolean repetir,int sangria,int index){
            if(animaObj != null){
                if(backgroObj == null){
                    backgroObj = new ArrayList();
                }
                if(index >= 0 && index < backgroObj.size()){
                    backgroObj.add(index,new BackGroundObject(animaObj,speedX,speedY,subjectCamera,repetir,sangria));
                }else{
                    backgroObj.add(new BackGroundObject(animaObj,speedX,speedY,subjectCamera,repetir,sangria));
                }
            }else{
                throw new IllegalArgumentException("BGaddNewBackGround: não pode receber animação nula");
            }
    }
    
    public void trocarArrayPos(int indexAtual, int indexNovo){
        if(backgroObj != null){
            if(indexAtual < backgroObj.size() && indexNovo < backgroObj.size()){
                BackGroundObject backg = backgroObj.get(indexAtual);
                backgroObj.remove(indexAtual);
                backgroObj.set(indexNovo, backg);
            }else{
                throw new IllegalArgumentException("BGtrocarArrayPos: index maior que o array");
            }
        }else{
            throw new IllegalArgumentException("BGtrocarArrayPos: array nula");   
        }
    }
    
    public void removeNewBackGround(int index){
            if(backgroObj != null){
                if(index < backgroObj.size()-1){
                    backgroObj.remove(index);
                }else{
                    throw new IllegalArgumentException("BGremoveNewBackGround: não pode receber index maior que o array");
                }
            }else{
                throw new IllegalArgumentException("BGremoveNewBackGround: array nulo");
            }
    }
  
    @Override
    public void gameUpdate(ConjuntoSonsMe sgBanco, Rectangle rectCamera) {
        //executa a logica
        if(backgroObj != null){
            for(int i = 0; i < backgroObj.size(); i++){
                 backgroObj.get(i).gameUpdate(sgBanco, rectCamera);//executa as imagens da animação
            }
        }
    }

    @Override
    public void render(Graphics2D grafic, ConjuntoImagenMe imgBanco, Rectangle rectCamera) {
        //executa as imagens da animação
        if(backgroObj != null){
            for(int i = backgroObj.size()-1; i >= 0; i--){
                backgroObj.get(i).render(grafic, imgBanco,rectCamera);//executa as imagens da animação
            }
        }
    }
    
    public abstract void loader(GameController gameCtr);
    public abstract void create();
    public abstract void destroy();

}
