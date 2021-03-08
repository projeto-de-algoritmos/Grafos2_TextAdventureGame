package game.models;

import game.models.item.Item;
import grafo.Aresta;
import grafo.Grafo;
import grafo.navegacao.Navegacao;
import grafo.Vertice;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public final class JogoController {

    private static JogoController jogo;
    private Jogador jogador;
    private Chefe chefe;
    private Grafo grafo = new Grafo();
    private static InterpreteJogador interpreteJogador;

    private Integer turnoAtualJogador;
    private Integer turnoAtualJogo;
    private Integer turnoAtualChefe;

    private Boolean jogoAcabou = false;

    public void iniciarJogo(String nome, Integer vida, Area areaInicial){
        iniciarJogo(nome, vida, areaInicial, null);
    }

    public void iniciarJogo(String nome, Integer vida, Area areaInicial, Chefe chefe){
        this.chefe = chefe;
        jogador = new Jogador(nome, vida, areaInicial);
        interpreteJogador = new InterpreteJogador();
        Navegacao navegacao = new Navegacao();
        Scanner scanner = new Scanner(System.in);
        turnoAtualJogo = 1;
        turnoAtualChefe = 1;
        turnoAtualJogador = 1;

        //Andamento do jogo - Só para no fim do jogo;
        while(!jogoAcabou){
            System.out.println("=============================================================");
            // 0 - Printar turno atual
            System.out.println("Turno atual: " + turnoAtualJogo);
            System.out.println("Local do boss: " + chefe.getAreaAtual().getNome());

            // 1 - identifica a área atual
            Area areaAtual = identificarAreaAtual();

            if(turnoAtualJogador <= turnoAtualJogo){

                // 1 - descreve a área e itens
                System.out.println(areaAtual.getDescricao());
                String comando = scanner.nextLine();
                interpreteJogador.interpretarString(comando);
            } else {
                System.out.println("O personagem ainda não finalizou a ação !");
            }
            // 2 - verifica e executa ação desejada - interprete

            // 3 - Realiza ações do chefe
            if(chefe != null){
                if(turnoAtualChefe <= turnoAtualJogo){
                    chefe.agir();
                }
            }

            // 4 - verifica se está em batalha
            // 5 - verifica se o jogo acabou
            // 6 - verificações de itens e outras coisas
            jogador.verificarItensEsgotadosOuQuebrados();

            System.out.println("\n\n\n\n");
            turnoAtualJogo++;
        }
    }

    public static JogoController getJogo() {
        if (jogo == null) {
            jogo = new JogoController();
        }

        return jogo;
    }

    public void removerVida(Integer quantidade){
        Integer vidaAtual = jogador.getVida();
        jogador.setVida(vidaAtual - quantidade);
    }

    public void adicionarVida(Integer quantidade){
        Integer vidaAtual = jogador.getVida();
        jogador.setVida(vidaAtual + quantidade);
    }

    public Area getAreaAtualJogador(){
        return jogador.getAreaAtual();
    }

    public Item coletarItem(String nomeItemDesejado) {

        Area areaAtual = getJogo().identificarAreaAtual();

        if(areaAtual != null) {
            for(Item item : areaAtual.getItens()) {
                if(item.getNome().toLowerCase().contains(nomeItemDesejado.toLowerCase()) || nomeItemDesejado.toLowerCase().contains(item.getNome().toLowerCase())) {
                    return item;
                }
            }

            System.out.println("Este item não foi encontrado !");

        } else {
            System.out.println("Erro desconhecido ao identificar área atual !");
        }

        return null;
    }

    public Area identificarAreaAtual(){
        Navegacao navegacao = new Navegacao();

        Vertice area = navegacao.buscaVertice(grafo, this.getAreaAtualJogador().getNome());

        if(area instanceof Area) {
            Area areaAtual = ((Area) area);
            return areaAtual;
        } else {
            System.out.println("Erro desconhecido ao identificar área atual !");
            return null;
        }
    }

    public void addArea(Area area){
        this.grafo.addVertice(area);
    }

    public void conectarArea(int distancia, Area origem, Area destino){
        this.grafo.addAresta(distancia, origem, destino);
    }

    public void conectarArea(Area origem, Area destino){
        this.grafo.addAresta(origem, destino);
    }

    public Item utilizarItemInventario(String nomeItemDesejado) {

        for(Item item : jogador.getItens()) {
            if(item.getNome().toLowerCase().contains(nomeItemDesejado.toLowerCase()) || nomeItemDesejado.toLowerCase().contains(item.getNome().toLowerCase()) ) {
                return item;
            }
        }

        System.out.println("Este item não foi encontrado !");

        return null;
    }

    public void addItem(Item e){
        jogador.getItens().add(e);
        System.out.println ("O item " + e.getNome() + " foi coletado com sucesso !");
    }

    public void atualizarAreaAtual(Area area){
        jogador.setAreaAtual(area);
    }

    public Area identificarAreaConectada(Area salaAtual, String salaFinal) {
        Navegacao navegacao = new Navegacao();

        Vertice verticeFinal = navegacao.retornarAreaConectada(salaAtual, salaFinal);

        if(verticeFinal instanceof Area){
            Area areaFinal =  (Area) verticeFinal;

            return areaFinal;
        }

        return null;
    }

    public Integer identificaDistancia(Area salaAtual, Area areaDesejada) {
        Navegacao navegacao = new Navegacao();

        Aresta caminho = navegacao.retornarCaminhoConectado(salaAtual, areaDesejada);

        if(caminho != null){
            return caminho.getPeso();
        }

        return null;
    }

    public void imprimeMapaJogo(){
        grafo.imprimeGrafo();
    }

    public List<Area> listarAreasJogo(){
        List<Area> areas = new ArrayList<>();

        for(Vertice vertice: grafo.getVertices()){
            if(vertice instanceof Area){
                Area area = (Area) vertice;
                areas.add(area);
            }
        }

        return areas;
    }

    public Area perseguirJogador(){
        LinkedList<Vertice> caminho = Navegacao.menorCaminho(grafo, chefe.getAreaAtual(), jogador.getAreaAtual());

        Area areaAlvo = null;

        if(caminho != null){
            areaAlvo = (Area) caminho.get(1);
        }

       return areaAlvo;

    }

    public Grafo getGrafo() {
        return grafo;
    }

    public void incrementarTurnosJogador(Integer turnosGastos){
        this.turnoAtualJogador += turnosGastos;
    }

    public void incrementarTurnosChefe(Integer turnosGastos){
        this.turnoAtualChefe += turnosGastos;
    }

    public void finalizarJogo(){
        this.jogoAcabou = true;
    }

    public Jogador getJogador() {
        return jogador;
    }

    public Integer getTurnoAtualJogador() {
        return turnoAtualJogador;
    }

    public Integer getTurnoAtualChefe() {
        return turnoAtualChefe;
    }
}
