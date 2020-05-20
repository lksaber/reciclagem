/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.Classes.ObjetosAuxiliares;

import java.util.ArrayList;
import model.Classes.Armazenamento.SongReference;

/**
 *
 * @author lucas
 */
public class ButtonAnimaDesing {
    private final ArrayList<AnimaObj2D> anima; //array list que armazena a animação

    private SongReference songSelect; //armazena a referencia do som no banco
    private SongReference songPress; //armazena a referencia do som no banco
    private SongReference songPressInative; //armazena a referencia do som no banco
    
    
    public ButtonAnimaDesing(
            AnimaObj2D aniDeselect,AnimaObj2D aniSelect,AnimaObj2D aniPress,AnimaObj2D aniInative,
            AnimaObj2D aniInativeSel,AnimaObj2D aniInativePress,
            SongReference songSelect,SongReference songPress,SongReference songPressInative
    ){
    
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
    
    
    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////GET E SETER SONS////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////
    public SongReference getSongSelect() {
        return songSelect;
    }
    public void setSongSelect(SongReference songSelect) {
        this.songSelect = songSelect;
    }
    
    public SongReference getSongPress() {
        return songPress;
    }
    public void setSongPress(SongReference songPress) {
        this.songPress = songPress;
    }
    
    public SongReference getSongPressInative() {
        return songPressInative;
    }
    public void setSongPressInative(SongReference songPressInative) {
        this.songPressInative = songPressInative;
    }
}
