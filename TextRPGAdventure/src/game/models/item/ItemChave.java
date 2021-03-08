package game.models.item;

import game.models.JogoController;

public class ItemChave extends Item{

    private ItemDesbloqueavel itemDesbloqueavel;
    private String mensagemDesbloqueiaItem;

    public ItemChave(String nome, String descricao, Boolean rastreavel, ItemDesbloqueavel itemDesbloqueavel, String mensagemDesbloqueiaItem) {
        super(nome, descricao, rastreavel);
        this.itemDesbloqueavel = itemDesbloqueavel;
        this.mensagemDesbloqueiaItem = mensagemDesbloqueiaItem;
    }

    public ItemDesbloqueavel getItemDesbloqueavel() {
        return itemDesbloqueavel;
    }

    @Override
    public void usar() {
        if(JogoController.getJogo().getAreaAtualJogador().getItens().contains(itemDesbloqueavel)){
            System.out.println(mensagemDesbloqueiaItem);
            System.out.println("O item " + super.getNome() + " foi removido do inventário !");
            itemDesbloqueavel.setDesbloqueado(true);
        }
    }

}
