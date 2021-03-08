package game.models.item;

public abstract class Item {

    private String nome;
    private String descricao;
    private Boolean rastreavel;

    public Item(String nome, String descricao, Boolean rastreavel){
        this.nome = nome;
        this.descricao = descricao;
        this.rastreavel = rastreavel;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getNome() {
        return nome;
    }

    public Boolean getRastreavel() {
        return rastreavel;
    }

    public abstract void usar();

}
