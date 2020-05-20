/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.Classes.Armazenamento;

/**
 *
 * @author lucas
 */
/*
    classe do tipo referencia premite guardar o local onde o objeto esta
*/

public final class ImageReference {
    private int indexConjunto; // INDEX DO CONJUNTO NA BASIC PARAM
    private int indexElemento;// INDEX DO ELEMENTO QUE SERÁ ACESSDO PELA VIEW
    
    public ImageReference(int indexConjunto,int indexElemento){
        
        if(referenceExistente(indexConjunto,indexElemento) == false){
                throw new IllegalArgumentException("Não existe essa imagem");
        }
        
        this.indexConjunto = indexConjunto;
        this.indexElemento = indexElemento;
    }

    public boolean referenceExistente(int indexConjunto,int indexElemento){
        /*if(ConjuntoSons.getSong(indexConjunto, indexElemento) == null){
            return false;
        }*/
        return true;
    }
    
    public int getIndexConjunto() {
        return indexConjunto;
    }
    public void setIndexConjunto(int indexConjunto) {
        if(referenceExistente(indexConjunto,this.indexElemento) == true){
            this.indexConjunto = indexConjunto;
        }else{
            throw new IllegalArgumentException("ImageReference: Erro ao enviar o novo conjunto");
        }
    }

    public int getIndexElemento() {
        return indexElemento;
    }
    public void setIndexElemento(int indexElemento) {
        if(referenceExistente(this.indexConjunto,indexElemento) == true){
            this.indexElemento = indexElemento;
        }else{
            throw new IllegalArgumentException("ImageReference: Erro ao enviar o novo elemento");
        }
    }
   
    @Override
    public SongReference clone() throws CloneNotSupportedException{
        return (SongReference) super.clone();
    }

    public void getIndexElemento(int imgIndex) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
