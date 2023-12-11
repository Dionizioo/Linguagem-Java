//
class Carro extends Veiculo {
    private int numPortas;

    public Carro(String marca, String modelo, int ano, double preco, int numPortas) {
        super(marca, modelo, ano, preco);
        this.numPortas = numPortas;
    }

    public int getNumPortas() {
        return numPortas;
    }

}
