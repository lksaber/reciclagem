/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.Classes.ObjetosAuxiliares;

import java.util.ArrayList;

/**
 *
 * @author lucas
 */

//GUARDA OS PRINCIPAIS CALCULOS USADOS PARA TAMANHO DAS COISAS
public class gameCalculos {
    public static int zoomCalculo(double zoom,int tamanho){
        if(tamanho > 0 && zoom >= -100){
            double alpha = (double) (zoom * 0.01) + 1;
            int  intRetorno = (int) (alpha * tamanho);
            return intRetorno;
        }
        return 0;
    }
}
