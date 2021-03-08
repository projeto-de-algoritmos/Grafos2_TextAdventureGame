package game.models;

import game.models.item.Item;
import grafo.Aresta;
import grafo.Vertice;

import java.util.ArrayList;
import java.util.List;

public class Area extends Vertice {

    private String descricao;
    private List<Item> itens;
    private List<Inimigo> inimigos;

    public Area(String nome, String descricao){
        super(nome);
        this.descricao = descricao;
        this.itens = new ArrayList<>();
        this.inimigos = new ArrayList<>();
    }

    public Area(String nome, String descricao, List<Item> itens, List<Inimigo> inimigos){
        super(nome);
        this.descricao = descricao;
        this.itens = itens;
        this.inimigos = inimigos;
    }

    public void addItem(Item item){
        this.itens.add(item);
    }

    public void addInimigo(Inimigo inimigo){
        this.inimigos.add(inimigo);
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public List<Inimigo> getInimigos() {
        return inimigos;
    }

    public List<Item> getItens() {
        return itens;
    }

    public void mostrarDescricaoDetalhada(String textoEspecífico) {
        System.out.println(this.getDescricao());

        // ITENS

        if(getItens() != null && getItens().size() > 0){
            System.out.println("\nAo olhar mais atentamente, é possivel ver as seguintes coisas: ");

            for(Item item : getItens()){
                System.out.println(item.getDescricao());
            }
        } else {
            System.out.println("Não há nada demais para ver aqui.");
        }

        //CAMINHOS
        if(getAdjacencias() != null && getAdjacencias().size() > 0){
            System.out.println("\nE é possível identificar os seguintes caminhos para ir: ");

            for(Aresta salaAdjacente: getAdjacencias()){
                System.out.println(salaAdjacente.getDestino().getNome());
            }
        } else {
            System.out.println("Não há caminhos aparentes para sair daqui.");
        }

        System.out.println(textoEspecífico);
    }

    public void mostrarDescricaoDetalhada() {
        mostrarDescricaoDetalhada("");
    }

    @Override
    public String toString() {
        return "Area{" +
                "nome='" + getNome() + '\'' +
                ", descricao='" + descricao + '\'' +
                ", itens=" + itens +
                ", inimigos=" + inimigos +
                '}';
    }
}
