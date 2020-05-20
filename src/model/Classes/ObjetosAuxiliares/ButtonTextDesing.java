/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.Classes.ObjetosAuxiliares;

import java.awt.Color;
import java.util.ArrayList;
import model.Classes.Armazenamento.SongReference;



/**
 *
 * @author lucas
 */
/*
CARREGA AS OPÇOES DE DESING PARA O BOTÃO 
*/

public class  ButtonTextDesing{
    private final ArrayList<Color> colorString; //diversos textos
    private SongReference songSelect; //armazena a referencia do som no banco
    private SongReference songPress; //armazena a referencia do som no banco
    private SongReference songPressInative; //armazena a referencia do som no banco
    
     public ButtonTextDesing(Color colorDeselect,Color colorSelect){
         this(colorDeselect,colorSelect,null,null,null,null,null,null,null);
     }
    
    public ButtonTextDesing(
            Color colorDeselect,Color colorSelect,
            SongReference songSelect,SongReference songPress
    ){
        this(colorDeselect,colorSelect,null,null,null,null,songSelect,songPress,null);
    }
    
    public ButtonTextDesing(
            Color colorDeselect,Color colorSelect,Color colorPress,Color colorInative,
            Color colorInativeSel,Color colorInativePress,
            SongReference songSelect,SongReference songPress,SongReference songPressInative
    ){
    
        colorString = new ArrayList(6);
        colorString.add(colorDeselect);
        colorString.add(colorSelect);
        colorString.add(colorPress);
        colorString.add(colorInative);
        colorString.add(colorInativeSel);
        colorString.add(colorInativePress);
        
        this.songSelect = songSelect;
        this.songPress = songPress;
        this.songPressInative = songPressInative; 
    }
    
    public Color getColorDeselect() {
        return colorString.get(0);
    }
    public void setColorDeselect(Color colorDeselect){
        this.colorString.set(0,colorDeselect);
    }
    
    public Color getColorSelect() {
        return colorString.get(1);
    }  
    public void setColorSelect(Color colorSelect){
        this.colorString.set(1,colorSelect);
    }
    
    public Color getColorPress() {
        return colorString.get(2);
    }
    public void setColorPress(Color colorPress){
        this.colorString.set(2,colorPress);
    }
    
    public Color getColorInative() {
        return colorString.get(3);
    }
    public void setColorInative(Color colorInative){
        this.colorString.set(3,colorInative);
    }
    
    public Color getColorInativeSel() {
        return colorString.get(4);
    }
    public void setColorInativeSel(Color colorInativeSel){
        this.colorString.set(4,colorInativeSel);
    }
    
    public Color getColorInativePress() {
        return colorString.get(5);
    }
    public void setColorInativePress(Color colorInativePress){
        this.colorString.set(5,colorInativePress);
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
