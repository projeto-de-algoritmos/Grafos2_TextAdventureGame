package game.models.item;

public class ItemDecorativo extends Item{

    public ItemDecorativo(String nome, String descricao, Boolean rastreavel){
        super(nome, descricao, rastreavel);
    }

    @Override
    public void usar() {
        System.out.println(getDescricao());
    }
}
