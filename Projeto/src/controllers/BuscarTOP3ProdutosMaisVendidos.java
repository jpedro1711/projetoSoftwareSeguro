package controllers;

import dao.ProdutoDAO;
import dao.VendaDAO;
import models.Produto;
import views.ProdutoView;
import views.VendaView;

import java.util.List;

public class BuscarTOP3ProdutosMaisVendidos {
    private ProdutoDAO dao;
    private ProdutoView view;
    public BuscarTOP3ProdutosMaisVendidos() {
        dao = new ProdutoDAO();
        view = new ProdutoView();

        List<Produto> produtos = dao.getTop3MaisVendidos();

        view.listar(produtos);
    }
}
