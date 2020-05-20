/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.Classes.ObjetosAuxiliares;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;
import model.Classes.Armazenamento.ConjuntoImagenMe;
import model.Classes.Armazenamento.ConjuntoSonsMe;
import model.Classes.Armazenamento.SongReference;
import model.Classes.BasicButtonObject;

/**
 *
 * @author lucas
 */
public class ButtonAnimaObj extends BasicButtonObject{
    private int posx;
    private int posy;
    private int zoom;
    private int distorcX;
    private int distorcY;
    private ArrayList<AnimaObj2D> anima; //array list que armazena a animação

    private SongReference songSelect; //armazena a referencia do som no banco
    private SongReference songPress; //armazena a referencia do som no banco
    private SongReference songPressInative; //armazena a referencia do som no banco
    
    private boolean songAtive;
    
    public ButtonAnimaObj(int posx,int posy,int zoom,ButtonAnimaDesing buttonDesing){
        this(posx,posy,zoom,buttonDesing.getAniDeselect(),buttonDesing.getAniSelect(),
                buttonDesing.getAniPress(),buttonDesing.getAniInative(),
                buttonDesing.getAniInativeSel(),buttonDesing.getAniInativePress(),
                buttonDesing.getSongSelect(),buttonDesing.getSongPress(),
                buttonDesing.getSongPressInative()); 
    }
    
    public ButtonAnimaObj(int posx,int posy,int zoom,
            AnimaObj2D aniDeselect,AnimaObj2D aniSelect,AnimaObj2D aniPress,AnimaObj2D aniInative
    ){
        this(posx,posy,zoom,aniDeselect,aniSelect,aniPress,aniInative,null,null,null,null,null);
    }
    
    public ButtonAnimaObj(int posx,int posy,int zoom,
            AnimaObj2D aniDeselect,AnimaObj2D aniSelect,AnimaObj2D aniPress,AnimaObj2D aniInative,
            SongReference songSelect,SongReference songPress,SongReference songPressInative
    ){
        this(posx,posy,zoom,aniDeselect,aniSelect,aniPress,aniInative,null,null,songSelect,songPress,songPressInative);
    }
    
    public ButtonAnimaObj(
            int posx,int posy,int zoom,
            AnimaObj2D aniDeselect,AnimaObj2D aniSelect,AnimaObj2D aniPress,AnimaObj2D aniInative,
            AnimaObj2D aniInativeSel,AnimaObj2D aniInativePress,
            SongReference songSelect,SongReference songPress,SongReference songPressInative
    ){
    
        this.posx = posx;
        this.posy = posy;
        this.zoom = zoom;
        
        anima = new ArrayList(6);
        anima.add(aniDeselect);
        anima.add(aniSelect);
        anima.add(aniPress);
        anima.add(aniInative);
        anima.add(aniInativeSel);
        anima.add(aniInativePress);
        
        this.songSelect = songSelect;
        this.songPress = songPress;
        this.songPressInative = songPressInative; 
    }
    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////GET E SETER POSIÇÃO E DISTORÇÃO/////////////////////
    ////////////////////////////////////////////////////////////////////////////
    
    public int getPosx() {
        return posx;
    }
    public void setPosx(int posx) {
        this.posx = posx;
    }

    public int getPosy() {
        return posy;
    }
    public void setPosy(int posy) {
        this.posy = posy;
    }

    public int getDistorcX() {
        return distorcX;
    }
    public void setDistorcX(int distorcX) {
        this.distorcX = distorcX;
    }
    
    public int getDistorcY() {
        return distorcY;
    }
    public void setDistorcY(int distorcY) {
        this.distorcY = distorcY;
    }

    public int getZoom() {
        return zoom;
    }

    public void setZoom(int zoom) {
        this.zoom = zoom;
    } 
    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////GET E SETER ANIMAÇAO////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////
    public AnimaObj2D getAniDeselect() {
        return anima.get(0);
    }
    public void setAniDeselect(AnimaObj2D aniDeselect){
        this.anima.set(0,aniDeselect);
    }
    
    public AnimaObj2D getAniSelect() {
        return anima.get(1);
    }  
    public void setAniSelect(AnimaObj2D aniSelect){
        this.anima.set(1,aniSelect);
    }
    
    public AnimaObj2D getAniPress() {
        return anima.get(2);
    }
    public void setAniPress(AnimaObj2D aniPress){
        this.anima.set(2,aniPress);
    }
    
    public AnimaObj2D getAniInative() {
        return anima.get(3);
    }
    public void setAniInative(AnimaObj2D aniInative){
        this.anima.set(3,aniInative);
    }
    
    public AnimaObj2D getAniInativeSel() {
        return anima.get(4);
    }
    public void setAniInativeSel(AnimaObj2D aniInativeSel){
        this.anima.set(4,aniInativeSel);
    }
    
    public AnimaObj2D getAniInativePress() {
        return anima.get(5);
    }
    public void setAniInativePress(AnimaObj2D aniInativePress){
        this.anima.set(5,aniInativePress);
    }
    
    
    @Override
    public void mudanca() {//indica que ouve uma mudança de estado
        songAtive = true;
    }

    @Override
    public void gameUpdate(ConjuntoSonsMe sgBanco, Rectangle rectCamera) {
        if(songAtive == true){
            if(getEstado() == 1 && songSelect != null){//selecionado
                sgBanco.startSound(songSelect.getIndexConjunto(), songSelect.getIndexElemento());
            }
            if(getEstado() == 2){
                if(isAtivado() == true && songPress != null){
                    sgBanco.startSound(songPress.getIndexConjunto(), songPress.getIndexElemento());
                }else if(isAtivado() == false && songPressInative != null){
                    sgBanco.startSound(songPressInative.getIndexConjunto(), songPressInative.getIndexElemento());
                }
            }
            songAtive = false;
        }
    }

    
    private void renderAnima(AnimaObj2D animas,Graphics2D grafic, ConjuntoImagenMe imgBanco, Rectangle rectCamera){
        //anima e retorna a imagem para a posição original
        int posxAnt = animas.Tranform().getPosx();
        int posyAnt = animas.Tranform().getPosy();
        int distorXAnt = animas.Tranform().getDistorceX();
        int distorYAnt = animas.Tranform().getDistorceY();
        int zoomAnt = animas.Tranform().getZoom();
        animas.Tranform().setPosx(posx);
        animas.Tranform().setPosy(posy);
        animas.Tranform().setDistorceX(distorcX);
        animas.Tranform().setDistorceY(distorcY);
        animas.Tranform().setZoom(zoom);
        animas.render(grafic, imgBanco, rectCamera);//caso o botão esteja ativo imprime o estado
        animas.Tranform().setPosx(posxAnt);
        animas.Tranform().setPosy(posyAnt);
        animas.Tranform().setDistorceX(distorXAnt);
        animas.Tranform().setDistorceY(distorYAnt);
        animas.Tranform().setZoom(zoomAnt);
    }
    
    @Override
    public void render(Graphics2D grafic, ConjuntoImagenMe imgBanco, Rectangle rectCamera) {
        switch(getEstado()){
            case 0://deselect
                if(isAtivado() == true && anima.get(0) != null){
                    renderAnima(anima.get(0),grafic, imgBanco, rectCamera);
                }
                else if(isAtivado() == false && anima.get(3) != null){
                    renderAnima(anima.get(3),grafic, imgBanco, rectCamera);
                }
                break;
            case 1://Select
                if(isAtivado() == true && anima.get(1) != null){
                    renderAnima(anima.get(1),grafic, imgBanco, rectCamera);
                }
                else if(isAtivado() == false && anima.get(4) != null){
                    renderAnima(anima.get(4),grafic, imgBanco, rectCamera);
                }
                break;
            case 2://press
                if(isAtivado() == true && anima.get(2) != null){
                    renderAnima(anima.get(2),grafic, imgBanco, rectCamera);
                }
                else if(isAtivado() == false && anima.get(5) != null){
                    renderAnima(anima.get(5),grafic, imgBanco, rectCamera);
                }
                break;
        }
    } 
}
