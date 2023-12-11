import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Inicializar o gerenciador central
        GerenciadorCentral.inicializar();

        int opcao;
        LojaVirtualObservavel lojaEscolhida = null;

        do {
            System.out.println("Escolha uma opção:");
            System.out.println("1. Escolher Loja");
            System.out.println("2. Adicionar Item ao Carrinho");
            System.out.println("3. Mostrar Carrinho");
            System.out.println("4. Escolher Forma de Pagamento");
            System.out.println("5. Cancelar Compra");
            System.out.println("0. Encerrar Compras");

            System.out.print("Digite o número da opção desejada: ");
            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    // Escolher Loja
                    System.out.println("Escolha a loja:");
                    System.out.println("1. Mercado Livre");
                    System.out.println("2. AliExpress");

                    System.out.print("Digite o número da opção desejada: ");
                    int opcaoLoja = scanner.nextInt();
                    lojaEscolhida = GerenciadorCentral.escolherLoja(opcaoLoja);

                    if (lojaEscolhida != null) {
                        System.out.println("Loja escolhida: " + lojaEscolhida.getNomeLoja());
                    } else {
                        System.out.println("Opção inválida. Tente novamente.");
                    }
                    break;

                case 2:
                    // Adicionar Item ao Carrinho
                    if (lojaEscolhida != null) {
                        GerenciadorCentral.getCatalogo().mostrarCatalogo();
                        System.out.print("Digite o número do produto desejado: ");
                        int codigoProduto = scanner.nextInt();
    
                        Produto produtoEscolhido = GerenciadorCentral.getCatalogo().getProdutoPorCodigo(codigoProduto);
                        if (produtoEscolhido != null) {
                            System.out.print("Digite a quantidade desejada: ");
                            int quantidadeProduto = scanner.nextInt();
    
                            ItemCompra item = new ItemCompra(produtoEscolhido, quantidadeProduto);
                            GerenciadorCentral.getCarrinho().adicionarItem(item);
                        } else {
                            System.out.println("Produto inválido. Tente novamente.");
                        }
                    } else {
                        System.out.println("Escolha uma loja antes de adicionar itens ao carrinho.");
                    }
                    break;

                case 3:
                    // Mostrar Carrinho
                    if (lojaEscolhida != null) {
                        System.out.println("Carrinho Atual:");
                        System.out.println("Total: R$ " + GerenciadorCentral.getCarrinho().calcularTotal());
                    } else {
                        System.out.println("Escolha uma loja antes de mostrar o carrinho.");
                    }
                    break;

                case 4:
                    // Escolher Forma de Pagamento
                    if (lojaEscolhida != null) {
                        System.out.println("Escolha a forma de pagamento:");
                        System.out.println("1. Pagar com Nubank");
                        System.out.println("2. Pagar com PicPay");

                        System.out.print("Digite o número da opção desejada: ");
                        int opcaoPagamento = scanner.nextInt();

                        lojaEscolhida.realizarCompra(GerenciadorCentral.getCarrinho().calcularTotal());
                    } else {
                        System.out.println("Escolha uma loja antes de escolher a forma de pagamento.");
                    }
                    break;

                    case 5:
                    // Cancelar Compra
                    if (lojaEscolhida != null) {
                        System.out.println("Deseja realmente cancelar a compra? (Digite 'S' para Sim ou 'N' para Não)");
                        String confirmacao = scanner.next();
                
                        if (confirmacao.equalsIgnoreCase("S")) {
                            GerenciadorCentral.getCarrinho().limparCarrinho();
                            System.out.println("Compra cancelada. Carrinho zerado.");
                        } else if (confirmacao.equalsIgnoreCase("N")) {
                            System.out.println("Compra não cancelada. Retornando ao menu.");
                        } else {
                            System.out.println("Opção inválida. Retornando ao menu.");
                        }
                    } else {
                        System.out.println("Escolha uma loja antes de cancelar a compra.");
                    }
                    break;
            }

        } while (opcao != 0);

        // Fechar o scanner
        scanner.close();
    }
}
