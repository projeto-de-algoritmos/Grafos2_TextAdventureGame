package game.models;

import grafo.navegacao.Navegacao;

public class Chefe extends Inimigo{

    private Area areaAtual;

    public Chefe(String nome, Integer vida, Area areaInicial) {
        super(nome, vida);
        this.areaAtual = areaInicial;
    }

    public Area getAreaAtual() {
        return areaAtual;
    }

    public void agir(){

        if(areaAtual.getNome().equals(JogoController.getJogo().getAreaAtualJogador().getNome())) {
            System.out.println("O Chucky te estrangulou até a morte ! Você morreu !");
            JogoController.getJogo().finalizarJogo();
        } else {
            andar();
        }

    }

    private void andar(){
        Area areaAlvo = JogoController.getJogo().perseguirJogador();
        Integer distanciaEntreSalas = JogoController.getJogo().identificaDistancia(areaAtual, areaAlvo);

        JogoController.getJogo().incrementarTurnosChefe(distanciaEntreSalas);
        areaAtual = areaAlvo;
    }

}
