/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.Classes.Armazenamento;

import java.applet.AudioClip;
import java.net.URL;
import java.util.ArrayList;

/**
 *
 * @author lucas
 */
public class ConjuntoSonsMe {
    private ArrayList<SongBancoMe> songBank;
    
    public ConjuntoSonsMe(){
        songBank = new ArrayList();
    }
    //BANCO DE SONS
    public int addNovoConjunto(String nome){
        if(nome != null){//verifica se o nome não é nulo
            if(nome.length() >= 4){// verifica a quantidade de caracteres
                //verifica se o nome ja existe em outro conjunto de sons
                int index = -1;
                
                for(int i=0;i < songBank.size();i++){
                    if(nome.equals(songBank.get(i).getNome())){
                        index = i;
                    }
                }
                if(index == -1){
                    songBank.add(new SongBancoMe(nome));
                    return songBank.size() - 1;
                }else{
                    throw new IllegalArgumentException("Nome ja existente");
                }
            }else{
                throw new IllegalArgumentException("Nome tem que ter mais de quatro caracters");
            }
        }else{
            throw new IllegalArgumentException("Nome Não Pode ser Null");
        }
    }
    
    public int procurarConjunto(String nome){
        //RETORNA O INDEX SE FOR -1 O CONJUNTO PROCURADO NÃO EXISTE 
        int index = -1;
        //VERIFICA SE O NOME DO CONJUNTO JA EXISTE E CASO NÃO EXISTA PERMITE CRIALO
        for(int i=0;i < songBank.size();i++){
            if(nome.equals(songBank.get(i).getNome())){
                index = i;
            }
        }
        return index;
    }
    
    public void removeConjunto(int conjuntoIndex){
        if(conjuntoIndex < songBank.size()){
            //VERIFICAR SE ALGUM OBJETO FILHO ESTA USANDO ESSE CONJUNTO
            songBank.remove(conjuntoIndex);
        }else{
            throw new IllegalArgumentException("conjunto inexistente");
        }       
    }//precisa de atualização
    
    public void removeSong(int indexConjunto,int songIndex){
        if(indexConjunto < songBank.size()){
            if(songIndex < songBank.get(indexConjunto).getArraySongSize()){
                //VERIFICAR SE NENHUM OBJETO ESTÁ USANDO ESSA IMAGEM ANTES DE APAGAR
                songBank.get(indexConjunto).getSound(songIndex);
            } else{
                throw new IllegalArgumentException("conjunto inexistente");
            }
        }else{
            throw new IllegalArgumentException("conjunto inexistente");
        }
    }
    
    public int sizeConjunto(){
        return songBank.size();
    }
    
    
    public void addSong(int indexConjunto,String address){
        URL addressUrl = getClass().getResource(address);
        songBank.get(indexConjunto).addSons(addressUrl);
    }
 
    public AudioClip getSong(int indexConjunto,int songIndex){
        if(songBank != null && indexConjunto < songBank.size()){
            if(songIndex < songBank.get(indexConjunto).getArraySongSize()){
                return songBank.get(indexConjunto).getSound(songIndex);
            }
                return null;
        }
            return null;
    }

    public void startSound(int indexConjunto,int songIndex){
        if(songBank != null && indexConjunto < songBank.size()){
            if(songIndex < songBank.get(indexConjunto).getArraySongSize()){
                songBank.get(indexConjunto).getSound(songIndex).stop();
                songBank.get(indexConjunto).getSound(songIndex).play();
            }
        }
    }
    
    public void stopSound(int indexConjunto,int songIndex){
        if(songBank != null && indexConjunto < songBank.size()){
            if(songIndex < songBank.get(indexConjunto).getArraySongSize()){
                songBank.get(indexConjunto).getSound(songIndex).stop();
            }
        }
    }
    
    public void startSoundLoop(int indexConjunto,int songIndex){
        if(songBank != null && indexConjunto < songBank.size()){
            if(songIndex < songBank.get(indexConjunto).getArraySongSize()){
                songBank.get(indexConjunto).getSound(songIndex).loop();
            }
        }
    }
    
    
}
