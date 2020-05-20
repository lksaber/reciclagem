/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.Classes.ObjetosAuxiliares;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import model.Classes.Armazenamento.ConjuntoImagenMe;
import model.Classes.Armazenamento.ConjuntoSonsMe;
import model.Classes.MetodosImpostoObj;

/**
 *
 * @author lucas
 */
/*
UM OBJETO SIMPLIFICADO PERMITINDO A CRIAÇÃO DOS ELEMENTOS QUE
COMPONHEM O BACKGROUNDS POSSUINDO DIVERSAS CONFIGURAÇÕES
pxu = pixel por atualização
*/

public class BackGroundObject extends MetodosImpostoObj {
    private AnimaObj2D animaObj; //OBJETO QUE CARREGA AS IMAGENS
    private int speedXpxu; //PIXEL POR ATUALIZAÇÃO QUE O OBJETO VAI PERCORRER
    private int speedYpxu; //PIXEL POR ATUALIZAÇÃO QUE O OBJETO VAI PERCORRER
    private boolean repetir;// SE O OBETO VAI SE REPETIR DIVERSAS VEZES NA TELA
    private int sangria;//DISTANCIA ENTRE OS OBJETOS SE ELES SE REPETIREM
    private boolean submCamera;/*
    INDICA SE O OBJETO É UM SUBMETIDO AO MOVIMENTO DA CAMERA OU SEJA SÓ SE MOVIMENTA
    CONFORME O MOVIMENTO DA CAMERA 
    */
    private Point pointFantasma; //Ajuda na criação de um elemento fantasma
    private Point pointFantasma2; //Ajuda na criação de um elemento fantasma      
    
    public BackGroundObject(AnimaObj2D animaObj, int speedXpxu, int speedYpxu){
        if(animaObj != null){
            this.animaObj = animaObj;
            this.speedXpxu = speedXpxu;
            this.speedYpxu = speedYpxu;
            this.submCamera = false;
            this.repetir = false;
            this.sangria = 0;
            pointFantasma = new Point(); 
            pointFantasma2 = new Point(); 
        }else{
            throw new IllegalArgumentException("BgObjeto: Objeto de animção deve conter ao menos uma imagem");
        }
    }
    public BackGroundObject(AnimaObj2D animaObj, int speedXpxu, int speedYpxu,boolean subjectCamera,boolean repetir,int sangria){
        if(animaObj != null){
            this.animaObj = animaObj;
            this.speedXpxu = speedXpxu;
            this.speedYpxu = speedYpxu;
            this.submCamera = subjectCamera;
            this.repetir = repetir;
            this.sangria = sangria;
            
            pointFantasma = new Point(); 
            pointFantasma2 = new Point(); 
            
            if(repetir == true){
                pointFantasma.x = animaObj.Tranform().getPosx();
                pointFantasma.y = animaObj.Tranform().getPosy();
            }
        }else{
            throw new IllegalArgumentException("BgObjeto:Objeto de animção deve conter ao menos uma imagem");
        }
    }
    
    //ADICIONANDO UMA NOVO OBJETO DE ANIMAÇÃO OU RECUPERANDO O ATUAL PARA MODIFICAÇÃO
    public AnimaObj2D getSimplesObjeto() {
        return animaObj;
    }
    public void setSimplesObjeto(AnimaObj2D animaObj) {
        if(animaObj != null){
            this.animaObj = animaObj;
        }
    }
    
    //ATUALIZAÇÃO DE VELOCIDADE
    public int getSpeedXpxu() {
        return speedXpxu;
    }
    public void setSpeedXpxu(int speedXpxu) {
        this.speedXpxu = speedXpxu;
    }

    public int getSpeedYpxu() {
        return speedYpxu;
    }
    public void setSpeedYpxu(int speedYpxu) {
        this.speedYpxu = speedYpxu;
    }
    
    //INDICAÇÃO DE SUBJETIVO A CAMERA
    public boolean isSubmCamera(){
        return submCamera;
    }
    public void setSubmCamera(boolean submCamera) {
        this.submCamera = submCamera;
    }
    
    //PERMIÇÃO DE REPETIÇÃO E VALOR DE SANGRIA E Pixel
    public boolean isRepetir() {
        return repetir;
    }
    public void setRepetir(boolean repetir) {
        this.repetir = repetir;
    }

    public int getSangriaPx() {
        return sangria;
    }
    public void setSangriaPx(int sangria) {
        this.sangria = sangria;
    }

    
    //////////////////////////////////////////////////////////
    /////METODOS OBRIGATORIOS DA CLASSE MetodosImpostoObj/////
    //////////////////////////////////////////////////////////
    
    //ATUALIZAÇÃO DOS QUADROS E EXECUÇÃO DOS SONS
    @Override
    public void gameUpdate(ConjuntoSonsMe sgBanco,Rectangle rectCamera){
        if(repetir == true){//criando imagens fantasmas
            pointFantasma.x += speedXpxu;
            pointFantasma.y += speedYpxu;
        }
        else{
            animaObj.Tranform().setPosx(animaObj.Tranform().getPosx() + speedXpxu);//ATUALIZA A POSIÇÃO DA ANIMAÇÃO X
            animaObj.Tranform().setPosy(animaObj.Tranform().getPosy() + speedYpxu);//ATUALIZA A POSIÇÃO DA ANIMAÇÃO Y
        }
        animaObj.gameUpdate(sgBanco,rectCamera);//executa a logica da animação
    }
    
    //EXIBIÇÃO DAS IMAGENS
    @Override
    public void render(Graphics2D grafic,ConjuntoImagenMe imgBanco,Rectangle rectCamera){
        if(repetir == true){//criando imagens fantasmas
            int NumeroElementos;
            Point pointTamImage = animaObj.getTamPrimeiraImagen(imgBanco);
            
            if(pointTamImage != null){
                //Calculo do numero de elementos que serão impressos
                NumeroElementos = (rectCamera.width/(pointTamImage.x+sangria)+1);
                
                if(NumeroElementos < 1){
                    NumeroElementos = 1;
                }
                
                //POSICIONAMENTO DOS ELEMENTOS FANTASMAS PRA CRIAR DUAS POSIÇOES 
                //AFIM DE PERMITIR A ROTAÇÃO DE MULTIPLOS ELEMENTOS
                pointFantasma2.x = pointFantasma.x - ((pointTamImage.x + sangria)*NumeroElementos);
                pointFantasma2.y = pointFantasma.y;
                if(speedXpxu < 0){                
                    if(pointFantasma.x < 0){
                        pointFantasma2.x = pointFantasma.x ;
                        pointFantasma.x = pointFantasma2.x + ((pointTamImage.x + sangria)*NumeroElementos);
                    }
                }
                
                if(speedXpxu > 0){                
                    if(pointFantasma2.x > 0){
                        pointFantasma.x = pointFantasma2.x;
                        pointFantasma2.x = pointFantasma.x - ((pointTamImage.x + sangria)*NumeroElementos);
                    }
                }
                
                //FUNÇÃO 01 AJUSTADA
                for(int i = 0; i < NumeroElementos; i++){
                    animaObj.Tranform().setPosx((pointFantasma.x + sangria) + ((pointTamImage.x + sangria) * i));
                    animaObj.Tranform().setPosy(pointFantasma.y);
                    animaObj.render(grafic, imgBanco,rectCamera);//executa as imagens da animação
                    
                    animaObj.Tranform().setPosx((pointFantasma2.x + sangria) + ((pointTamImage.x + sangria) * i));
                    animaObj.Tranform().setPosy(pointFantasma2.y);
                    animaObj.render(grafic, imgBanco,rectCamera);//executa as imagens da animação
                }
            }
        }
        else{
            animaObj.render(grafic, imgBanco,rectCamera);//executa as imagens da animação
        }
    }
}
