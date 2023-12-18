import java.text.DecimalFormat;
import java.util.Map;
import java.util.Scanner;

public class Livraria {
    public static void main(String[] args) {
        System.out.println("BEM VINDO A LIVRARIA APRENDA A SONHAR");

        Scanner scanner = new Scanner(System.in);

        System.out.print("Informe seu nome: ");
        String nomeCliente = scanner.nextLine();

        System.out.print("Informe seu e-mail: ");
        String emailCliente = scanner.nextLine();

        Cliente cliente = new Cliente(nomeCliente, emailCliente);

        Estoque estoque = new Estoque();

        Carrinho carrinho = new Carrinho();

        do {
            System.out.println("\nLivros disponíveis:");
            for (Map.Entry<Integer, Integer> entry : estoque.getEstoque().entrySet()) {
                int numeroLivro = entry.getKey();
                Livro livro = estoque.getCatalogo().get(numeroLivro);
                int quantidadeDisponivel = entry.getValue();
                System.out.println(numeroLivro + ". " + livro.getTitulo() + " - " + livro.getGenero() +
                        " - R$" + livro.getPreco() + " - Quantidade disponível: " + quantidadeDisponivel);
            }

            System.out.print("\nDigite o número do livro que deseja adicionar ao carrinho: ");
            int numeroLivroEscolhido = scanner.nextInt();
            scanner.nextLine();

            if (estoque.verificarDisponibilidade(numeroLivroEscolhido, 1)) {
                carrinho.adicionarItem(estoque.getCatalogo().get(numeroLivroEscolhido), 1);
                estoque.removerLivro(numeroLivroEscolhido, 1);
                System.out.println("Livro adicionado ao carrinho!");
            } else {
                System.out.println("Livro não disponível no estoque ou número inválido. Escolha outro.");
            }

            System.out.print("\nDeseja adicionar mais livros ao carrinho? (S/N): ");
            String continuar = scanner.nextLine().toUpperCase();

            if (!continuar.equals("S")) {
                break;
            }
        } while (true);

        double totalCompra = carrinho.calcularTotal();
        System.out.println("\nTotal da compra para " + cliente.getNome() + ": R$" + formatarValor(totalCompra));

        Pagamento formaPagamento = escolherFormaPagamento(scanner);

        double valorFinal = formaPagamento.calcularValorFinal(totalCompra);

        System.out.println("\nLivros selecionados:");
        for (Map.Entry<Livro, Integer> entry : carrinho.getItens().entrySet()) {
            Livro livro = entry.getKey();
            int quantidade = entry.getValue();
            System.out.println(livro.getTitulo() + " - " + quantidade + " unidade(s)");
        }

        System.out.println("\nValor final: R$" + formatarValor(valorFinal));

        if (formaPagamento instanceof PagamentoParcelado) {
            double valorParcela = valorFinal / 2;
            double juros = valorFinal * 0.05;

            System.out.println("A compra será parcelada em 2 vezes:");
            System.out.println("1ª parcela: R$" + formatarValor(valorParcela));
            System.out.println("2ª parcela: R$" + formatarValor(valorParcela));
            System.out.println("Acréscimo de juros (5%): R$" + formatarValor(juros));
        }

        System.out.println("Compra realizada com sucesso! Obrigado por comprar na Livraria Aprenda a Sonhar. Volte sempre.");

        scanner.close();
    }

    private static Pagamento escolherFormaPagamento(Scanner scanner) {
        System.out.println("\nEscolha a forma de pagamento:");
        System.out.println("1. À vista (10% de desconto)");
        System.out.println("2. Cartão de crédito (5% de desconto)");
        System.out.println("3. Parcelado em 2 vezes (5% de juros)");
        System.out.println("4. Boleto");

        System.out.print("Digite o número correspondente à forma de pagamento: ");
        int opcaoPagamento = scanner.nextInt();
        scanner.nextLine();

        switch (opcaoPagamento) {
            case 1:
                return new PagamentoAVista();
            case 2:
                return new PagamentoCartao();
            case 3:
                return new PagamentoParcelado();
            default:
                throw new IllegalArgumentException("Opção de pagamento inválida");
        }
    }

    private static String formatarValor(double valor) {
        DecimalFormat decimalFormat = new DecimalFormat("#0.00");
        return decimalFormat.format(valor);
    }
}