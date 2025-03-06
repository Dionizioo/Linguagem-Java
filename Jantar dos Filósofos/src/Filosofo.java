import java.util.concurrent.Semaphore;

public class Filosofo extends Thread {
    private int id;
    private Garfo garfoEsquerdo;
    private Garfo garfoDireito;
    private Semaphore mutex;

    public Filosofo(int id, Garfo garfoEsquerdo, Garfo garfoDireito, Semaphore mutex) {
        this.id = id;
        this.garfoEsquerdo = garfoEsquerdo;
        this.garfoDireito = garfoDireito;
        this.mutex = mutex;
    }

    public void run() {
        while (true) {
            try {
                pensar();
                comer();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void pensar() throws InterruptedException {
        System.out.println("Filósofo " + id + " está pensando");
        Thread.sleep((long) (Math.random() * 1000));
    }

    private void comer() throws InterruptedException {
        mutex.acquire(); // Garante exclusão mútua para pegar garfos
        garfoEsquerdo.pegarGarfo(id); // Usando o método pegarGarfo do objeto Garfo
        garfoDireito.pegarGarfo(id); // Usando o método pegarGarfo do objeto Garfo
        mutex.release(); // Libera o mutex

        System.out.println("Filósofo " + id + " está comendo");
        Thread.sleep((long) (Math.random() * 1000));

        garfoDireito.liberarGarfo(id); // Usando o método liberarGarfo do objeto Garfo
        garfoEsquerdo.liberarGarfo(id); // Usando o método liberarGarfo do objeto Garfo
    }
}
