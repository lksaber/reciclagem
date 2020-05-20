/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.Classes;

import java.util.ArrayList;

/**
 *
 * @author lucas
 */
public abstract class MetodosArrayObject {
    public static ArrayList addObjeto(Object objeto,ArrayList array){
        return addObjeto(-1,objeto,array);
    }
    
    public static ArrayList addObjeto(int index,Object objeto,ArrayList array){
        if(objeto != null){
            if(array == null){
                array = new ArrayList();
            }
            if(index == -1){
                array.add(objeto);
                return array;
            }else if(index > -1 && index < array.size() -1 ){
                array.add(index,objeto);
                return array;
            } 
        }
        return null;
    }
    
    public static boolean removeObjeto(int index,ArrayList array){
        if(array != null){
            if(index < array.size() && index > -1){
                array.remove(index);
                return  true;
            }
        }
        return false;

    }
    
    public static void RecolacaPosArray(int indexAtual, int indexNovo,ArrayList array){
        if(array != null){
            if(indexAtual < array.size() && indexNovo < array.size()){
                Object obj = array.get(indexAtual);
                array.remove(indexAtual);
                array.set(indexNovo, obj);
            }else{
                throw new IllegalArgumentException("RecolacaPosArray: index maior que o array");
            }
        }else{
            throw new IllegalArgumentException("RecolacaPosArray: array nula");   
        }
    }
    
    public static void TrocaPosArray(int indexObj1, int indexObj2,ArrayList array){
        if(array != null){
            if(indexObj1 < array.size() && indexObj2 < array.size()){
                Object obj1 = array.get(indexObj1);
                Object obj2 = array.get(indexObj2);
                
                array.remove(indexObj1);
                array.set(indexObj1, obj2);
                
                array.remove(indexObj2);
                array.set(indexObj2, obj1);
            }else{
                throw new IllegalArgumentException("TrocaPosArray: index maior que o array");
            }
        }else{
            throw new IllegalArgumentException("TrocaPosArray: array nula");   
        }
    }
}
