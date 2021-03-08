package game.models;

import game.models.item.Item;

import java.util.ArrayList;
import java.util.List;

public class Personagem {

    private String nome;
    private Integer vida;
    private List<Item> itens;

    public Personagem(String nome, Integer vida){
        this.nome = nome;
        this.vida = vida;
        itens = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public Integer getVida() {
        return vida;
    }

    public void setVida(Integer vida) {
        this.vida = vida;
    }

    public List<Item> getItens() {
        return itens;
    }

    public void addItem(Item e){
        this.itens.add(e);
    }

    @Override
    public String toString() {
        return "Personagem{" +
                "nome='" + nome + '\'' +
                ", vida=" + vida +
                ", itens=" + itens +
                '}';
    }
}
