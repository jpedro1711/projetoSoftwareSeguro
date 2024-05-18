package controllers;

import dao.ProdutoDAO;
import dao.VendaDAO;
import exceptions.ProdutoException;
import models.ItemVenda;
import models.Produto;
import models.Venda;
import models.viewModels.CadastrarVendaViewModel;
import views.VendaView;

import java.util.ArrayList;
import java.util.List;

public class CadastrarVendaController {
    private VendaView vendaView;
    private VendaDAO dao;
    private ProdutoDAO produtoDAO;

    public CadastrarVendaController() {
        vendaView = new VendaView();
        dao = new VendaDAO();
        produtoDAO = new ProdutoDAO();

        List<CadastrarVendaViewModel> vendaViewModels = vendaView.cadastrarVenda(produtoDAO.listar());

        Venda venda = new Venda();
        List<ItemVenda> items = new ArrayList<>();

        for (CadastrarVendaViewModel viewModel : vendaViewModels) {
            Produto produto = produtoDAO.buscarProdutoPorId(viewModel.getProdutoId());

            if (produto == null) {
                System.out.println("Falha ao adicionar produto com ID " + viewModel.getProdutoId() + " à venda");
                return;
            } else {
                int quantidade = viewModel.getQtd();
                ItemVenda itemVenda = new ItemVenda(venda, produto, quantidade);
                if (produto.getQuantidadeEstoque() < quantidade) {
                    System.out.println("Erro: quantidade em estoque insuficiente");
                    return;
                }
                try {
                    produtoDAO.atualizarQuantidadeEstoque(viewModel.getProdutoId(), produto.getQuantidadeEstoque() - itemVenda.getQuantidade());
                } catch (ProdutoException e) {
                    System.out.println("Erro ao atualizar QTD em estoque do produto");
                    return;
                }

                items.add(itemVenda);

            }
        }

        venda.setProdutos(items);
        dao.cadastrarVenda(venda);

        System.out.println("Venda cadastrada com sucesso!");
    }
}
