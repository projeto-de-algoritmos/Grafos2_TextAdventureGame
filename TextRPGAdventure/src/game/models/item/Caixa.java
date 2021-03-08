package game.models.item;

public class Caixa extends ItemDesbloqueavel{

    public Caixa(String nome, String descricao, Boolean rastreavel, String mensagemItemBloqueado) {
        super(nome, descricao, rastreavel, mensagemItemBloqueado);
    }

    @Override
    public void usarDesbloqueado() {
        System.out.println("Parece que já retiraram o que estava aqui.");
    }
}
