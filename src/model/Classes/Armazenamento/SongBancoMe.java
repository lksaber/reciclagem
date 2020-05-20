/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.Classes.Armazenamento;

import java.applet.Applet;
import java.applet.AudioClip;
import java.net.URL;
import java.util.ArrayList;

/**
 *
 * @author lucas
 */
public class SongBancoMe {
    private String nome;
    private ArrayList<AudioClip> song;

    
    public SongBancoMe(String nome){
        this.nome = nome;
        song = new ArrayList();
    }

    public String getNome(){
        return nome;
    }
    
    public void addSons(URL endereco){
        try{
            //CARREGANDO SONS NA MEMORIA
            song.add(Applet.newAudioClip(endereco));
        }catch(Exception ex){
            //CASO NÃO COSIGA CARREGAR
            throw new IllegalArgumentException(" load sons erro de arquivo");
        }  
    }

    public AudioClip getSound(int songIndex){
        if(song != null && song.isEmpty() == false){
            return song.get(songIndex);
        }
        return null;
    }
    
    public void removeSong(int songIndex){
        if(songIndex < song.size()){
            song.remove(songIndex);
        }
    }
    
    public int getArraySongSize(){
        if(song == null || song.isEmpty()){
            return -1;
        }
        return song.size();
    }
}
