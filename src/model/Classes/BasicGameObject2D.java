/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.Classes;

import java.awt.Rectangle;
import java.util.ArrayList;

/*
ELEMENTOS DE JOGO QUE POSSUEM COLISÃO USAR ESSA CLASSE
ARMAZENAMENTO DOS RETANGULOS DE COLISÃO
ARMAZENAMENTO DAS REFERENCIAS DE IMAGENS
 */
public abstract class BasicGameObject2D extends BasicParamObject{
   /*/ private int angulo;
    protected ArrayList<Rectangle> rect2D;
    
    public Rectangle getRectObject(int rectIndex) {
        if(rectIndex < rect2D.size()){
            return rect2D.get(rectIndex);
        }else{
            return null;
        }
    }

        ////METODOS QUE SERVEM APENAS PARA CALCULOS DE COMPARAÇÃO/////
    public int TestdeColisaoX(int index,int posX,int posY,Rectangle rectObject){
        //MOSTRA SE A DIRETA DESTE OBJETO ESTÁ COLLIDINDO COM A ESQUERDA DE OUTRO OBJETO
        //O CALCULO SE BASEIA INDO ATÉ A METADE DO OBJETO
        //Retorno 0 = sem colisão / 1 = direita/ 2 = esquerda
        
        if(index < this.rect2D.size()){
            Rectangle estObj = new Rectangle();
            Rectangle outObj = new Rectangle();

            
            estObj.x = this.getPosX();
            estObj.width = rect2D.get(index).width/2;
            
            estObj.y = this.getPosY();
            estObj.height = rect2D.get(index).height;
            
            outObj.x = posX;
            outObj.width = rectObject.width/2;
            
            outObj.y = posY;
            outObj.height = rectObject.height;
            
            
            /////////CALCULANDO////////////
            if(outObj.x > (estObj.x + estObj.width) && outObj.x < estObj.x + (estObj.width*2)){ 
                //calculo x // testando a direita
                //calculo y
                if(outObj.y > estObj.y && outObj.y < estObj.y + estObj.height ||
                    outObj.y + outObj.height > estObj.y && outObj.y + outObj.height < estObj.y + estObj.height){ 
                   return 1;
                    
                }else if(estObj.y > outObj.y && estObj.y < outObj.y + outObj.height ||
                    estObj.y + estObj.height > outObj.y && estObj.y + estObj.height < outObj.y + outObj.height){ 
                    return 1;
                }
            }
            else if(estObj.x > (outObj.x + outObj.width) && estObj.x < outObj.x + (outObj.width*2)){ 
                //calculo x // testando a esquerda
                //calculo y
                if(outObj.y > estObj.y && outObj.y < estObj.y + estObj.height ||
                    outObj.y + outObj.height > estObj.y && outObj.y + outObj.height < estObj.y + estObj.height){ 
                   return 2;
                    
                }else if(estObj.y > outObj.y && estObj.y < outObj.y + outObj.height ||
                    estObj.y + estObj.height > outObj.y && estObj.y + estObj.height < outObj.y + outObj.height){ 
                    return 2;
                }
            }
            return 0;
        }
        return 0;
    }
    public int TestdeColisaoY(int index,int posX,int posY,Rectangle rectObject){
        //MOSTRA SE A DIRETA DESTE OBJETO ESTÁ COLLIDINDO COM A ESQUERDA DE OUTRO OBJETO
        //O CALCULO SE BASEIA INDO ATÉ A METADE DO OBJETO
        //Retorno 0 = sem colisão / 1 = cima/ 2 = baixo
        
        if(index < this.rect2D.size()){
            Rectangle estObj = new Rectangle();
            Rectangle outObj = new Rectangle();

            
            estObj.x = this.getPosX();
            estObj.width = rect2D.get(index).width;
            
            estObj.y = this.getPosY();
            estObj.height = rect2D.get(index).height/2;
            
            outObj.x = posX;
            outObj.width = rectObject.width;
            
            outObj.y = posY;
            outObj.height = rectObject.height/2;
            
            
            /////////CALCULANDO////////////
            if(estObj.y > (outObj.y + outObj.height) && estObj.y < outObj.y + (outObj.height*2)){ 
                //calculo y // testando a para cima
                //calculo x
                if(outObj.x > estObj.x && outObj.x < estObj.x + estObj.width ||
                    outObj.x + outObj.width > estObj.x && outObj.x + outObj.width < estObj.x + estObj.width){ 
                   return 1;
                    
                }else if(estObj.x > outObj.x && estObj.x < outObj.x + outObj.width ||
                    estObj.x + estObj.width > outObj.x && estObj.x + estObj.width < outObj.x + outObj.width){ 
                    return 1;
                }
            }
            if(outObj.y > (estObj.y + estObj.height) && outObj.y < estObj.y + (estObj.height*2)){ 
                //calculo y // testando para  baixo
                //calculo x
                if(outObj.x > estObj.x && outObj.x < estObj.x + estObj.width ||
                    outObj.x + outObj.width > estObj.x && outObj.x + outObj.width < estObj.x + estObj.width){ 
                   return 2;
                    
                }else if(estObj.x > outObj.x && estObj.x < outObj.x + outObj.width ||
                    estObj.x + estObj.width > outObj.x && estObj.x + estObj.width < outObj.x + outObj.width){ 
                    return 2;
                }
            }
            return 0;
        }
        return 0;
    }*/
}
