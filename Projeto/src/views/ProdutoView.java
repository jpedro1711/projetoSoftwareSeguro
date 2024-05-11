package views;

import models.Produto;
import utils.TextEntryValidator;

import java.util.Scanner;

public class ProdutoView {
    private Scanner sc;
    private TextEntryValidator validator;

    public ProdutoView() {
        sc = new Scanner(System.in);
        validator = TextEntryValidator.getInstance();
    }
    public Produto cadastrarProduto() {
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
    }
}
