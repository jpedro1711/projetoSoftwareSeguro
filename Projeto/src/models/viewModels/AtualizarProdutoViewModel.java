package models.viewModels;

import models.Produto;

public class AtualizarProdutoViewModel {
    private int produtoId;
    private Produto novoProduto;

    public AtualizarProdutoViewModel(int produtoId, Produto novoProduto) {
        this.produtoId = produtoId;
        this.novoProduto = novoProduto;
    }

    public int getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(int produtoId) {
        this.produtoId = produtoId;
    }

    public Produto getNovoProduto() {
        return novoProduto;
    }

    public void setNovoProduto(Produto novoProduto) {
        this.novoProduto = novoProduto;
    }
}
