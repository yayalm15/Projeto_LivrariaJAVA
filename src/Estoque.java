import java.util.HashMap;
import java.util.Map;

public class Estoque {
    private Map<Integer, Livro> catalogo = new HashMap<>();
    private Map<Integer, Integer> estoque = new HashMap<>();

    public Estoque() {
        inicializarCatalogo();
        inicializarEstoque();
    }

    private void inicializarCatalogo() {
        catalogo.put(1, new Livro("Jogos vorazes", "Fantasia juvenil", 78.49));
        catalogo.put(2, new Livro("É assim que acaba", "Romance", 50.97));
        catalogo.put(3, new Livro("A culpa é das estrelas", "Romance dramático", 45.90));
        catalogo.put(4, new Livro("Percy Jackson: O ladrão de raios", "Ficção e aventura juvenil", 69.99));
        catalogo.put(5, new Livro("Harry Potter e a Pedra Filosofal", "Literatura infantojuvenil", 50.92));
        catalogo.put(6, new Livro("It - A Coisa", "Terror", 82.90));
        catalogo.put(7, new Livro("Os olhos do ceifador", "Suspense terror", 81.99));
        catalogo.put(8, new Livro("Verity", "Romance psicológico", 49.90));
        catalogo.put(9, new Livro("Diário de um banana: Um dia de Cão", "Comédia Infantil", 46.12));
    }

    private void inicializarEstoque() {
        for (int i = 1; i <= catalogo.size(); i++) {
            estoque.put(i, 50);
        }
    }

    public Map<Integer, Livro> getCatalogo() {
        return catalogo;
    }

    public Map<Integer, Integer> getEstoque() {
        return estoque;
    }

    public boolean verificarDisponibilidade(int numeroLivro, int quantidade) {
        if (estoque.containsKey(numeroLivro) && estoque.get(numeroLivro) >= quantidade) {
            return true;
        }
        return false;
    }

    public void removerLivro(int numeroLivro, int quantidade) {
        if (verificarDisponibilidade(numeroLivro, quantidade)) {
            int estoqueAtual = estoque.get(numeroLivro);
            estoque.put(numeroLivro, estoqueAtual - quantidade);
        }
    }
}