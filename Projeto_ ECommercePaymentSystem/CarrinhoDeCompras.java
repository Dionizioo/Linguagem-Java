
import java.util.ArrayList;
import java.util.List;

// Carrinho de Compras
class CarrinhoDeCompras {
    List<ItemCompra> itens;

    public CarrinhoDeCompras() {
        this.itens = new ArrayList<>();
    }

    public void adicionarItem(ItemCompra item) {
        if (item != null) {
            itens.add(item);
            System.out.println(item.getQuantidade() + "x " + item.getProduto().getNome() + " adicionado ao carrinho.");
        } else {
            System.out.println("Não foi possível adicionar o item ao carrinho. Dados inválidos.");
        }
    }

    public double calcularTotal() {
        double total = 0;
        for (ItemCompra item : itens) {
            total += item.calcularPrecoTotal();
        }
        return total;
    }

    public void limparCarrinho() {
        this.itens.clear();
    }
}
