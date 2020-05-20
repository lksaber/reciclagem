/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.Classes.Armazenamento;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import javax.imageio.ImageIO;

/**
 *
 * @author lucas
 */

/*
    Imagens que estão alocadas na memoria
*/
public class ImagenBancoMe{
    private String nome;
    private ArrayList<BufferedImage> image;
    
    public ImagenBancoMe(String nome){
        this.nome = nome;
        image = new ArrayList();
    }

    public String getNome(){
        return nome;
    }
    
    public void addImagen(URL endereco){
        try{
            //CARREGANDO IMAGENS NA MEMORIA
            image.add(ImageIO.read(endereco));
        }catch(IOException ex){
            //CASO NÃO COSIGA CARREGAR
            throw new IllegalArgumentException("ImagenBancoMe: load imagens erro de arquivo");  
        }
    }
    
    public BufferedImage getImage(int imageIndex){
        if(image != null && image.isEmpty() == false){
            if(imageIndex < image.size()){
                return image.get(imageIndex);
            }else{
            throw new IllegalArgumentException("ImageBancoMe.getImage("+imageIndex+"); imagem inexistente");
            }
        }else{
            throw new IllegalArgumentException("ImageBancoMe.getImage("+imageIndex+"); imagem inexistente");
        }
    }
    
    public void removeImage(int imageIndex){
        if(imageIndex < image.size()){
            image.get(imageIndex).flush();
            image.remove(imageIndex);
        }
    }
    
    public int getArrayImageSize(){
        if(image == null || image.isEmpty()){
            return -1;
        }
        return image.size();
    }
}
