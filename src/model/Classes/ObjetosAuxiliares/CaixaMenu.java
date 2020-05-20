/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.Classes.ObjetosAuxiliares;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

/**
 *
 * @author lucas
 */
public class CaixaMenu {
    private double ajuntPosx;
    private double ajuntPosy;
    private double ajuntTamx;
    private double ajuntTamy;
    
    
    private int posx;
    private int posy;
    private int tamx;
    private int tamy;
    private int radio;
    private int velPxUp;
    private Color corCaixa;
    private int tamContorno;
    private Color corContorno;
    private boolean start;
    private  boolean visible;
    private boolean rebobine;
    
    public CaixaMenu(int posx,int posy,int tamx,int tamy,Color corCaixa){
        this(posx,posy,tamx,tamy,0,0,corCaixa,0,null);
    }
    
    public CaixaMenu(int posx,int posy,int tamx,int tamy,Color corCaixa,int radio){
        this(posx,posy,tamx,tamy,radio,0,corCaixa,0,null);
    }
    
    public CaixaMenu(int posx,int posy,int tamx,int tamy,int velPxUp,Color corCaixa){
        this(posx,posy,tamx,tamy,0,velPxUp,corCaixa,0,null);
    }
    
    public CaixaMenu(int posx,int posy,int tamx,int tamy,int radio,int velPxUp,Color corCaixa){
        this(posx,posy,tamx,tamy,radio,velPxUp,corCaixa,0,null);
    }
    
    public CaixaMenu(
            int posx,int posy,int tamx,int tamy,int radio,int velPxUp,
            Color corCaixa,int tamContorno,Color corContorno
    ){
        this.posx = posx;
        this.posy = posy;
        this.tamx = tamx;
        this.tamy = tamy;
        this.radio = radio;
        this.velPxUp = velPxUp;
        this.tamContorno = tamContorno;
        this.corContorno = corContorno;
        this.corCaixa = corCaixa;
        start = true;
        visible = true;
        rebobine = false;
    }

    public int getPosx() {
        return posx;
    }
    public void setPosx(int posx) {
        if(start == false){
            this.posx = posx;
        }
    }

    public int getPosy() {
        return posy;
    }
    public void setPosy(int posy) {
        if(start == false){
            this.posy = posy;
        }
    }

    public int getTamx() {
        return tamx;
    }
    public void setTamx(int tamx) {
        if(start == false){
            this.tamx = tamx;
        }
    }

    public int getTamy() {
        return tamy;
    }
    public void setTamy(int tamy) {
        if(start == false){
            this.tamy = tamy;
        }
    }

    public int getVelPxUp() {
        return velPxUp;
    }
    public void setVelPxUp(int velPxUp) {
        if(start == false){
            this.velPxUp = velPxUp;
        }
    }

    public int getRadio() {
        return radio;
    }
    public void setRadio(int radio) {
        if(start == false){
            this.radio = radio;
        }
    }

    public Color getCorCaixa() {
        return corCaixa;
    }
    public void setCorCaixa(Color corCaixa) {
        if(corCaixa != null && start == false){
            this.corCaixa = corCaixa;
        }
    }

    public int getTamContorno() {
        return tamContorno;
    }
    public void setTamContorno(int tamContorno) {
        if(tamContorno > -1 && start == false){
            this.tamContorno = tamContorno;
        }
    }

    public Color getCorContorno() {
        return corContorno;
    }
    public void setCorContorno(Color corContorno) {
        if(start == false && corContorno != null){
            this.corContorno = corContorno;
        }
    }

    public Rectangle getRect(){
        return new Rectangle(posx,posy,tamx,tamy);
    }
    
    
    public boolean isStart() {
        return start;
    }
    public boolean isPause(){
        return visible == true && start == false; 
    }  
    
    public void start() {
        visible = true;
        this.start = true;
    }
    public void pause(){
        visible = true;
        this.start = false;
    }
    public void stop(){
        visible = false;
        this.start = false;
    }
    public void reiniciar(){
        ajuntPosx = 0;
        ajuntPosy = 0;
        ajuntTamx = 0; 
        ajuntTamy = 0;
    }
    public void rebobinar(boolean rebobine){
        this.rebobine = rebobine;
    }
    
    
    
    
    public void logica(){
        if(start == true){
            if(velPxUp > 0){
                if(rebobine == false && ajuntTamx < tamx){
                    ajuntTamx +=  tamx/velPxUp;
                    ajuntTamy +=  tamy/velPxUp;
                
                    ajuntPosx = ((tamx/2) - (ajuntTamx/2)) + posx;
                    ajuntPosy = ((tamy/2) - (ajuntTamy/2)) + posy;       
                }else if(rebobine == false && ajuntTamx >= tamx){
                    ajuntTamx = tamx;
                    ajuntTamy = tamy;
                    rebobine = true;
                    pause();
                }
                else if(rebobine == true && ajuntTamx > 0){
                    ajuntTamx -=  tamx/velPxUp;
                    ajuntTamy -=  tamy/velPxUp;
                    ajuntPosx = ((tamx/2) - (ajuntTamx/2)) + posx;
                    ajuntPosy = ((tamy/2) - (ajuntTamy/2)) + posy;
                }else if(rebobine == true && ajuntTamx <= 0){
                    ajuntTamx = 0;
                    ajuntTamy = 0;
                    rebobine = false;
                    stop();
                }
            }else{
                ajuntTamx =  tamx;
                ajuntTamy =  tamy;
                ajuntPosx = posx;
                ajuntPosy = posy;
            }
        }  
    }
    
    public void grafic(Graphics2D grafic){ 
        if(visible == true){
            Color antigaCor = grafic.getColor();
            
            if(tamContorno > 0){
                if(corContorno != null){
                    grafic.setColor(corContorno);
                }
                grafic.fillRoundRect((int) ajuntPosx - tamContorno, (int) ajuntPosy - tamContorno,
                (int)ajuntTamx + (tamContorno*2), (int)ajuntTamy + (tamContorno*2), radio, radio);
                grafic.setColor(antigaCor);
            }
            if(corCaixa != null){
                grafic.setColor(corCaixa);
            }
            grafic.fillRoundRect((int) ajuntPosx, (int) ajuntPosy, (int)ajuntTamx, (int)ajuntTamy, radio, radio);
            
            grafic.setColor(antigaCor);
        }
    }
}
