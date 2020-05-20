/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.Classes.ObjetosAuxiliares;

/**
 *
 * @author lucas
 */
public class LoaderReturn{
    private int indexImg;
    private int indexSong;

    public LoaderReturn(int indexImg,int indexSong){
        this.indexImg = indexImg;
        this.indexSong = indexSong;
    }
    
    public int getIndexImg() {
        return indexImg;
    }

    public void setIndexImg(int indexImg) {
        this.indexImg = indexImg;
    }

    public int getIndexSong() {
        return indexSong;
    }

    public void setIndexSong(int indexSong) {
        this.indexSong = indexSong;
    }
}