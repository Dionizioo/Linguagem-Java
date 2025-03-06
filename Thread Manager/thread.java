public class thread extends Thread{

    private String nome;
    private int tempo;

    public thread(String nome,int tempo) {
        this.nome = nome;
        this.tempo = tempo;
        start();
    }
    public void run(){

        try {
            System.out.println("Executando");

            for ( int i = 0 ; i < 6 ; i++){
                System.out.println(nome + " contador " + i );
                Thread.sleep(tempo);
            }
            Thread.sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(nome + " terminou");
    }
}
