package controllers;

import dao.ProdutoDAO;
import exceptions.ProdutoException;
import models.Produto;
import models.viewModels.AtualizarProdutoViewModel;
import views.ProdutoView;

import java.util.List;

public class AtualizarProdutoController {
    private ProdutoView view;
    private ProdutoDAO dao;

    public AtualizarProdutoController() {
        view = new ProdutoView();
        dao = new ProdutoDAO();
        List<Produto> produtos = dao.listar();
        AtualizarProdutoViewModel viewModel = view.atualizarProduto(produtos);

        try {
            Produto produto = dao.atualizarProduto(viewModel.getProdutoId(), viewModel.getNovoProduto());
            System.out.println(produto.getProdutoId() + " - " + produto.getNomeProduto());
        } catch (ProdutoException ex) {
            view.showError("Produto não encontrado com o id inserido");
        } catch (Exception ex) {
            view.showError("Error");
        }
    }
}
