public class PagamentoParcelado implements Pagamento {
    @Override
    public double calcularValorFinal(double totalCompra) {
        return totalCompra * 1.05;
    }
}
