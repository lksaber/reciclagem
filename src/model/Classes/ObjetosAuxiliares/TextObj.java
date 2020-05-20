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
import model.Classes.Armazenamento.ConjuntoImagenMe;
import model.Classes.Armazenamento.ConjuntoSonsMe;
import model.Classes.MetodosImpostoObj;

/**
 *
 * @author lucas
 */
public class TextObj extends MetodosImpostoObj{
    private int posx;
    private int posy;
    private String text;
    private Font font;
    private int tamFont;
    private Color cor;
    private boolean visivel;
    private Rectangle rect;
    
    public TextObj(int posx,int posy,String text,Font font, int tamFont,Color cor){
        textCreate(posx,posy,text,font,tamFont,cor);
    }
    
    public TextObj(Rectangle rect,String text,Font font, int tamFont,Color cor){
        if(rect!= null){
            if(rect.height >= 100 && rect.width >= 100){
                this.rect = rect;
                textCreate(0,0,text,font,tamFont,cor);
            }else{
                throw new IllegalArgumentException("TextObj: rect.Height, e rect.Width não pode ser menor que 100");
            }
        }else{
            throw new NullPointerException("TextObj: retagulo não pode ser nulo");
        }
        
    }


    private void textCreate(int posx,int posy,String text,Font font, int tamFont,Color cor){
        if(text != null){
            this.posx = posx;
            this.posy = posy;
            this.text = text;
            this.font = font;
            this.cor = cor;
            this.visivel = true;
            
            if(tamFont > 0 && tamFont < 100){
                this.tamFont = tamFont;
            }else{
                this.tamFont = 20;
            }
            
        }else{
            throw new IllegalArgumentException("TextObj: text não pode ser null");
        }
    }
    
    
    public String getText() {
        return text;
    }
    public void setText(String text) {
        if(text != null){
            this.text = text;
        }
    }

    public Font getFont() {
        return font;
    }
    public void setFont(Font font) {
        this.font = font;
    }

    public int getTamFont() {
        return tamFont;
    }
    public void setTamFont(int tamFont) {
        if(tamFont > 0 && tamFont < 100){
            this.tamFont = tamFont;
        }
    }

    public Color getCor() {
        return cor;
    }
    public void setCor(Color cor) {
        this.cor = cor;
    }

    public boolean isVisivel() {
        return visivel;
    }
    public void setVisivel(boolean visivel) {
        this.visivel = visivel;
    }
    
    
    
    
    @Override
    public void gameUpdate(ConjuntoSonsMe sgBanco, Rectangle rectCamera) {
        ///NADA PARA CONSTAR
        //TODO FAZER ALGUMA COISA AQUI
    }
    
    
    @Override
    public void render(Graphics2D grafic, ConjuntoImagenMe imgBanco, Rectangle rectCamera) {
        render(rect,grafic,imgBanco,rectCamera);
    }
    
    public void render(Rectangle rect,Graphics2D grafic, ConjuntoImagenMe imgBanco, Rectangle rectCamera) {
        if(visivel == true){    
            Font antigaFont = grafic.getFont();
            Color antigaColor = grafic.getColor();
        
            try{
                grafic.setFont(this.font);
            }catch(Exception ex){
                grafic.setFont(antigaFont);
            }
            
            if(cor != null){
                grafic.setColor(cor);
            }
            
            Font atualFont = grafic.getFont();
            grafic.setFont(atualFont.deriveFont((float)tamFont));

            //então compensa a posição escolhida pelo usuario
            int tamanhoY =(int) grafic.getFontMetrics(grafic.getFont()).getStringBounds(text, grafic).getHeight();
            int tamanhoX =(int) grafic.getFontMetrics(grafic.getFont()).getStringBounds(text, grafic).getWidth();
            if(rect != null){
                int newPosX = 0;
                int newPosY = 0;
                
                if(tamanhoX > rect.width){
                    newPosX = (tamanhoX/2) - (rect.width/2);
                }else{
                    newPosX = (rect.width/2) - (tamanhoX/2);
                }
                newPosX += rect.x;
                
                if(tamanhoY > rect.height){
                    newPosY = (tamanhoY/2) - (rect.height/2);
                }else{
                    newPosY = (rect.height/2) - (tamanhoY/2);
                }
                newPosY += rect.y;
                
                grafic.drawString(text, newPosX, newPosY + tamanhoY);
            }else{
                grafic.drawString(text, posx, posy + tamanhoY);
            }
            
            ///RECUPERA AS ANIGAS FONT E COLOR
            grafic.setFont(antigaFont);
            grafic.setColor(antigaColor);        
        }
    }
    
}
