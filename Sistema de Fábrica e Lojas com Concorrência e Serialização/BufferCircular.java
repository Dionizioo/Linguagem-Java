import java.util.concurrent.Semaphore;
public class BufferCircular {
    private int[] buffer; // Array para armazenar os itens no buffer
    private int tamanho; // Tamanho do buffer
    private int entrada, saida; // Índices de entrada e saída no buffer

    // Semáforos para controlar o acesso ao buffer
    private Semaphore mutex; //exclusão mútua
    private Semaphore vazio; //controlar espaços vazios no buffer
    private Semaphore cheio; //controlar espaços cheios no buffer

    // Construtor para inicializar o buffer com o tamanho especificado
    public BufferCircular(int tamanho) {
        this.tamanho = tamanho;
        this.buffer = new int[tamanho];
        this.entrada = 0;
        this.saida = 0;

        // Inicialização dos semáforos
        this.mutex = new Semaphore(1); // Mutex começa com permissão de acesso garante uma Thread por vez
        this.vazio = new Semaphore(tamanho); // Todos os espaços inicialmente vazios
        this.cheio = new Semaphore(0); // Nenhum item inicialmente no buffer
    }

    // produzir um item e colocá-lo no buffer
    public void produzir(int item) throws InterruptedException {
        vazio.acquire(); // Aguarda até que haja espaço vazio no buffer
        mutex.acquire(); // Garante exclusão mútua no acesso ao buffer

        // Adiciona o item ao buffer e atualiza o índice de entrada
        buffer[entrada] = item;
        entrada = (entrada + 1) % tamanho; //aqui que a acontece o buffer circular,onde o indice de entrada é atualizado
        //e não deixa o buffer estourar
        System.out.println("Produzido: " + item);

        mutex.release(); // Libera o acesso ao buffer
        cheio.release(); // Indica que o buffer agora tem um item adicional
    }

    // Método para consumir um item do buffer
    public int consumir() throws InterruptedException {
        cheio.acquire(); // Aguarda até que haja pelo menos um item no buffer
        mutex.acquire(); // Garante exclusão mútua no acesso ao buffer

        // Remove o item do buffer e atualiza o índice de saída
        int item = buffer[saida];
        saida = (saida + 1) % tamanho;
        System.out.println("Consumido: " + item);

        mutex.release(); // Libera o acesso ao buffer
        vazio.release(); // Indica que o buffer agora tem um espaço vazio

        return item; // Retorna o item consumido
    }
}

// Classe para representar um produtor que produz itens para o buffer
class Produtor extends Thread {
    private BufferCircular buffer;

    public Produtor(BufferCircular buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 10; i++) {
                buffer.produzir(i); // Produz um item e coloca no buffer
                Thread.sleep((long) (Math.random() * 1000)); // Pausa aleatória
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

// Classe para representar um consumidor que consome itens do buffer
class Consumidor extends Thread {
    private BufferCircular buffer;

    public Consumidor(BufferCircular buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 10; i++) {
                buffer.consumir(); // Consome um item do buffer
                Thread.sleep((long) (Math.random() * 1000)); // Pausa aleatória
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
