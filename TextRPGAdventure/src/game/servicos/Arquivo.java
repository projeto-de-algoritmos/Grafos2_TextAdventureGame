package game.servicos;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Arquivo {
    public static void imprimeImagemAscii(String nome) throws FileNotFoundException {
        File arquivo = new File("src/assets/asciiImages/" + nome + ".txt");
        Scanner scanner = new Scanner(arquivo);

        while (scanner.hasNextLine()) {
            String linha = scanner.nextLine();
            System.out.println(linha);
        }

        scanner.close();
    }
}
