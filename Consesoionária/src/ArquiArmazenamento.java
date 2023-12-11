import java.util.ArrayList;
import java.util.List;

// Classe ArquivoArmazenamento que implementa Armazenamento
class ArquiArmazenamento implements Armazenamento {
    //private String arquivo;
    private List<Veiculo> veiculos = new ArrayList<>();

    public ArquiArmazenamento(String arquivo){
        //this.arquivo = arquivo;
    }

    @Override
    public void adicionarVeiculo(Veiculo veiculo) {
        veiculos.add(veiculo);
    }

    @Override
    public List<Veiculo> recuperarVeiculos() {
        return veiculos;
    }

    @Override
    public void atualizarVeiculo(int indice, Veiculo novoVeiculo) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'atualizarVeiculo'");
    }
}
