import java.util.concurrent.Semaphore;

public class Jantar {
    public static void main(String[] args) {
        Semaphore mutex = new Semaphore(1); // Semáforo para garantir exclusão mútua

        Garfo garfoEsquerdo = new Garfo("Esquerdo");
        Garfo garfoDireito = new Garfo("Direito");

        Filosofo[] filosofos = new Filosofo[5];
        for (int i = 0; i < 5; i++) {
            filosofos[i] = new Filosofo(i, garfoEsquerdo, garfoDireito, mutex);
            filosofos[i].start();
        }
    }
}
