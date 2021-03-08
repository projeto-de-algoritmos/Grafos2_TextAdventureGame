package game.models.item;

public abstract class ItemConsumivel extends Item{

    private Integer cargas;
    private String mensagemConsumir;

    public ItemConsumivel(String nome, String descricao, Boolean rastreavel, Integer cargas){
        super(nome, descricao, rastreavel);
        this.cargas = cargas;
    }

    public Integer getCargas() {
        return cargas;
    }

    public void setCargas(Integer cargas) {
        this.cargas = cargas;
    }

    public abstract void consumir();

    @Override
    public void usar() {
        if(cargas > 0){
            consumir();
            cargas = cargas--;
        }

    }

    @Override
    public String toString() {
        return "Item{" +
                "nome='" + getNome() + '\'' +
                ", descricao='" + getDescricao() + '\'' +
                ", cargas='" + cargas + '\'' +
                '}';
    }


}
