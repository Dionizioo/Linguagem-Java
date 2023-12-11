// Classe abstrata para itens do carrinho
abstract class ItemCarrinho {
    String nome;
    double preco;

    public ItemCarrinho(String nome, double preco) {
        this.nome = nome;
        this.preco = preco;
    }

    abstract double calcularPrecoTotal(int quantidade);
}