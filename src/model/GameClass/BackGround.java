/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.GameClass;

import controller.GameController;
import model.Classes.BasicFundoObj2D;
import model.Classes.ObjetosAuxiliares.AnimaObj2D;


/**
 *
 * @author lucas
 */
public final class BackGround extends BasicFundoObj2D {
    int imageConjunto = -1;
    int songConjunto = -1;

    public BackGround(GameController gameCtr){
        if(gameCtr != null){
            loader(gameCtr);
            create();
        }
        else{
            throw new IllegalArgumentException("BackGround: gameCtr não pode ser nulo");
        }
    }
    
    @Override
    public void loader(GameController gameCtr) {
        if(gameCtr.getimgBanco().procurarConjunto("MenuInicial") == -1){
            //CARREGA OS OBJETOS QUE COPONHEM O BACKGROUND//
            imageConjunto = gameCtr.getimgBanco().addNovoConjunto("MenuInicial");
            gameCtr.getimgBanco().addImage(imageConjunto,"/model/Arquivos/imagens/BackGround.png");
        }else{
             imageConjunto = gameCtr.getimgBanco().procurarConjunto("MenuInicial");
        }
    }

    @Override
    public void create() {
        addNewBackGround(new AnimaObj2D(0,0,imageConjunto,0,false));
    }

    @Override
    public void destroy() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
