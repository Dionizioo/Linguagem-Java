// Classe do Agente Logístico
class AgenteLogistico implements ObservadorConfirmacaoCompra {
    @Override
    public void updateCompraConfirmada() {
        System.out.println("Agente logístico: Compra confirmada. Preparando para entrega.");
        // Lógica para preparar a entrega
    }

    @Override
    public void updateCompraCancelada() {
        System.out.println("Agente logístico: Compra cancelada. Procedendo à devolução.");
        // Lógica para devolução dos produtos ou cancelamento da ordem de transporte
    }
}