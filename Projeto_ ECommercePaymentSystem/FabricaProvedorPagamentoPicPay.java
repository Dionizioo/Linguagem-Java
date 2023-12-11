class FabricaProvedorPagamentoPicPay implements FabricaProvedorPagamento {
    @Override
    public ProvedorPagamento criarProvedorPagamento() {
        return new ProvedorPagamentoPicPay();
    }
}