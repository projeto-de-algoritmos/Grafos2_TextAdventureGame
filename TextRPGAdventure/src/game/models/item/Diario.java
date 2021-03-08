package game.models.item;

public class Diario extends Item{

    public Diario(String nome, String descricao){
        super(nome, descricao, false);
    }

    @Override
    public void usar() {
        System.out.println("Ao abrir o diário, você consegue ler entre as folhas sujas e amassadas, a seguinte passagem: ");
        System.out.println("'Estou aqui a mais ou menos 128 dias. Não sei mais se consigo escapar. O carrasco deixa água e restos de comida, e sai. Eu nem ao menos tenho força para tentar enfrentá-lo.'");
    }
}
