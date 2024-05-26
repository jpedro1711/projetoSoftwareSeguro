package controllers;

import dao.ProdutoDAO;
import models.Produto;
import views.ProdutoView;

import java.util.List;

public class ListarProdutosAbaixoQtdMinimaEmEstoqueController {
    private ProdutoView produtoView;
    private ProdutoDAO dao;

    public ListarProdutosAbaixoQtdMinimaEmEstoqueController() {
        produtoView = new ProdutoView();
        dao = new ProdutoDAO();

        List<Produto> produtos = dao.listarAbaixoEstoqueMinimo();

        produtoView.listar(produtos);
    }
}
