package grafo;

import java.util.ArrayList;
import java.util.List;

public abstract class Vertice {
	
	private String nome;
	private List<Aresta> adjacencias;
	private boolean visitado;
	
	public Vertice(String nome){
		this.nome = nome;
		adjacencias = new ArrayList<>();
		visitado = false;
	}

	public void addAdjacencias(Aresta aresta) {
		adjacencias.add(aresta);
	}

	public void marcarComoVisitado() {
		visitado = true;
	}
	
	public String getNome() {
		return nome;
	}
	
	public List<Aresta> getAdjacencias() {
		return adjacencias;
	}

	public boolean isVisitado() {
		return visitado;
	}

	public void setVisitado(boolean visitado) {
		this.visitado = visitado;
	}
}
