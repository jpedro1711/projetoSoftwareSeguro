package controllers;

import dao.ProdutoDAO;
import models.Produto;
import views.ProdutoView;

public class CadastrarProdutoController {
    private ProdutoView produtoView;
    private ProdutoDAO dao;

    public CadastrarProdutoController() {
        produtoView = new ProdutoView();
        dao = new ProdutoDAO();

        Produto produto = produtoView.cadastrarProduto();

        dao.cadastrar(produto);
    }
}
