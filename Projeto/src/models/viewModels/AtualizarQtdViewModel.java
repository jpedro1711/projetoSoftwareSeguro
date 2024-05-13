package models.viewModels;

public class AtualizarQtdViewModel {
    private int produtoId;
    private int novaQuantidade;

    public AtualizarQtdViewModel(int produtoId, int novaQuantidade) {
        this.produtoId = produtoId;
        this.novaQuantidade = novaQuantidade;
    }

    public int getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(int produtoId) {
        this.produtoId = produtoId;
    }

    public int getNovaQuantidade() {
        return novaQuantidade;
    }

    public void setNovaQuantidade(int novaQuantidade) {
        this.novaQuantidade = novaQuantidade;
    }
}
