// Classe para representar os produtos disponíveis na loja

import java.util.HashMap;
import java.util.Map;

class CatalogoProdutos {
    private Map<Integer, Produto> catalogo;

    public CatalogoProdutos() {
        this.catalogo = new HashMap<>();
        inicializarCatalogo();
    }

    private void inicializarCatalogo() {
        catalogo.put(1, new Produto("Camiseta", 29.99));
        catalogo.put(2, new Produto("Calça Jeans", 59.99));
        catalogo.put(3, new Produto("Tênis Esportivo", 79.99));
        catalogo.put(4, new Produto("Bermuda Casual", 39.99));
        catalogo.put(5, new Produto("Relógio de Pulso", 99.99));
    
    }

    public void mostrarCatalogo() {
        System.out.println("Catálogo de Produtos:");
        for (Map.Entry<Integer, Produto> entry : catalogo.entrySet()) {
            System.out.println(entry.getKey() + ". " + entry.getValue().getNome() + " - R$" + entry.getValue().getPreco());
        }
    }

    public Produto getProdutoPorCodigo(int codigo) {
        return catalogo.get(codigo);
    }
}