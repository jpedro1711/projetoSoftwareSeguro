package views;

import controllers.CadastrarVendaController;
import controllers.ListarProdutosController;
import models.ItemVenda;
import models.Produto;
import models.viewModels.CadastrarVendaViewModel;
import utils.TextEntryValidator;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class VendaView {
    private Scanner sc;
    private TextEntryValidator validator;

    public VendaView() {
        sc = new Scanner(System.in);
        validator = TextEntryValidator.getInstance();
    }

    public List<CadastrarVendaViewModel> cadastrarVenda(List<Produto> produtos) {
        List<CadastrarVendaViewModel> vendaViewModels = new ArrayList<>();

        if (produtos.size() == 0) {
            System.out.println("Sem produtos cadastrados");
            return vendaViewModels;
        }

        for (Produto prod:produtos) {
            System.out.println(
                    "ID do produto: " + prod.getProdutoId() + "\n" +
                            "Produto: " + prod.getNomeProduto() + "\n" +
                            "Valor: " + prod.getValorVenda() + "\n" +
                            "Custo Unitário: " + prod.getCustoUnitario() + "\n" +
                            "Qtd. em estoque: " + prod.getQuantidadeEstoque() + "\n" +
                            "Qtd. mínima de estoque: " + prod.getQuantidadeMinimaEstoque() +
                            "\n==============================================================="
            );
        }

        int id = -1;

        while (id != 0) {
            System.out.println("Digite o ID do produto para adicioná-lo a venda ou digite (0) para sair: ");
            id = sc.nextInt();

            if (id != 0) {
                System.out.println("Quantidade: ");
                int qtd = sc.nextInt();

                if (qtd <= 0) {
                    System.out.println("Quantidade deve ser maior que 0!");
                }
                else {
                    vendaViewModels.add(new CadastrarVendaViewModel(id, qtd));
                }
            }
        }

        return vendaViewModels;
    }

    public void valorTotalVendido(double valor) {
        System.out.println("Valor total vendido: " + valor);
    }
}
