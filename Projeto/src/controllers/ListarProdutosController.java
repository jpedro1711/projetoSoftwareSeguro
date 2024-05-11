package controllers;

import dao.ProdutoDAO;
import models.Produto;
import views.ProdutoView;

import java.util.List;

public class ListarProdutosController {
    private ProdutoView produtoView;
    private ProdutoDAO dao;

    public ListarProdutosController() {
        produtoView = new ProdutoView();
        dao = new ProdutoDAO();

        List<Produto> produtos = dao.listar();

        produtoView.listar(produtos);
    }
}
