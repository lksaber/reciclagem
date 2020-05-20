/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.Classes.ObjetosAuxiliares;


import java.awt.Color;
import java.awt.Font;
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

/*
    CRIA UM ELEMENTO DO TIPO BOTÃO COMUM QUE TERA LOGICA POSIÇÃO DESING ENTRE OUTROS

*/
public class ButtonTextObj extends BasicButtonObject{
    /////DESING////
    private int posX;
    private int posY;
    private String text;// texto unico
    private String textInativo;
    private ArrayList<Color> colorString; //diversos textos
    
    private int tamFont;
    private Font font;
    
    private SongReference songSelect; //armazena a referencia do som no banco
    private SongReference songPress; //armazena a referencia do som no banco
    private SongReference songPressInative; //armazena a referencia do som no banco
    
    private boolean songAtive;    
  
    
    ///////////////////////CONTRUTOR COM TEXTO UNICO////////////////////////////
    public ButtonTextObj(
            int posX,int posY,String text,String textInativo,int tamFont,Font font,
            ButtonTextDesing buttonDesing
    ){
                this(posX,posY,text,textInativo,tamFont,font,
                    buttonDesing.getColorDeselect(),buttonDesing.getColorSelect(),
                    buttonDesing.getColorPress(),buttonDesing.getColorInative(),
                    buttonDesing.getColorInativeSel(),buttonDesing.getColorInativePress(),
                    buttonDesing.getSongSelect(),buttonDesing.getSongPress(),
                    buttonDesing.getSongPressInative());  
    }
    
    public ButtonTextObj(
            int posX,int posY,String text,String textInativo,int tamFont,Font font,
            Color colorDeselect,Color colorSelect,Color colorPress,Color colorInative,
            Color colorInativeSel,Color colorInativePress
    ){
            this(posX,posY,text,textInativo,tamFont,font,colorDeselect,colorSelect,colorPress,
                    colorInative,colorInativeSel,colorInativePress,null,null,null);
    }

    public ButtonTextObj(
            int posX,int posY,String text,String textInativo,int tamFont,Font font,
            Color colorDeselect,Color colorSelect,Color colorPress,Color colorInative,
            Color colorInativeSel,Color colorInativePress,
            SongReference songSelect,SongReference songPress,SongReference songPressInative
    ){
        if(text != null){
            if(text.length() < 31){
                this.posX = posX;
                this.posY = posY;
                this.text = text;
                this.textInativo = textInativo;
        
                colorString = new ArrayList(6);
                colorString.add(colorDeselect);
                colorString.add(colorSelect);
                colorString.add(colorPress);
                colorString.add(colorInative);
                colorString.add(colorInativeSel);
                colorString.add(colorInativePress);

                this.songAtive = false;
        
                this.songSelect = songSelect;
                this.songPress = songPress;
                this.songPressInative = songPressInative; 
        
                setTamFont(tamFont);
                this.font = font;
            }else{
                throw new IllegalArgumentException("ButtonTextObj: text ou textInvative > 30 caracteres");
            }   
        }
        else{
            throw new IllegalArgumentException("ButtonTextObj: text e textInvative não pode nulos");
        }
    }

     ////////////////////////////////////////////////////////////////////////////
    ///////////////////GET E SETER FONT TAMFONT POSIÇOES////////////////////////
    ////////////////////////////////////////////////////////////////////////////
    public int getTamFont() {
        return tamFont;
    }
    public void setTamFont(int tamFont) {
        if(tamFont > 0 && tamFont < 100){
            this.tamFont = tamFont;
        }else{
            this.tamFont = 20;
        }
    }

    public Font getFont() {
        return font;
    }
    public void setFont(Font font) {
        this.font = font;
    }

    public int getPosX() {
        return posX;
    }
    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }
    public void setPosY(int posY) {
        this.posY = posY;
    }

    public String getText() {
        return text;
    }
    public void setText(String text) {
        if(text != null && text.length() < 31){
            this.text = text;
        }
    }

    public String getTextInativo() {
        return textInativo;
    }
    public void setTextInativo(String textInativo) {
        if(textInativo != null && textInativo.length() < 31){
            this.textInativo = textInativo;
        }
    }
    
    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////GET E SETER CORES E SONS////////////////////////////
    ////////////////////////////////////////////////////////////////////////////
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
    
    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////SISTEMA, LOGICA E IMPRESSÃO/////////////////////////
    ////////////////////////////////////////////////////////////////////////////
    
    @Override
    public void mudanca() {
        this.songAtive = true;
        
        System.out.println("mudando?");
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

    @Override
    public void render(Graphics2D grafic, ConjuntoImagenMe imgBanco, Rectangle rectCamera) {
        ///SALVA AS ANTIGAS FONT E COLOR DA GRAFICS
        Font antigaFont = grafic.getFont();
        Color antigaColor = grafic.getColor();
        
        try{
            grafic.setFont(this.font);
        }catch(Exception ex){
            grafic.setFont(antigaFont);
        }
        
        Font atualFont = grafic.getFont();
        grafic.setFont(atualFont.deriveFont((float)tamFont));
        
        switch(getEstado()){
            case 0://deselect
                if(isAtivado() == true && colorString.get(0) != null){
                    grafic.setColor(colorString.get(0));
                }
                else if(isAtivado() == false && colorString.get(3) != null){
                    grafic.setColor(colorString.get(3));
                }
                break;
            case 1://Select
                if(isAtivado() == true && colorString.get(1) != null){
                    grafic.setColor(colorString.get(1));
                }
                else if(isAtivado() == false && colorString.get(4) != null){
                    grafic.setColor(colorString.get(4));
                }
                break;
            case 2://press
                if(isAtivado() == true && colorString.get(2) != null){
                    grafic.setColor(colorString.get(2));
                }
                else if(isAtivado() == false && colorString.get(5) != null){
                    grafic.setColor(colorString.get(5));
                }
                break;
        }
        
        int compensaY = 0; //faz a compençação da fonte ja que a fonte é impressa de cima para baixo
        //então compensa a posição escolhida pelo usuario
        if(isAtivado() == true || (isAtivado() == false && textInativo == null)){
            compensaY =(int) grafic.getFontMetrics(grafic.getFont()).getStringBounds(text, grafic).getHeight();
            grafic.drawString(text, posX, posY + compensaY);
        }else{
            compensaY =(int) grafic.getFontMetrics(grafic.getFont()).getStringBounds(textInativo, grafic).getHeight();
            grafic.drawString(textInativo, posX, posY + compensaY);
        }

        ///RECUPERA AS ANIGAS FONT E COLOR
        grafic.setFont(antigaFont);
        grafic.setColor(antigaColor);
    }
}
 