package game.models.item;

import game.models.Area;
import game.models.JogoController;
import grafo.Aresta;
import grafo.Grafo;
import grafo.Vertice;

import java.util.ArrayList;
import java.util.List;

public class Rastreador extends Item{

    public Rastreador(String nome, String descricao, Boolean rastreavel) {
        super(nome, descricao, rastreavel);
    }

    @Override
    public void usar() {

        System.out.println(" É possível identificar que talvez hajam itens do seu interesse nos seguintes locais: ");
        buscaItemRastreavel(JogoController.getJogo().getGrafo());

    }

    //Mover essa lógica pra Jogo Controller
    private void buscaItemRastreavel(Grafo grafo) {
        List<Vertice> vertices = grafo.getVertices();
        List<Vertice> fila = new ArrayList<>();

        for (Vertice vertice : vertices) {
            if (!vertice.isVisitado()) {
                fila.add(vertice);
                vertice.marcarComoVisitado();

                if(verificaItemRastreavel(vertice)){
                    System.out.println(vertice.getNome());
                }

                while (!fila.isEmpty()) {
                    Vertice verticeAtual = fila.remove(0);

                    for (Aresta vizinho : verticeAtual.getAdjacencias()) {
                        if(vizinho.getDestino() instanceof Area) {
                            Vertice destino = vizinho.getDestino();
                            if (!destino.isVisitado()) {
                                destino.marcarComoVisitado();

                                if(verificaItemRastreavel(destino)){
                                    System.out.println(destino.getNome());
                                }

                                fila.add(destino);
                            }
                        }

                    }
                }

            }

        }

        grafo.resetVertices();

    }

    private boolean verificaItemRastreavel(Vertice vertice){

        if(vertice instanceof Area) {
            Area area = (Area) vertice;

            for (Item item : area.getItens()) {
                if (item.getRastreavel()) {
                    return true;
                }
            }
        }

        return false;
    }


}
