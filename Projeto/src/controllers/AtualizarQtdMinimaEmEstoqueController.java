package controllers;

import dao.ProdutoDAO;
import exceptions.ProdutoException;
import models.Produto;
import models.viewModels.AtualizarQtdViewModel;
import views.ProdutoView;

import java.util.List;

public class AtualizarQtdMinimaEmEstoqueController{
    private ProdutoView view;
    private ProdutoDAO dao;

    public AtualizarQtdMinimaEmEstoqueController() {
        view = new ProdutoView();
        dao = new ProdutoDAO();
        List<Produto> produtos = dao.listar();
        AtualizarQtdViewModel viewModel = view.atualizarQtdMinimaEmEstoque(produtos);
        try {
            dao.atualizarQuantidadeMinimaEstoque(viewModel.getProdutoId(), viewModel.getNovaQuantidade());
        } catch (ProdutoException ex) {
            view.showError("Produto não encontrado com o id inserido");
        } catch (Exception ex) {
            view.showError("Error");
        }
    }
}
