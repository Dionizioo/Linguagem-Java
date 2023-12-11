class FabricaProvedorPagamentoNubank implements FabricaProvedorPagamento {
    @Override
    public ProvedorPagamento criarProvedorPagamento() {
        return new ProvedorPagamentoNubank();
    }
}
