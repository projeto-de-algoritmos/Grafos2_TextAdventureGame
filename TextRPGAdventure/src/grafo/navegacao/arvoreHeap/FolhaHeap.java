package grafo.navegacao.arvoreHeap;

import grafo.Vertice;

public class FolhaHeap {
    private Vertice verticeAtual;
    private Vertice verticeOrigem;
    private int custo;

    public FolhaHeap(Vertice verticeAtual, Vertice verticeOrigem, int custo) {
        this.verticeAtual = verticeAtual;
        this.verticeOrigem = verticeOrigem;
        this.custo = custo;
    }

    public Vertice getVerticeAtual() {
        return verticeAtual;
    }

    public Vertice getVerticeOrigem() {
        return verticeOrigem;
    }

    public int getCusto() {
        return custo;
    }
}
