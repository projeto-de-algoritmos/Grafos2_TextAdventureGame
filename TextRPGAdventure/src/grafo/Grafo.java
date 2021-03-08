package grafo;

import java.util.ArrayList;
import java.util.List;

public class Grafo{
	
	private List<Vertice> vertices;
	private List<Aresta> arestas;
	
	public Grafo(){
		vertices = new ArrayList<Vertice>();
		arestas = new ArrayList<Aresta>();
	}
	
	public void addVertice(Vertice vertice){
		vertices.add(vertice);
	}

	public Aresta addAresta(Vertice origem, Vertice destino){
		Aresta aresta = new Aresta(1, origem, destino);
		origem.addAdjacencias(aresta);
		arestas.add(aresta);

		return aresta;
	}

	public Aresta addAresta(int peso, Vertice origem, Vertice destino) {
		Aresta aresta = new Aresta(peso, origem, destino);
		origem.addAdjacencias(aresta);
		arestas.add(aresta);

		return aresta;
	}

	public void imprimeGrafo(){
		for(Vertice vertice : this.vertices){
			System.out.print(vertice);
			System.out.println(" ----> ");
			for(Aresta aresta : vertice.getAdjacencias()){
				System.out.print("\t\t\t");
				System.out.print(aresta.getDestino().getNome());
				System.out.print(" - ");
				System.out.println("Peso:" + aresta.getPeso());
			}
		}
	}
	
	public List<Vertice> getVertices() {
		return vertices;
	}
	
	public List<Aresta> getArestas() {
		return arestas;
	}

	public void resetVertices() {
		for (Vertice vertice : vertices) {
			vertice.setVisitado(false);
		}
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}
