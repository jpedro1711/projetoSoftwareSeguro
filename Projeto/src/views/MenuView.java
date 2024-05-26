package views;

import java.util.Scanner;

public class MenuView {
    private Scanner sc;

    public MenuView(){

        sc = new Scanner(System.in);
    }

    public int mostrarMenu() {
        System.out.println("Selecione uma opção: " +
                "\n(0) Sair                                     |                      (1) Cadastrar produto                 | (2) Listar Produtos       " +
                "\n(3) Atualizar quantidade em estoque          |                      (4) Cadastrar venda                   | (5) Cadastrar novo usuário" +
                "\n(6) Atualizar quantidade mínima em estoque   |                      (7) Listar produtos com quantidade em estoque abaixo da mínima  " +
                "\n(8) Valor total vendido"
                );

        return sc.nextInt();

    }

    public void opcaoInvalida(){
        System.out.println("Opção inválida");
    }
}
