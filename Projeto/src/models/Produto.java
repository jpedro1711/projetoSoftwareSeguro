package models;

public class Produto {
    private int produtoId;
    private String nomeProduto;
    private double valorVenda;
    private double custoUnitario;
    private int quantidadeEstoque;
    private int quantidadeMinimaEstoque;

    public Produto(int produtoId, String nomeProduto, double valorVenda, double custoUnitario, int quantidadeEstoque, int quantidadeMinimaEstoque) {
        this.produtoId = produtoId;
        this.nomeProduto = nomeProduto;
        this.valorVenda = valorVenda;
        this.custoUnitario = custoUnitario;
        this.quantidadeEstoque = quantidadeEstoque;
        this.quantidadeMinimaEstoque = quantidadeMinimaEstoque;
    }

    public Produto(String nomeProduto, double valorVenda, double custoUnitario, int quantidadeEstoque, int quantidadeMinimaEstoque) {
        this.nomeProduto = nomeProduto;
        this.valorVenda = valorVenda;
        this.custoUnitario = custoUnitario;
        this.quantidadeEstoque = quantidadeEstoque;
        this.quantidadeMinimaEstoque = quantidadeMinimaEstoque;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public double getValorVenda() {
        return valorVenda;
    }

    public void setValorVenda(double valorVenda) {
        this.valorVenda = valorVenda;
    }

    public double getCustoUnitario() {
        return custoUnitario;
    }

    public void setCustoUnitario(double custoUnitario) {
        this.custoUnitario = custoUnitario;
    }

    public int getQuantidadeEstoque() {
        return quantidadeEstoque;
    }

    public void setQuantidadeEstoque(int quantidadeEstoque) {
        this.quantidadeEstoque = quantidadeEstoque;
    }

    public int getQuantidadeMinimaEstoque() {
        return quantidadeMinimaEstoque;
    }

    public void setQuantidadeMinimaEstoque(int quantidadeMinimaEstoque) {
        this.quantidadeMinimaEstoque = quantidadeMinimaEstoque;
    }

    public int getProdutoId() {
        return produtoId;
    }
}
