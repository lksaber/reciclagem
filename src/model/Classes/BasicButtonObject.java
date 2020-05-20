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

/*
Classe para criação do botão (é bem aberta se usada da maneira correta pode
cria botões de 3 estados)
*/

public abstract class BasicButtonObject extends MetodosImpostoObj{
    private int estado = 0; /*são os estados aos quais um botão pode estar dos quais são
    0 = deselecionado; 1 = selecionado; 2 = pressionado;*/
    private boolean ativado = true;/*indica se o botão esta ativado para ser pressionado*/
    /* usado na composição de botoes do estilo 3 estados podendo
    assim criar botões que indicarão que uma opção está ativa
    */
    
    public int getEstado() {
        return estado;
    }
    public void estadoDeselect(){
        estado = 0;
        mudanca();
    }
    public void estadoSelect(){
        estado = 1;
        mudanca();
    }
    public void estadoPress(){
        if(estado == 1){
            estado = 2;
            mudanca();
        }
        mudanca();
    }

    public boolean isAtivado() {
        return ativado;
    }
    
    public void buttonAtivar() {
        ativado = true;
    }
    
    public void buttonDesativar() {
        ativado = false;
    }
    
    public abstract void mudanca();//indica que ouve uma mudança de estado para as classes filhas
    
}
