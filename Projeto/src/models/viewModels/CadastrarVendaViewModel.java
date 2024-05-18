package models.viewModels;

public class CadastrarVendaViewModel {
    private int produtoId;
    private int qtd;

    public CadastrarVendaViewModel(int produtoId, int qtd) {
        this.produtoId = produtoId;
        this.qtd = qtd;
    }

    public int getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(int produtoId) {
        this.produtoId = produtoId;
    }

    public int getQtd() {
        return qtd;
    }

    public void setQtd(int qtd) {
        this.qtd = qtd;
    }
}
