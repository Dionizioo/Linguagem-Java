import java.util.concurrent.Semaphore;

public class Garfo {
    private final String nome;
    private Semaphore semaforo; // Semáforo para controlar o acesso ao garfo

    public Garfo(String nome) {
        this.nome = nome;
        this.semaforo = new Semaphore(1); // Inicializa o semáforo com 1 para permitir acesso inicial ao garfo
    }

    public void pegarGarfo(int filosofoId) throws InterruptedException {
        semaforo.acquire(); // Adquire permissão para pegar o garfo
        System.out.println("Filósofo " + filosofoId + " pegou o garfo " + nome);
    }

    public void liberarGarfo(int filosofoId) {
        semaforo.release(); // Libera a permissão para largar o garfo
        System.out.println("Filósofo " + filosofoId + " largou o garfo " + nome);
    }
}
