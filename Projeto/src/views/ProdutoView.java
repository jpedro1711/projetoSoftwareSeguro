package views;

import models.Produto;
import models.viewModels.AtualizarQtdViewModel;
import utils.TextEntryValidator;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class ProdutoView {
    private Scanner sc;
    private TextEntryValidator validator;

    public ProdutoView() {
        sc = new Scanner(System.in);
        validator = TextEntryValidator.getInstance();
    }
    public Produto cadastrarProduto() {
        try {
            System.out.print("Nome do produto: ");
            String nome = validator.validate(sc.nextLine());

            System.out.print("Valor de venda do produto: ");
            double valorVenda = sc.nextDouble();

            System.out.print("Custo unitário do produto: ");
            double custoUnitario = sc.nextDouble();

            System.out.print("Quantidade em estoque do produto: ");
            int qtdEstoque = sc.nextInt();

            System.out.print("Quantidade mínima em estoque do produto: ");
            int qtdMinimaEstoque = sc.nextInt();
            return new Produto(nome, valorVenda, custoUnitario, qtdEstoque, qtdMinimaEstoque);
        } catch (InputMismatchException e) {
            System.out.println("Entrada inválida!");
            return null;
        }
    }

    public void listar(List<Produto> produtos) {
        if (produtos.size() == 0) {
            System.out.println("Sem produtos cadastrados");
            return;
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
    }

    public AtualizarQtdViewModel atualizarQtdEmEstoque(List<Produto> produtos) {
        listar(produtos);
        System.out.print("Digite o id do produto: ");
        int produtoId = sc.nextInt();

        System.out.print("\nDigite a nova quantidade em estoque: ");
        int novaQtd = sc.nextInt();

        return new AtualizarQtdViewModel(produtoId, novaQtd);
    }

    public void showError(String msg) {
        System.out.println("Error: " + msg);
    }
}
