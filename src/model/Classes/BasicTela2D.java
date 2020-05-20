/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.Classes;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;
import model.Classes.Armazenamento.ConjuntoImagenMe;
import model.Classes.Armazenamento.ConjuntoSonsMe;
/**
Auxilia na criação do menu
 */
public abstract class BasicTela2D extends MetodosFinalObjeto{
    private BasicFundoObj2D background;
    private ArrayList<MetodosImpostoObj> gameObj;
    private ArrayList<String> strObj;
    
    public BasicTela2D() {
        this.background = null;
        gameObj = null;
        strObj = null;
    }
    ///BACKGROUND
    public void addBackGround(BasicFundoObj2D background){
        if(background != null){
            this.background = background;
        }
        else{
            throw new IllegalArgumentException("BasicMenuElement2D: addBackGround(não pode ser null)");
        }
    }

    public void removeBackGround(){
        background = null;
    }
    
    public void addGameObj(MetodosImpostoObj gameObj,String nome){
        if(this.gameObj == null){
           this.gameObj = new ArrayList(); 
           this.strObj = new ArrayList(); 
        }
        if(gameObj != null){
            this.gameObj.add(gameObj);
            this.strObj.add(nome);
        }
    }
    
    public MetodosImpostoObj getGameObj(int index){
        if(this.gameObj != null){
            if(index < this.gameObj.size()){
                this.gameObj.get(index);
            }
        }
        return null;
    }
    
    public MetodosImpostoObj getGameObj(String nome){
        if(this.gameObj != null && this.strObj != null){
            for(int i = 0; i < this.strObj.size(); i++){
                if(strObj.get(i).equals(nome) == true){
                    return gameObj.get(i);
                }
            }
        }
        return null;
    }
    
    public void removeGameObj(int index){
        if(this.gameObj != null){
            if(index < this.gameObj.size()){
                this.gameObj.remove(index);
            }
        }
    }
    
    
    //ATUALIZAÇÃO DOS QUADROS E EXECUÇÃO DOS SONS
    @Override
    public void gameUpdate(ConjuntoSonsMe sgBanco, Rectangle rectCamera) {
        if(background != null){
            background.gameUpdate(sgBanco, rectCamera);
        }
        if(gameObj != null){
            for(int i = 0; i < this.gameObj.size(); i++){
                gameObj.get(i).gameUpdate(sgBanco, rectCamera);
            }
        }
        this.logica(sgBanco,rectCamera);
    }
    
    //EXIBIÇÃO DAS IMAGENS
    @Override
    public void render(Graphics2D grafic,ConjuntoImagenMe imgBanco,Rectangle rectCamera){
        if(background != null){//IMPRESSÃO DO BACKGROUND
            background.render(grafic, imgBanco, rectCamera);
        }
        if(gameObj != null){
            for(int i = 0; i < this.gameObj.size(); i++){
                gameObj.get(i).render(grafic, imgBanco, rectCamera);
            }
        }
        this.grafic(grafic,imgBanco,rectCamera);
    } 
    
    ////////////////////////METODOS ABSTRATOS///////////////////////////////////
    public abstract void logica(ConjuntoSonsMe sgBanco,Rectangle rectCamera);//metodo que permite a estruturação da logica
    //esse metodo possui todas as funcionalidades do menu e é chamado pelo 
    //gameupdate impedindo o acesso direto pelo usuario
    public abstract void grafic(Graphics2D grafic,ConjuntoImagenMe imgBanco,Rectangle rectCamera);//é puxado pelo render para que o usuario não tenha
    //acesso direto ao render
}
