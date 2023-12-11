public class GerenciadorCentral {
    private static FabricaProvedorPagamento fabricaNubank;
    private static FabricaProvedorPagamento fabricaPicPay;
    private static LojaVirtualObservavel lojaMercadoLivre;
    private static LojaVirtualObservavel lojaAliExpress;
    private static CarrinhoDeCompras carrinho;
    private static AgenteLogistico agenteLogistico;
    private static CatalogoProdutos catalogo;  

    public static void inicializar() {
        fabricaNubank = new FabricaProvedorPagamentoNubank();
        fabricaPicPay = new FabricaProvedorPagamentoPicPay();
        lojaMercadoLivre = new LojaVirtualObservavel("Mercado Livre", fabricaNubank);
        lojaAliExpress = new LojaVirtualObservavel("AliExpress", fabricaPicPay);
        carrinho = new CarrinhoDeCompras();
        agenteLogistico = new AgenteLogistico();
        catalogo = new CatalogoProdutos();  // Inicialize a instância do CatalogoProdutos

        // Adicionando observador ao agente logístico
        lojaMercadoLivre.adicionarObservador(agenteLogistico);
        lojaAliExpress.adicionarObservador(agenteLogistico);
    }

    public static LojaVirtualObservavel escolherLoja(int opcao) {
        switch (opcao) {
            case 1:
                return lojaMercadoLivre;
            case 2:
                return lojaAliExpress;
            default:
                return null;
        }
    }

    public static CarrinhoDeCompras getCarrinho() {
        return carrinho;
    }

    public static CatalogoProdutos getCatalogo() {
        return catalogo;  
    }
}
