/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.Classes.ObjetosAuxiliares;

import java.awt.Point;

/**
 *
 * @author lucas
 */
/*
CONFIGURAÇÃO BASICA DO GAME
RESOLUÇÃO
E VOLUME DOS SONS
*/
public class GameConfig{
    private Point resolucao;
    private boolean fullResolucao;
    private int musicVolume;
    private int efectVolume;
    
    public GameConfig(int escolhaResoluca,boolean fullResolucao,int musicVolume,int efectVolume){
        setResolucao(escolhaResoluca);
        setFullResolucao(fullResolucao);
        setMusicVolume(musicVolume);
        setEfectVolume(efectVolume);
    }
    
    public Point getResolucao(){
        return resolucao;
    }
    public void setResolucao(int escolhaResolucao){
        if(escolhaResolucao == 0){
            resolucao.x = 800;
            resolucao.y = 600;
        }
        else{
            resolucao.x = 1024;
            resolucao.y = 768;
        }
    }
    
    public boolean getFullResolucao(){
        return fullResolucao;
    }
    public void setFullResolucao(boolean fullResolucao){
        this.fullResolucao = fullResolucao; 
    }
    
    public int getMusicVolume(){
        return musicVolume;
    }
    public void setMusicVolume(int musicVolume){
        if(musicVolume > 100){
            this.musicVolume = 100;
        }
        else if(musicVolume < 0){
            this.musicVolume = 0;
        }
        this.musicVolume = musicVolume;
    }
    
    public int getEfectVolume(){
        return efectVolume;
    }
    
    public void setEfectVolume(int efectVolume){
        if(efectVolume > 100){
            efectVolume = 100;
        }
        else if (efectVolume < 100){
            efectVolume = 0;
        }
        this.efectVolume = efectVolume;
    }
    
}
