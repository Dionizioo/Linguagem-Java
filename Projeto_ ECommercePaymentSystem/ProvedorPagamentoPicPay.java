class ProvedorPagamentoPicPay implements ProvedorPagamento {
    @Override
    public void processarPagamento(double valor) {
        System.out.println("Pagamento processado via PicPay: R$ " + valor);
    }
}