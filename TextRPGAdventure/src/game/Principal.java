package game;

import game.models.Area;
import game.models.Imagens;
import game.models.JogoController;
import game.models.item.*;
import game.servicos.Arquivo;
import grafo.Grafo;
import grafo.Vertice;
import grafo.navegacao.Navegacao;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Principal {

    public static void main(String[] args) {

        JogoController jogo = JogoController.getJogo();

        Area salaEscura = new Area("Sala Escura", "Você se encontra em uma sala repleta de sombras e pouca iluminação. Suas mãos encontram-se machucadas e você sente o chão frio sob seus pés.");

        ItemDecorativo espelho = new ItemDecorativo("Espelho", "Um espelho quebrado que te mostra seu reflexo borrado.", false);

        List<String> textosDiario = new ArrayList<>();

        textosDiario.add("Ao abrir o diário, você consegue ler entre as folhas sujas e amassadas, a seguinte passagem: ");
        textosDiario.add("'Estou aqui a mais ou menos 128 dias. Não sei mais se consigo escapar. O carrasco deixa água e restos de comida, e sai. Eu nem ao menos tenho força para tentar enfrentá-lo.'");

        ItemLegivel diario = new ItemLegivel("Diário Velho", "Um velho diário gasto pelo tempo.", false, textosDiario);

        Caixa caixa = new Caixa("Caixa Trancada", "Uma pequena caixa trancada.", false, "Esta caixa está trancada !");

        Rastreador rastreador = new Rastreador("Rastreador", "Uma espécie de radar altamente tecnológico que provavelmente deu muito trabalho para ser desenvolvido.", false);

        salaEscura.addItem(espelho);
        salaEscura.addItem(diario);
        salaEscura.addItem(caixa);
        salaEscura.addItem(rastreador);

        Area banheiroEscuro = new Area("Banheiro Escuro", "Um banheiro com cheiro pútrido. O chão parece estar coberto por uma espécie de gosma pegajosa.");

        Area garagem = new Area("Garagem", "Um leve cheiro de óleo de motor, e várias bugigangas espalhadas pelo chão, mas nada muito interessante.");
        Area porao = new Area("Porão", "Um porão completamente escuro, cheios de madeiras velhas e outras coisas descartadas. Alguma coisa parece brilhar na escuridão.");

        ItemChave chave = new ItemChave("Chave Pequena", "Uma pequena chave reluzente.", true, caixa, "Você conseguiu abrir a caixa !");
        porao.addItem(chave);

        jogo.addArea(salaEscura);
        jogo.addArea(banheiroEscuro);
        jogo.addArea(garagem);
        jogo.addArea(porao);

        jogo.conectarArea(salaEscura, banheiroEscuro);
        jogo.conectarArea(banheiroEscuro, salaEscura);
        jogo.conectarArea(2,  salaEscura, garagem);
        jogo.conectarArea(2, garagem, salaEscura);
        jogo.conectarArea(100,  salaEscura, porao);
        jogo.conectarArea(10,  porao, salaEscura);
        jogo.conectarArea(13, garagem, porao);
        jogo.conectarArea(13, porao, garagem);

        System.out.println("\n\n\n");

//        try {
//            Arquivo.imprimeImagemAscii("olhos");
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }

//        jogo.iniciarJogo("Caio", 15, salaEscura);

        testaMenorCaminho(jogo.getGrafo(), salaEscura, porao);
    }

    private static void testaMenorCaminho(Grafo grafo, Vertice inicio, Vertice fim) {
        LinkedList<Vertice> caminho = Navegacao.menorCaminho(grafo, inicio, fim);
        caminho.forEach(vertice -> System.out.print(vertice.getNome() + " → "));
        System.out.print("↓");
    }

}
