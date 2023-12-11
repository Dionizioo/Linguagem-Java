// Implementação concreta de um provedor de pagamento
class ProvedorPagamentoNubank implements ProvedorPagamento {
    @Override
    public void processarPagamento(double valor) {
        System.out.println("Pagamento processado via Nubank: R$ " + valor);
    }
}