public class PagamentoAVista implements Pagamento {
    @Override
    public double calcularValorFinal(double totalCompra) {
        return totalCompra * 0.9;
    }
}
