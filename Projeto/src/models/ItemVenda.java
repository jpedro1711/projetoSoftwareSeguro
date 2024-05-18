package models;

public class ItemVenda {
    private Venda venda;
    private Produto produto;
    private int quantidade;

    public ItemVenda(Venda venda, Produto produto, int quantidade) {
        this.venda = venda;
        this.produto = produto;
        this.quantidade = quantidade;
        venda.setTotalVenda(produto.getValorVenda() * quantidade);
    }

    public Venda getVenda() {
        return venda;
    }

    public void setVenda(Venda venda) {
        this.venda = venda;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}
