package game.models.item;

import game.models.JogoController;

public class PocaoCura extends ItemConsumivel{

    private final Integer quantidadeCurada;

    public PocaoCura(String nome, String descricao, Integer cargas, Boolean rastreavel, Integer quantidadeCurada){
        super(nome, descricao, rastreavel, cargas);
        this.quantidadeCurada = quantidadeCurada;
    }

    @Override
    public void consumir() {
        System.out.println("Você se sente rejuvenescido ao tomar um gole !");
        JogoController.getJogo().adicionarVida(quantidadeCurada);
    }

}
