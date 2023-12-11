import java.util.ArrayList;
import java.util.List;

class LojaVirtualObservavel implements ObservavelConfirmacaoCompra {
    private List<ObservadorConfirmacaoCompra> observadores;
    private FabricaProvedorPagamento fabricaProvedorPagamento;
    private String nomeLoja;

    public LojaVirtualObservavel(String nomeLoja, FabricaProvedorPagamento fabricaProvedorPagamento) {
        this.nomeLoja = nomeLoja;
        this.fabricaProvedorPagamento = fabricaProvedorPagamento;
        this.observadores = new ArrayList<>();
    }

    public void realizarCompra(double valor) {
        ProvedorPagamento provedorPagamento = fabricaProvedorPagamento.criarProvedorPagamento();
        
        // Aplicar juros se o provedor for PicPay ou Nubank
        if (provedorPagamento instanceof ProvedorPagamentoPicPay) {
            valor += valor * 0.02;  // Adiciona 2% de juros para PicPay
        } else if (provedorPagamento instanceof ProvedorPagamentoNubank) {
            valor += valor * 0.03;  // Adiciona 3% de juros para Nubank
        }

        provedorPagamento.processarPagamento(valor);

        // Notificar os observadores sobre a confirmação da compra
        notificarCompraConfirmada();
    }

    public void cancelarCompra() {
        // Lógica para cancelar a compra

        // Notificar os observadores sobre o cancelamento da compra
        notificarCompraCancelada();
    }

    // Métodos para suportar o padrão Observer
    @Override
    public void adicionarObservador(ObservadorConfirmacaoCompra observador) {
        observadores.add(observador);
    }

    @Override
    public void removerObservador(ObservadorConfirmacaoCompra observador) {
        observadores.remove(observador);
    }

    @Override
    public void notificarCompraConfirmada() {
        for (ObservadorConfirmacaoCompra observador : observadores) {
            observador.updateCompraConfirmada();
        }
    }

    @Override
    public void notificarCompraCancelada() {
        for (ObservadorConfirmacaoCompra observador : observadores) {
            observador.updateCompraCancelada();
        }
    }

    public String getNomeLoja() {
        return nomeLoja;
    }
}