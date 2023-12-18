public abstract class Produto implements ItemVendavel {
    protected String titulo;
    protected String genero;
    protected double preco;

    public Produto(String titulo, String genero, double preco) {
        this.titulo = titulo;
        this.genero = genero;
        this.preco = preco;
    }

    @Override
    public String getTitulo() {
        return titulo;
    }

    @Override
    public String getGenero() {
        return genero;
    }

    @Override
    public double getPreco() {
        return preco;
    }
}

