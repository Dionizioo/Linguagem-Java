import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class Concessionaria {
    private Armazenamento armazenamento;

    public Concessionaria(Armazenamento armazenamento) {
        this.armazenamento = armazenamento;
    }

    public void adicionarVeiculo(Veiculo veiculo) {
        armazenamento.adicionarVeiculo(veiculo);
    }

    public void removerVeiculo(int indice) {
        List<Veiculo> veiculos = armazenamento.recuperarVeiculos();
        if (indice >= 0 && indice < veiculos.size()) {
            veiculos.remove(indice);
            System.out.println("Veículo removido com sucesso.");
        } else {
            System.out.println("Índice de veículo inválido.");
        }
    }

    public List<Veiculo> listarVeiculos() {
        List<Veiculo> veiculos = armazenamento.recuperarVeiculos();
        if (veiculos.isEmpty()) {
            System.out.println("Nenhum veículo disponível.");
        } else {
            for (int i = 0; i < veiculos.size(); i++) {
                Veiculo veiculo = veiculos.get(i);
                System.out.println("Índice: " + i);
                System.out.println("Marca: " + veiculo.getMarca());
                System.out.println("Modelo: " + veiculo.getModelo());
                System.out.println("Ano: " + veiculo.getAno());
                System.out.println("Preço: " + veiculo.getPreco());
                if (veiculo instanceof Carro) {
                    Carro carro = (Carro) veiculo;
                    System.out.println("Tipo: Carro");
                    System.out.println("Número de Portas: " + carro.getNumPortas());
                } else if (veiculo instanceof Motocicleta) {
                    Motocicleta moto = (Motocicleta) veiculo;
                    System.out.println("Tipo: Motocicleta");
                    System.out.println("Cilindradas: " + moto.getCilindradas());
                }
                System.out.println();
            }
        }
        return veiculos;
    }

    public void alterarAtributo(int indice, String atributo, String novoValor) {
        List<Veiculo> veiculos = armazenamento.recuperarVeiculos();
        if (indice >= 0 && indice < veiculos.size()) {
            Veiculo veiculo = veiculos.get(indice);
            switch (atributo.toLowerCase()) {
                case "marca":
                    veiculo.setMarca(novoValor);
                    break;
                case "modelo":
                    veiculo.setModelo(novoValor);
                    break;
                case "ano":
                    veiculo.setAno(Integer.parseInt(novoValor));
                    break;
                case "preco":
                    veiculo.setPreco(Double.parseDouble(novoValor));
                    break;
                default:
                    System.out.println("Atributo inválido.");
                    return;
            }
            // Atualize o veículo no armazenamento
            armazenamento.atualizarVeiculo(indice, veiculo);
            System.out.println("Atributo alterado com sucesso.");
        } else {
            System.out.println("Índice de veículo inválido.");
        }
    }

    public void gerarArquivoTXT() {
            List<Veiculo> veiculos = armazenamento.recuperarVeiculos();
        if (veiculos.isEmpty()) {
            System.out.println("Nenhum veículo disponível para gerar o arquivo.");
            return;
        }

        String nomeArquivo = "estoquedecarros.txt"; // Nome fixo do arquivo

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nomeArquivo))) {
            for (Veiculo veiculo : veiculos) {
                // Escreva os detalhes dos veículos no arquivo
                writer.write("Marca: " + veiculo.getMarca() + "\n");
                writer.write("Modelo: " + veiculo.getModelo() + "\n");
                writer.write("Ano: " + veiculo.getAno() + "\n");
                writer.write("Preço: " + veiculo.getPreco() + "\n");

                if (veiculo instanceof Carro) {
                    Carro carro = (Carro) veiculo;
                    writer.write("Tipo: Carro\n");
                    writer.write("Número de Portas: " + carro.getNumPortas() + "\n");
                } else if (veiculo instanceof Motocicleta) {
                    Motocicleta moto = (Motocicleta) veiculo;
                    writer.write("Tipo: Motocicleta\n");
                    writer.write("Cilindradas: " + moto.getCilindradas() + "\n");
                }

                writer.write("\n");
            }

            System.out.println("Arquivo TXT gerado com sucesso.");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Erro ao gerar o arquivo TXT.");
        }
}

    public void atualizarVeiculo(int indice, Veiculo veiculo) {
    }   }
