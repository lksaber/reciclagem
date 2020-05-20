/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.Classes;

/**
 *
 * @author lucas
 */

/*CRIAÇÃO DAS BARRAS DE SANGUE E OUTRAS MEDIDAS QUE SERÃO USADAS NO PROJETO
AUXILIANDO NA CRIAÇÃO E COMPOSIÇÃO DESSAS BARRAS
*/
public abstract class BasicBarsObject extends BasicParamObject{
    private int tamanhoAtual;
    private int tamanhoMax;

    public BasicBarsObject(){
        tamanhoAtual = 0;
        tamanhoMax = 0;
    }

    public int getTamanhoAtual() {
        return tamanhoAtual;
    }
    public void setTamanhoAtual(int tamanhoAtual) {
        //o tamanhoAtual nunca pode ser maior que o tamnho maximo
        if(tamanhoAtual < tamanhoMax){
            this.tamanhoAtual = tamanhoAtual;
        }
    }

    public int getTamanhoMax() {
        return tamanhoMax;
    }
    public void setTamanhoMax(int tamanhoMax) {
        //o tamanhoMaximo nunca pode ser menor que o tamnhoAtual
        if(tamanhoMax > tamanhoAtual){
            this.tamanhoMax = tamanhoMax;
        }
    }
}
