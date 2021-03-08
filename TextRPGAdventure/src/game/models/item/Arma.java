package game.models.item;

public abstract class Arma extends Item{

    private Integer dano;
    private Integer durabilidade;

    public Arma(String nome, String descricao, Boolean rastreavel, Integer dano, Integer durabilidade){
        super(nome, descricao, rastreavel);
        this.dano = dano;
        this.durabilidade = durabilidade;
    }

    public Integer getDano() {
        return dano;
    }

    public void setDano(Integer dano) {
        this.dano = dano;
    }

    public Integer getDurabilidade() {
        return durabilidade;
    }

    public abstract void atacar();

    @Override
    public void usar() {
        if(durabilidade > 0){
            atacar();
            durabilidade = durabilidade--;
        }

    }

    @Override
    public String toString() {
        return "Item{" +
                "nome='" + getNome() + '\'' +
                ", descricao='" + getDescricao() + '\'' +
                ", dano='" + dano + '\'' +
                ", durabilidade='" + durabilidade + '\'' +
                '}';
    }
}
