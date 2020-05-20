/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.Classes;
import java.util.ArrayList;

/*
PARAMETROS BASICOS PARA OS OBJETOS QUE SERÃO IMPRESSOS
ARMAZENAMENTO DO "BANCO" DE IMAGENS E SONS
 */
public abstract class BasicParamObject {
   /* private int posX;
    private int posY;
    private int zoom;
    ArrayList<ImagenSongBankRef> imageBkRef; //REFERENCIA DE IMAGENS
    ArrayList<ImagenSongBankRef> songBkRef; //REFERENCIA DE SONS

    //INFORAMÇÃO DO OBJETO QUE ESTA SENDO CRIADO
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
    
    public int getZoom() {
        return zoom;
    }
    public void setZoom(int zoom) {
        if(zoom < 100 || zoom > -100){
            this.zoom = zoom;
        }
        else{
            this.zoom = 0;
        }
    }
    
    //REFERENCIA AO BANCO DE IMAGENS E SONS
    public void addImg(int indexConjunto,int indexElemento){
        imageBkRef.add(new ImagenSongBankRef(indexConjunto,indexElemento,0));
    }
    public int getImgArraySize(){
        return imageBkRef.size();
    }
    
    public ImagenSongBankRef getImg(int indexElemento){
        if(indexElemento < imageBkRef.size()){
            return imageBkRef.get(indexElemento);
        }
        return null;
    }
    public void removeImgRef(int indexElemento){
        if(indexElemento < imageBkRef.size()){
            imageBkRef.remove(indexElemento);
        }
    }
    
    public void addSong(int indexConjunto,int indexElemento){
        songBkRef.add(new ImagenSongBankRef(indexConjunto,indexElemento,1));
    }
    public int getSongArraySize(){
        return songBkRef.size();
    }
    
    public ImagenSongBankRef getSong(int indexElemento){
        if(indexElemento < songBkRef.size()){
            return songBkRef.get(indexElemento);
        }
        return null;
    }
    public void removeSongRef(int indexElemento){
        if(indexElemento < songBkRef.size()){
            songBkRef.remove(indexElemento);
        }
    }
    
    public abstract void create();
    public abstract void logica(int valor);
    public abstract void gameUpdate();
    public abstract ImagenSongBankRef render();
    public abstract void destroy();*/
}
