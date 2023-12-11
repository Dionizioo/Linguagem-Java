// Modificação na classe LojaVirtual para suportar observadores
interface ObservavelConfirmacaoCompra {
    void adicionarObservador(ObservadorConfirmacaoCompra observador);
    void removerObservador(ObservadorConfirmacaoCompra observador);
    void notificarCompraConfirmada();
    void notificarCompraCancelada();
}