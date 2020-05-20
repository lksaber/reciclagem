/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.Classes.ObjetosAuxiliares;

import java.awt.Rectangle;

/**
 *
 * @author lucas
 */
/*CONTROLE DA CAMERA PERMITINDO ELA PASSEAR PELO PLANO DA JANELA TENDO COMO POR 
PADRÃO DE CONTROLE O TAMANHO DE 1024 x 768, todos os calculos do plano levam essa
resolução como principal configuração para ser feito os calculos, depois a imagem
sobre uma distorção para ser colocada em outras resoluções
*/

public class Camera {
    private Rectangle rect;
    private Rectangle rectMax;
    
    public Camera(){
        rect = new Rectangle();
        rect.x = 0;
        rect.y = 0;
        rect.height = 768;
        rect.width = 1024;
        
        rectMax = new Rectangle();
        rectMax.x = 0;
        rectMax.y = 0;
        rectMax.height = 768;
        rectMax.width = 1024;
    }
    
    public Rectangle getRectCamera(){
        return rect;
    }
    
    public void setRectCamera(int posx, int posy){
        if(posx + rect.width > rectMax.width){
            posx = posx - ((posx + rect.width) - rectMax.width);
        }
        if(posy + rect.height > rectMax.height){
            posy = posy - ((posy + rect.height) - rectMax.height);
        }
        if(posx < rectMax.x){
            posx = rectMax.x;
        }
        if(posy < rectMax.y){
            posy = rectMax.y;
        }
    }
    
    public Rectangle getRectMax(){
        return rectMax;
    }
    public void setRectMax(int MinX,int MinY,int MaxX,int MaxY){
        //define qual o minimo que a camera pode ir e o maximo
        if(MaxX < MinX + rect.width){
            MaxX = MinX + rect.width;
        }
        if(MaxY < MinY + rect.height){
            MaxY = MinY + rect.height;
        }
    }
}
