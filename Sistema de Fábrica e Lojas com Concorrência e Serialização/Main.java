// Classe principal que cria instâncias do buffer, produtor e consumidor, e inicia suas threads
public class Main {

    public static void main(String[] args) {
        BufferCircular buffer = new BufferCircular(5); // Cria um buffer com tamanho 5
        Produtor produtor = new Produtor(buffer); // Cria um produtor para o buffer
        Consumidor consumidor = new Consumidor(buffer); // Cria um consumidor para o buffer

        produtor.start(); // Inicia a thread do produtor
        consumidor.start(); // Inicia a thread do consumidor
    }
}