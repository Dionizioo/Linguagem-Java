import java.util.List;

// Interface Armazenamento
interface Armazenamento {
    // Método para adicionar um veículo ao armazenament
    void adicionarVeiculo(Veiculo veiculo);
    // Método para recuperar a lista de veículos armazenados
    List<Veiculo>recuperarVeiculos();

    void atualizarVeiculo(int indice, Veiculo novoVeiculo);
}
