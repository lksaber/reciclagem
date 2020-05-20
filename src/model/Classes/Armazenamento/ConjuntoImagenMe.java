/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.Classes.Armazenamento;

import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.ArrayList;

/**
 *
 * @author lucas
 */
public class ConjuntoImagenMe {
    private  ArrayList<ImagenBancoMe> imageBanco;
    
    public ConjuntoImagenMe(){
        imageBanco = new ArrayList();
    }
    
    //BANCO DE IMAGENS
    public int addNovoConjunto(String nome){
        if(nome != null){//verifica se o nome não é nulo
            if(nome.length() >= 4){// verifica a quantidade de caracteres
                //verifica se o nome ja existe em outro conjunto de sons
                int index = -1;
                
                for(int i=0;i < imageBanco.size();i++){
                    if(nome.equals(imageBanco.get(i).getNome())){
                        index = i;
                    }
                }
                if(index == -1){
                    imageBanco.add(new ImagenBancoMe(nome));
                    return imageBanco.size() - 1;
                }else{
                    throw new IllegalArgumentException("ConjuntoImagenMe: load imagens erro de arquivo"); 
                }
            }else{
                throw new IllegalArgumentException("ConjuntoImagenMe: load imagens erro de arquivo"); 
            }
        }else{
            throw new IllegalArgumentException("ConjuntoImagenMe: load imagens erro de arquivo"); 
        }
    }
    
    public int procurarConjunto(String nome){
        //RETORNA O INDEX SE FOR -1 O CONJUNTO PROCURADO NÃO EXISTE 
        int index = -1;
        //VERIFICA SE O NOME DO CONJUNTO JA EXISTE E CASO NÃO EXISTA PERMITE CRIALO
        for(int i=0;i < imageBanco.size();i++){
            if(nome.equals(imageBanco.get(i).getNome())){
                index = i;
            }
        }
        return index;
    }
    
    public void removeConjunto(int conjuntoIndex){
        if(conjuntoIndex < imageBanco.size()){
            //VERIFICAR SE ALGUM OBJETO FILHO ESTA USANDO ESSE CONJUNTO
            imageBanco.remove(conjuntoIndex);
        }else{
            throw new IllegalArgumentException("ConjuntoImagenMe: conjunto inexistente");
        }       
    }//precisa de atualização
    
    protected int sizeConjunto(){
        return imageBanco.size();
    }
    
    public void addImage(int indexConjunto,String address){
        URL addressUrl = getClass().getResource(address);
        imageBanco.get(indexConjunto).addImagen(addressUrl);
    }

    public BufferedImage getImage(int indexConjunto,int imageIndex){
        if(imageBanco != null || indexConjunto < imageBanco.size()){
            if(imageIndex < imageBanco.get(indexConjunto).getArrayImageSize()){
                return imageBanco.get(indexConjunto).getImage(imageIndex);
            }
                return null;
        }
            return null;
    }

    public void removeImage(int indexConjunto,int imageIndex){
        if(indexConjunto < imageBanco.size()){
            if(imageIndex < imageBanco.get(indexConjunto).getArrayImageSize()){
                //VERIFICAR SE NENHUM OBJETO ESTÁ USANDO ESSA IMAGEM ANTES DE APAGAR
                imageBanco.get(indexConjunto).getImage(imageIndex);
            }else{
                throw new IllegalArgumentException("ConjuntoImagenMe: conjunto inexistente");
            }
        }else{
            throw new IllegalArgumentException("ConjuntoImagenMe: conjunto inexistente");
        }
    }
}
