// Classe principal do sistema de comércio eletrônico
class LojaVirtual {
    private FabricaProvedorPagamento fabricaProvedorPagamento;

    public LojaVirtual(FabricaProvedorPagamento fabricaProvedorPagamento) {
        this.fabricaProvedorPagamento = fabricaProvedorPagamento;
    }

    public void realizarCompra(double valor) {
        ProvedorPagamento provedorPagamento = fabricaProvedorPagamento.criarProvedorPagamento();
        provedorPagamento.processarPagamento(valor);
    }
}