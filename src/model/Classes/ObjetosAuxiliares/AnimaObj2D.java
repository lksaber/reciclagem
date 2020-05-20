/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.Classes.ObjetosAuxiliares;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import model.Classes.Armazenamento.ConjuntoImagenMe;
import model.Classes.Armazenamento.ConjuntoSonsMe;
import model.Classes.Armazenamento.ImageReference;
import model.Classes.Armazenamento.SongReference;
import model.Classes.MetodosImpostoObj;
/**
 *
 * @author lucas
 */
/*
    cria uma animação baseada em um bloco do banco de imagens e sons
*/

public class AnimaObj2D extends MetodosImpostoObj{
    //classes internas
    private Tranformacao tranformacao;
    private ClQuadroAnima clQuadroAnima;
    private AcoesAnima acoesAnima;
    //parametros para imagens
    private int posx;//posição x do objeto no plano
    private int posy;//posição x do objeto no plano
    private int zoom;// aproximação e distancia da imagem
    private int distorceX;//ditorce a imagem adicionando um valor ao seu tamanho
    private int distorceY;//ditorce a imagem adicionando um valor ao seu tamanho
   
    //array para audios e imagens
    private ArrayList<QuadroAnima> quadroAnima;
    
    private boolean loopRepeticao;//liga o loop infinito da animação
    private boolean start;//da um start na animação
    private boolean visible;//permite a imagem ser visivel sem estar no start
    private int quadroAtual;//qual quadro está para ser exevuta
    private boolean invertAnimationX; // inverte a animação em no eixo x
    private boolean invertAnimationY; // inverte a animação em no eixo y
    private boolean rebobinar; // faz a animação rodar ao contrario
    
   /* public AnimaObject(){
        resetAnimaParam();//da as variaves zeradas
    }*/
    
    private int timeUpdate;//recebe um calor que auxilia no update da animação sendo podendo assim retardar o
    //processo de update criando um sistema de controle interno da propria animaçao
    private int timeUpdateControl;//auxilia no time update sendo um contador para a atualização
    
    public AnimaObj2D(
        int posx,int posy,int imgConjunto,int imgIndexImage,boolean loopRepeticao
    ){
        AnimaObjectCreate(posx,posy,imgConjunto,imgIndexImage,-1,0,loopRepeticao,0);
    }
    
    public AnimaObj2D(
        int posx,int posy,int imgConjunto,int imgIndexImage,boolean loopRepeticao,int zoom
    ){
        AnimaObjectCreate(posx,posy,imgConjunto,imgIndexImage,-1,0,loopRepeticao,zoom);
    }
    
    public AnimaObj2D(
        int posx,int posy,int imgConjunto,int imgIndexInicial,int imgIndexFinal,int timeUpdate,
        boolean loopRepeticao
    ){
        AnimaObjectCreate(posx,posy,imgConjunto,imgIndexInicial,imgIndexFinal,timeUpdate,loopRepeticao,0);
    }
    
    public AnimaObj2D(
        int posx,int posy,int imgConjunto,int imgIndexInicial,int imgIndexFinal,int timeUpdate,
        boolean loopRepeticao,int zoom
    ){
        AnimaObjectCreate(posx,posy,imgConjunto,imgIndexInicial,imgIndexFinal,timeUpdate,loopRepeticao,zoom);
    }
    
    
    private void AnimaObjectCreate(int posx,int posy,
        int imgConjunto,int imgIndexInicial,int imgIndexFinal,int timeUpdate,
        boolean loopRepeticao,int zoom){
        
        if(imgConjunto >= 0 && imgIndexInicial >= 0){
            this.posx = posx;
            this.posy = posy;
            this.zoom = zoom;
            this.distorceX = 0;
            this.distorceY = 0;
            this.loopRepeticao = loopRepeticao;
            this.timeUpdate = timeUpdate;
            this.start = true;
            this.visible = true;
            acoesAnima = new AcoesAnima();
            tranformacao = new Tranformacao();
            clQuadroAnima = new ClQuadroAnima();
            quadroAnima = new ArrayList();
            if(imgIndexFinal == -1){//usase menos um para identificar que é apenas uma imagem a ser adicionada
                quadroAnima.add(new QuadroAnima(imgConjunto,imgIndexInicial));
            }else if(imgIndexFinal >= imgIndexInicial){
                for(int i = imgIndexInicial; i < imgIndexFinal+1;i++){
                    quadroAnima.add(new QuadroAnima(imgConjunto,i));
                }
            }
            else{
                throw new IllegalArgumentException("Anima: Erro no contrutor da animação");
            }
        }else{
                throw new IllegalArgumentException("Anima: Conjunto não pode ser vazio");
         }
    }
 
    ////////////////////////////////////////////////////////////////////////////
    ////////////////////ACESSO AS CLASSES INTERNAS//////////////////////////////
    ////////////////////////////////////////////////////////////////////////////
    
    public AcoesAnima AcoesAni(){//PERMITE AO USUARIO ACESSO A CLASSE
        return acoesAnima;
    }
    
    
    public ClQuadroAnima QuadroAni(){//PERMITE AO USUARIO ACESSO A CLASSE
        return clQuadroAnima;
    }
    
    public Tranformacao Tranform(){//PERMITE AO USUARIO ACESSO A CLASSE
        return tranformacao;
    }

    
    //RETORNA O TAMANHO DA PRIMEIRA IMAGEM DA ANIMAÇÃO (USADO NO BACKGROUND PARA MEDIR REPETIÇÃO)
    public Point getTamPrimeiraImagen(ConjuntoImagenMe imgBanco){
        if(quadroAnima != null && quadroAnima.isEmpty() == false){
            BufferedImage image;
            Point point = new Point();
            ImageReference imgRef = quadroAnima.get(0).getImgRef();
            if(imgBanco.getImage(imgRef.getIndexConjunto(), imgRef.getIndexElemento()) != null){
                image = imgBanco.getImage(imgRef.getIndexConjunto(), imgRef.getIndexElemento());
                
                point.x = gameCalculos.zoomCalculo(zoom,image.getWidth());//calculando tamanho com zoom
                point.y = gameCalculos.zoomCalculo(zoom,image.getHeight());//calculando tamanho com zoom
                
                return point;
            }
            return null;
        }
        return null;
    }
    
    
    //////////////////////////////////////////////////////////
    /////METODOS OBRIGATORIOS DA CLASSE MetodosImpostoObj/////
    //////////////////////////////////////////////////////////
    //ATUALIZAÇÃO DOS QUADROS E EXECUÇÃO DOS SONS
    @Override
    public void gameUpdate(ConjuntoSonsMe sgBanco,Rectangle rectCamera){
        int songConjunto = 0;
        int songIndex = 0;
        //ATUALIZA A ANIMAÇÃO SENDO QUE GAMEUPDATE DEVE ESTAR NO LOOP GAME UPDATE
        if(timeUpdateControl >= timeUpdate){//controle interno de tempo da animação
            timeUpdateControl = 0;
            if(start == true){
                if(rectCamera.width > posx){
                    if(rebobinar == false){//INDICA QUE REBOBINAR NÃO ESTA ATIVO 
                        quadroAtual++;
                
                        if(quadroAtual > quadroAnima.size()-1){
                            if(loopRepeticao == true){ // caso o quadro atual a ser exibido for maior que o quadro final
                                quadroAtual = 0;
                            }
                            else{
                                acoesAnima.pause();
                                quadroAtual = 0;
                            }
                        }
                    }
                    else{//INDICA QUE REBOBINAR ESTA ATIVO 
                        quadroAtual--;
                
                        if(quadroAtual < 0){
                            if(loopRepeticao == true){ // caso o quadro atual a ser exibido for maior que o quadro final
                                quadroAtual = quadroAnima.size()-1;
                            }
                            else{
                                acoesAnima.pause();
                                quadroAtual = quadroAnima.size()-1;
                            }
                        }
                    }
        
                    //EXECUTA OS SONS DO QUADRO
                    if(quadroAnima.get(quadroAtual).songRefSize() > 0){
                        for(int i = 0; i < quadroAnima.get(quadroAtual).songRefSize(); i++){
                            songConjunto = quadroAnima.get(quadroAtual).getSongRef(i).getIndexConjunto();
                            songIndex = quadroAnima.get(quadroAtual).getSongRef(i).getIndexElemento();
                        
                            if(sgBanco.getSong(songConjunto, songIndex) != null){
                                sgBanco.getSong(songConjunto, songIndex).play();
                            }
                        }
                    }
                }//se a start estiver ativo
            }
        }
        timeUpdateControl++;
    }
    
    //EXIBIÇÃO DAS IMAGENS
    @Override
    public void render(Graphics2D grafic,ConjuntoImagenMe imgBanco,Rectangle rectCamera){
        //MOSTRA AS IMAGEN
        if(visible == true){//verifcando se é star ou stopMasVisivel
            BufferedImage image;
            int tamanhox = 0;//quando for imiprimir a imagem faz uma analize com zoom
            int tamanhoy = 0;//quando for imiprimir a imagem faz uma analize com zoom
            int imgConjunto = quadroAnima.get(quadroAtual).getImgRef().getIndexConjunto();
            int imgIndex = quadroAnima.get(quadroAtual).getImgRef().getIndexElemento();
            
            if(imgBanco.getImage(imgConjunto, imgIndex) != null){//verificando se a imagem existe
               // if(rectCamera.width > posx){//verificando se o objeto está na area da camera ou proximo
                    
                    image = imgBanco.getImage(imgConjunto, imgIndex);
                    
                    tamanhox = gameCalculos.zoomCalculo(zoom,image.getWidth());//calculando tamanho com zoom
                    tamanhoy = gameCalculos.zoomCalculo(zoom,image.getHeight());//calculando tamanho com zoom
                    
                    if(invertAnimationX == true){
                        posx += tamanhox;
                        tamanhox = 0-tamanhox;
                    }
                
                    if(invertAnimationY == true){
                        posy += tamanhoy;
                        tamanhoy = 0-tamanhoy;
                    }
                    grafic.drawImage(image, posx, posy,tamanhox + distorceX,tamanhoy + distorceY, null);
                    
               // }
            }
        }
    }
    
    public void destroy(){
        tranformacao = null;
        clQuadroAnima = null;
        acoesAnima = null;
        quadroAnima = null;
    }
    ////////////////////////////////////////////////////////////////////////////
    //////////////////////////CLASSE QUE ACÃOES/////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////
    public class AcoesAnima{
    
        public boolean isStart() {
            return start;
        }
        
        public boolean isPause() {
            return visible == true && start == false;
        }
    
        public void start() {
            visible = true;
            start = true;
        }
        public void pause(){
            visible = true;
            start = false;
        }
        public void stop(){
            visible = false;
            start = false;
        }
    
        public void retonarOriginal(){
            invertAnimationX = false;
            invertAnimationY = false;
            rebobinar = false;
            zoom = 0;
            distorceX = 0;
            distorceY = 0;
            posx = 0;
            posy = 0;
        }
    }
    
    ////////////////////////////////////////////////////////////////////////////
    ///////////////////CLASSE QUE CRIA UM QUADRO DA ANIMAÇÃO////////////////////
    ////////////////////////////////////////////////////////////////////////////
    public class ClQuadroAnima{
        /////////////////////////////////////////
        ////////CLASSES PARA O USUARIO///////////
        /////////////////////////////////////////
        //ADICIONANDO IMAGENS
        public void addQuadro(int imgConjunto,int imgIndex){
            if(imgConjunto >= 0 && imgIndex >= 0){
                quadroAnima.add(new QuadroAnima(imgConjunto,imgIndex));
            }
        }
        public void addQuadro(int indexQuadro,int imgConjunto,int imgIndex){
            if(indexQuadro < quadroAnima.size() && imgConjunto >= 0 && imgIndex >= 0){
                quadroAnima.add(indexQuadro, new QuadroAnima(imgConjunto,imgIndex));
            }
        }
        public void removeQuadro(int indexQuadro){
            if(indexQuadro < quadroAnima.size()){
                quadroAnima.remove(indexQuadro);
            }
        }
        
        //SONS ADICIONADO E RETIRANDO REFERENCIAS DELES
        public void addEffectAudioRef(int indexQuadro,int songConjunto,int indexSong){
            //é dado as referencias do banco e em qual quadro deve ser ativada
            if(indexQuadro < quadroAnima.size() && songConjunto >= 0 && indexSong >= 0){
                quadroAnima.get(indexQuadro).AddSongRef(songConjunto, indexSong);
            }
        }
        public void removeEffectAudioRef(int indexQuadro, int indexSong){
            if(indexQuadro < quadroAnima.size()){
                if(indexSong < quadroAnima.get(indexQuadro).songRefSize()){
                    quadroAnima.get(indexQuadro).removeSongRef(indexSong);
                }
            }
        }
        public void removeAllEffectAudioRef(int indexQuadro){
            if(indexQuadro < quadroAnima.size()){
                quadroAnima.get(indexQuadro).removeAllSongRef();
            }
        }
    }
    
    
    private class QuadroAnima{
        private ImageReference imgRef;
        private ArrayList<SongReference> songRef;
        /////////////////////////////////////////
        ////////CLASSES PARA O PROGAMA///////////
        /////////////////////////////////////////
        private QuadroAnima(int imgConjunto,int imgIndex){
            if(imgConjunto >= 0 || imgIndex >= 0){
                imgRef = new ImageReference(imgConjunto,imgIndex);
                songRef = null;
            }else{
                throw new IllegalArgumentException("Não pode mandar conjunto ou index negativos");
            }
            }
        private void AddSongRef(int songConjunto, int songIndex){
            if(songConjunto >= 0 || songIndex >= 0){
                if(songRef == null){
                    songRef = new ArrayList();
                }
                songRef.add(new SongReference(songConjunto,songIndex));
            }
        }
    
        private SongReference getSongRef(int indexSong){
            if(indexSong >=0 && indexSong < songRef.size()){
                return songRef.get(indexSong);
            }
            return null;
        }
    
        private int songRefSize(){
            if(songRef == null){
                return 0;
            }
            return songRef.size();
        }
    
        private void removeSongRef(int indexSong){
            if(indexSong >=0 && indexSong < songRef.size()){
                songRef.remove(indexSong);
                if(songRef.isEmpty()){
                    songRef = null;
                }
            }
        }
    
        private void removeAllSongRef(){
            songRef.clear();
            songRef = null;
        }
    
        private ImageReference getImgRef(){
            return imgRef;
        }
        private void setImgRef(int imgConjunto,int imgIndex){
            if(imgConjunto >= 0 && imgIndex >= 0){
                imgRef.setIndexConjunto(imgConjunto);
                imgRef.getIndexElemento(imgIndex);
            }
       }
    }
    
    ////////////////////////////////////////////////////////////////////////////
    ////////////////CLASSE QUE DISTORCE A IMAGEM A SER IMPRESSA/////////////////
    ////////////////////////////////////////////////////////////////////////////
    public class Tranformacao{
        //LOCALIZADORES E ZOOM
        public int getPosx() {
            return posx;
        }
        public void setPosx(int trPosx) {
            posx = trPosx;
        }

        public int getPosy() {
            return posy;
        }
        public void setPosy(int trPosy) {
            posy = trPosy;
        }

        public int getZoom() {
            return zoom;
        }
        public void setZoom(int trZoom) {
            if(zoom < -100){
                zoom = 0;
            }
            else{
                zoom = trZoom;
            }
        }

        public int getDistorceX() {
            return distorceX;
        }

        public void setDistorceX(int trDistorceX) {
            distorceX = trDistorceX;
        }

        public int getDistorceY() {
            return distorceY;
        }

        public void setDistorceY(int trDistorceY) {
            distorceY = trDistorceY;
        }
        
        //INVERTENDO IMAGENS
        public boolean isInvertAnimationX() {    
            return invertAnimationX;
        }
        public void setInvertAnimationX(boolean trInvertAnimationX) {
            invertAnimationX = trInvertAnimationX;
        }
        public boolean isInvertAnimationY() {
            return invertAnimationY;
        }
        public void setInvertAnimationY(boolean trInvertAnimationY) {
            invertAnimationY = trInvertAnimationY;
        }
    
        //FRAZENDO A ANIMAÇÃO ANDAR AO CONTRARIO
        public boolean isRebobinar() {
            return rebobinar;
        }
        public void setRebobinar(boolean trRebobinar) {
            rebobinar = trRebobinar;
        }
    
        //TIME INTENO PARA UPDATE
        public int getTimeUpdate() {
            return timeUpdate;
        }
        public void setTimeUpdate(int trTimeUpdate) {
            if(trTimeUpdate < 0){
                timeUpdate = trTimeUpdate;
            }
        }
    
    
        //AUTORIZACOES DA ANIMAÇÃO (LOOP E START)
        public boolean isLoopRepeticao() {
            return loopRepeticao;
        }
        public void setLoopRepeticao(boolean trloopRepeticao) {
            loopRepeticao = trloopRepeticao;
        }
    }
}


