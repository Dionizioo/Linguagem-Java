// Implementação concreta de um item do carrinho
class Produto extends ItemCarrinho {
    public Produto(String nome, double preco) {
        super(nome, preco);
    }

    @Override
    double calcularPrecoTotal(int quantidade) {
        return preco * quantidade;
    }

    // Adição do método getNome()
    public String getNome() {
        return nome;
    }
    // Método adicionado para obter o preço
    public double getPreco() {
        return preco;
    }
}
