package models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Venda {
    private int vendaId;
    private Date dataVenda;
    private double totalVenda;
    private List<ItemVenda> produtos;

    public Venda() {
        this.dataVenda = new Date();
    }

    public void calcularTotal(){
        for(ItemVenda i : this.produtos) {
            totalVenda += i.getQuantidade() * i.getProduto().getValorVenda();
        }
    }

    public Date getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(Date dataVenda) {
        this.dataVenda = dataVenda;
    }

    public List<ItemVenda> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<ItemVenda> produtos) {
        this.produtos = produtos;
    }

    public void addProduto(ItemVenda produto) {
        this.produtos.add(produto);
    }

    public void removeProduto(Produto produto) {
        this.produtos.remove(produto);
    }

    public double getTotalVenda() {
        return totalVenda;
    }

    public void setTotalVenda(double totalVenda) {
        this.totalVenda = totalVenda;
    }

    public int getVendaId() {
        return vendaId;
    }

    public void setVendaId(int vendaId) {
        this.vendaId = vendaId;
    }
}
