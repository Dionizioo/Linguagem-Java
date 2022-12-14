//Alunos:
//Vinicius Dionizio Patrocinio


//import
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;



public class Genius {

    public static int PontosUsuarios; // Varialvel dos Pontos do Usuarios
    public static Scanner entrada = new Scanner(System.in); // entrada 
    public static Random gerador = new Random();// Gerador

    public static List<Integer> NumSorteados = new ArrayList<Integer>(); // Variavel do NumSorteados
    public static List<Integer> Num = new ArrayList<Integer>(); // Variavel do Num
    
    //Inicia o Sorteador
    public static void Sorteador() {
        int NumSort = 1 + gerador.nextInt(1 + 4); 
        NumSorteados.add(NumSort);
    }

    //Inicia a Escolha do usuario
    public static void Escolha() {
        System.out.println("\nDigite a sequencia: "); 
        Num.add(entrada.nextInt());
    }

    //Inicia do jogo Genius
    public static void main(String[] args) throws IOException, InterruptedException {
        System.out.println("Bem-vindo ao jogo do Genius");
        System.out.println("O jogo vai começar- PRESTE ATENÇAÕ!!!!!");
        do {
            Num.clear();
            Sorteador();
            for (int i = 0; i < NumSorteados.size(); i++) {
                NumSorteados.get(i);
                if (i == 0) {
                    System.out.printf("Rodada #%d: %d", NumSorteados.size(), NumSorteados.get(i));
                    Thread.sleep(2500);//Limpa na tela 
                    new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();//Limpa a tela do Console
                } else if (i > 0) {
                    System.out.printf("\n%d", NumSorteados.get(i));
                    Thread.sleep(1000);//Limpa na tela
                    new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();//Limpa a tela do Console
                }
            }
            for (int i = 0; i < NumSorteados.size(); i++) {
                Escolha();
            }
            if (Num.equals(NumSorteados)) {
                PontosUsuarios += 5;
            }

        } while (Num.equals(NumSorteados));
        System.out.println("Não foi dessa vez tente na proxima!!");
        System.out.printf("Sua pontuação foi: %d", PontosUsuarios);
    }
}