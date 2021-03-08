package game.models;

import game.Acao;
import game.TipoAcao;
import game.models.item.Item;
import game.models.item.ItemLegivel;

public class InterpreteJogador {

    public Acao interpretarString(String string) {

        if(string.equals("")) {
            return Acao.NADA;
        }

        return agir(string.toLowerCase().split(" "));
    }

    private Acao agir(String[] comandos) throws ArrayIndexOutOfBoundsException {

        if(comandos == null || comandos.length == 0){
            System.out.println("Faça alguma coisa !");
            return Acao.NADA;
        }

        String acaoEscolhida = comandos[0];
        Acao acao = null;

       out:{
           for(Acao a : Acao.values()){
               for(String alias: a.getAliases()){
                   if(acaoEscolhida.compareTo(alias) == 0){
                       acao = a;
                       break out;
                   }
               }
           }
       }

       if(acao == null){
           System.out.println("Faça alguma coisa !");
           return Acao.NADA;
       }

       switch (acao.getTipoAcao()){
           case PEGAR:
               if(comandos.length > 1){
                   String nomeItem = comandos[1];

                   Item item = JogoController.getJogo().coletarItem(nomeItem);

                   if(item != null) {
                       JogoController.getJogo().addItem(item);
                   }

                   return acao;
               } else {
                   System.out.println("Especifique qual item deseja pegar !");
               }

               break;
           case USAR:
               if(comandos.length > 1){
                   String nomeItem = comandos[1];

                   Item item = JogoController.getJogo().utilizarItemInventario(nomeItem);

                   if(item != null) {
                       item.usar();
                   }

                   return acao;
               } else {
                   System.out.println("Especifique qual item deseja usar ou interagir !");
               }

               break;
           case LER:
               if(comandos.length > 1){
                   String nomeItem = comandos[1];

                   Item item = JogoController.getJogo().utilizarItemInventario(nomeItem);

                   if(item != null) {
                       if(item instanceof ItemLegivel){
                           item.usar();
                       } else {
                           System.out.println("Não é possível ler este item !");
                       }
                   }

                   return acao;
               } else {
                   System.out.println("Especifique qual item deseja ler !");
               }

               break;
           case ANDAR:
               if(comandos.length > 1){
                   String nomeSala = comandos[1];

                   Area salaAtual = JogoController.getJogo().getAreaAtualJogador();

                   Area areaIndicada = JogoController.getJogo().identificarAreaConectada(salaAtual, nomeSala);

                   if(areaIndicada != null){
                        JogoController.getJogo().atualizarAreaAtual(areaIndicada);
                   } else {
                       System.out.println("Esse movimento não é permitido !");
                   }

                   return acao;
               } else {
                   System.out.println("Especifique para onde deseja ir !");
               }

               break;
           case OLHAR:
               JogoController.getJogo().identificarAreaAtual().mostrarDescricaoDetalhada();
               break;
           default:
               System.out.println("Esta não é uma ação válida !");
               break;
       }

       return null;

    }

}
