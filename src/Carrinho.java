import java.util.HashMap;
import java.util.Map;

public class Carrinho {
    private Map<Livro, Integer> itens = new HashMap<>();

    public void adicionarItem(Livro livro, int quantidade) {
        int estoqueAtual = itens.getOrDefault(livro, 0);
        itens.put(livro, estoqueAtual + quantidade);
    }

    public double calcularTotal() {
        double total = 0;
        for (Map.Entry<Livro, Integer> entry : itens.entrySet()) {
            Livro livro = entry.getKey();
            int quantidade = entry.getValue();
            total += livro.getPreco() * quantidade;
        }
        return total;
    }

    public Map<Livro, Integer> getItens() {
        return itens;
    }
}
