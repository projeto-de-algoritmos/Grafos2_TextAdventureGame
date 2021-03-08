package grafo.navegacao;

import game.models.Area;
import grafo.Aresta;
import grafo.Grafo;
import grafo.Vertice;
import grafo.navegacao.arvoreHeap.ArvoreHeap;
import grafo.navegacao.arvoreHeap.FolhaHeap;

import java.util.*;

public class Navegacao {
    private List<Vertice> fila = new ArrayList<>();

    public static LinkedList<Vertice> menorCaminho(Grafo grafo, Vertice inicio, Vertice fim) {
        Dijkstra dijkstra = new Dijkstra(grafo);
        dijkstra.executar(inicio);

        return dijkstra.menorCaminho(fim);
    }

    public void buscaEmLargura(Grafo grafo) {
        List<Vertice> vertices = grafo.getVertices();

        for (Vertice vertice : vertices) {
            if (!vertice.isVisitado()) {
                enfileirar(vertice);
                vertice.marcarComoVisitado();

                while (!fila.isEmpty()) {
                    Vertice verticeAtual = desenfileirar();

                    for (Aresta vizinho : verticeAtual.getAdjacencias()) {
                        Vertice destino = vizinho.getDestino();
                        if (!destino.isVisitado()) {
                            destino.marcarComoVisitado();

                            enfileirar(destino);
                        }
                    }
                }

            }
        }
    }

    public Vertice buscaVertice(Grafo grafo, String nome) {
        List<Vertice> vertices = grafo.getVertices();

        for (Vertice vertice : vertices) {
            if (!vertice.isVisitado()) {
                enfileirar(vertice);
                vertice.marcarComoVisitado();
                if (vertice.getNome().toLowerCase().contains(nome.toLowerCase())) {
                    grafo.resetVertices();
                    return vertice;
                }

                while (!fila.isEmpty()) {
                    Vertice verticeAtual = desenfileirar();

                    for (Aresta vizinho : verticeAtual.getAdjacencias()) {
                        Vertice destino = vizinho.getDestino();
                        if (!destino.isVisitado()) {
                            destino.marcarComoVisitado();

                            if (destino.getNome().toLowerCase().contains(nome.toLowerCase())) {
                                grafo.resetVertices();
                                return destino;
                            }

                            enfileirar(destino);
                        }
                    }
                }
                grafo.resetVertices();
            }
        }

        return null;
    }

    private void enfileirar(Vertice vertice) {
        fila.add(vertice);
    }

    private Vertice desenfileirar() {
        if (!fila.isEmpty()) {
            return fila.remove(0);
        } else {
            return null;
        }
    }

    public Vertice getSalaFinal(Grafo grafo, String salaInicial, String salaFinal) {
        Vertice verticeInicial = buscaVertice(grafo, salaInicial);

        enfileirar(verticeInicial);
        verticeInicial.marcarComoVisitado();

        while (!fila.isEmpty()) {
            Vertice verticeAtual = desenfileirar();

            for (Aresta vizinho : verticeAtual.getAdjacencias()) {
                Vertice destino = vizinho.getDestino();
                if (!destino.isVisitado()) {
                    if (destino.getNome().toLowerCase().contains(salaFinal.toLowerCase())) {
                        grafo.resetVertices();
                        return destino;
                    }

                    destino.marcarComoVisitado();

                    enfileirar(destino);
                }
            }

            grafo.resetVertices();
        }

        return null;
    }

    public Vertice retornarAreaConectada(Area areaAtual, String areaDesejada) {
        for(Aresta areaConectada : areaAtual.getAdjacencias()){
            if (areaConectada.getDestino().getNome().toLowerCase().contains(areaDesejada.toLowerCase())) {
                return areaConectada.getDestino();
            }
        }

        return null;
    }
}

class Dijkstra {
    private List<Vertice> vertices;
    private List<Aresta> arestas;
    private Map<Vertice, Integer> distancias;
    private Map<Vertice, Vertice> verticesAnteriores;
    private ArvoreHeap candidatos;

    public Dijkstra(Grafo grafo) {
        this.vertices = new ArrayList<>(grafo.getVertices());
        this.arestas = new ArrayList<>(grafo.getArestas());
    }

    public void executar(Vertice verticeInicial) {
        candidatos = new ArvoreHeap();
        distancias = new HashMap<>();
        verticesAnteriores = new HashMap<>();

        candidatos.inserir(new FolhaHeap(verticeInicial, verticeInicial, 0));
        distancias.put(verticeInicial, 0);

        while (candidatos.getTamanho() > 0) {
            Vertice vertice = candidatos.remover().getVerticeAtual();
            vertice.marcarComoVisitado();
            encontrarDistanciasMinimas(vertice);
        }
    }

    public LinkedList<Vertice> menorCaminho (Vertice fim) {
        LinkedList<Vertice> caminho = new LinkedList<>();
        Vertice passo = fim;

        if (verticesAnteriores.get(passo) != null) {
            caminho.add(passo);
            while (verticesAnteriores.get(passo) != null) {
                passo = verticesAnteriores.get(passo);
                caminho.add(passo);
            }

            Collections.reverse(caminho);
            return caminho;
        }

        return null;
    }

    private void encontrarDistanciasMinimas(Vertice vertice) {
        List<Aresta> vizinhos = vertice.getAdjacencias();
        for (Aresta vizinho : vizinhos) {
            int menorDistanciaVertice = pegaMenorDistancia(vertice);
            int menorDistanciaVizinho = pegaMenorDistancia(vizinho.getDestino());
            int distanciaVerticeParaVizinho = pegaDistancia(vertice, vizinho.getDestino());

            if (menorDistanciaVizinho > (menorDistanciaVertice + distanciaVerticeParaVizinho)) {
                int custo = menorDistanciaVertice + distanciaVerticeParaVizinho;
                distancias.put(vizinho.getDestino(), custo);

                verticesAnteriores.put(vizinho.getDestino(), vertice);
                candidatos.inserir(new FolhaHeap(vizinho.getDestino(), vertice, custo));
            }
        }
    }

    private int pegaDistancia(Vertice vertice, Vertice destino) {
        for (Aresta aresta : arestas) {
            if (aresta.getOrigem().equals(vertice) && aresta.getDestino().equals(destino)) {
                return aresta.getPeso();
            }
        }

        throw new RuntimeException("Estranho isso aí, não é pode acontecer");
    }

    private int pegaMenorDistancia(Vertice destino) {
        Integer distancia = distancias.get(destino);
        if (distancia == null) {
            return Integer.MAX_VALUE;
        } else {
            return distancia;
        }
    }
}