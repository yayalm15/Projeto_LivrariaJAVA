
public class PagamentoCartao implements Pagamento {
    @Override
    public double calcularValorFinal(double totalCompra) {
        return totalCompra * 0.95;
    }
}