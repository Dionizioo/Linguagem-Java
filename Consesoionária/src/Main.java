import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Inicialização da conexão com o banco de dados
        Connection connection = null;
        try {
            connection = BancoDeDados.conectar();
        } catch (SQLException e) {
            e.printStackTrace();
            return; // Encerra o programa em caso de erro na conexão
        }

        // Criação da tabela no banco de dados (se ainda não existir)
        BancoDeDados.criarTabela();

        // Inicialização do scanner para leitura de entrada do usuário
        Scanner scanner = new Scanner(System.in);

        // Criação da instância da classe Concessionaria com injeção de dependência para o armazenamento em banco
        Concessionaria concessionaria = new Concessionaria(new BancoArmazenamento());

        while (true) {
            System.out.println("Menu:");
            System.out.println("1. Cadastrar veículo");
            System.out.println("2. Remover veículo");
            System.out.println("3. Listar veículos");
            System.out.println("4. Alterar atributo do veículo");
            System.out.println("5. Gerar arquivo TXT do banco");
            System.out.println("6. Sair");
            System.out.print("Escolha uma opção: ");

            int opcao;
            if (scanner.hasNextInt()) {
                opcao = scanner.nextInt();
                scanner.nextLine(); // Consume a nova linha
            } else {
                System.out.println("Opção inválida. Digite um número válido.");
                scanner.nextLine(); // Consume a entrada inválida
                continue;
            }

            switch (opcao) {
                case 1:
                    cadastrarVeiculo(scanner, concessionaria);
                    break;
                case 2:
                    removerVeiculo(scanner, concessionaria);
                    break;
                case 3:
                    listarVeiculos(concessionaria);
                    break;
                case 4:
                    alterarAtributo(scanner, concessionaria);
                    break;
                case 5:
                    String nomeArquivo = "estoquedecarros.txt"; // Nome fixo do arquivo
                    gerarArquivoTXT(concessionaria, nomeArquivo);
                    break;
                case 6:
                    System.out.println("Saindo do programa.");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }

    private static void cadastrarVeiculo(Scanner scanner, Concessionaria concessionaria) {
        System.out.print("Digite a marca: ");
        String marca = scanner.nextLine();
        System.out.print("Digite o modelo: ");
        String modelo = scanner.nextLine();
        System.out.print("Digite o ano de fabricação: ");
        int ano = scanner.nextInt();
        System.out.print("Digite o preço: ");
        double preco = scanner.nextDouble();

        System.out.println("Escolha o tipo de veículo:");
        System.out.println("1. Carro");
        System.out.println("2. Motocicleta");
        System.out.print("Digite o número da opção: ");
        int tipoVeiculo = scanner.nextInt();

        if (tipoVeiculo == 1) {
            System.out.print("Digite o número de portas: ");
            int numPortas = scanner.nextInt();
            Carro carro = new Carro(marca, modelo, ano, preco, numPortas);
            concessionaria.adicionarVeiculo(carro);
            System.out.println("Carro cadastrado com sucesso.");
        } else if (tipoVeiculo == 2) {
            System.out.print("Digite as cilindradas: ");
            int cilindradas = scanner.nextInt();
            Motocicleta moto = new Motocicleta(marca, modelo, ano, preco, cilindradas);
            concessionaria.adicionarVeiculo(moto);
            System.out.println("Motocicleta cadastrada com sucesso.");
        } else {
            System.out.println("Opção de veículo inválida.");
        }
    }

    private static void removerVeiculo(Scanner scanner, Concessionaria concessionaria) {
        System.out.print("Digite o índice do veículo a ser removido: ");
        int indice = scanner.nextInt();
        concessionaria.removerVeiculo(indice);
        System.out.println("Veículo removido com sucesso.");
    }

    private static void listarVeiculos(Concessionaria concessionaria) {
        List<Veiculo> veiculos = concessionaria.listarVeiculos();
        System.out.println("Lista de veículos disponíveis:");
        for (int i = 0; i < veiculos.size(); i++) {
            Veiculo veiculo = veiculos.get(i);
            System.out.println("Índice " + i + ": " + veiculo.getMarca() + " " + veiculo.getModelo() + " (" + veiculo.getAno() + ") - R$ " + veiculo.getPreco());
        }
    }

    private static void alterarAtributo(Scanner scanner, Concessionaria concessionaria) {
        listarVeiculos(concessionaria);
        System.out.print("Digite o índice do veículo a ser alterado: ");
        int indice = scanner.nextInt();
        scanner.nextLine(); // Consume a nova linha
    
        System.out.print("Qual atributo deseja alterar (marca, modelo, ano, preco): ");
        String atributo = scanner.nextLine();
        System.out.print("Digite o novo valor para o atributo: ");
        String novoValor = scanner.nextLine();
    
        concessionaria.alterarAtributo(indice, atributo, novoValor);
    }

    private static void gerarArquivoTXT(Concessionaria concessionaria, String nomeArquivo) {
        concessionaria.gerarArquivoTXT();
        System.out.println("Arquivo TXT gerado com sucesso.");
    }
}
