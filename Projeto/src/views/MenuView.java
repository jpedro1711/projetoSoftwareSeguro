package views;

import java.util.Scanner;

public class MenuView {
    private Scanner sc;

    public MenuView(){

        sc = new Scanner(System.in);
    }

    public int mostrarMenu() {
        System.out.println("Selecione uma opção: " +
                "\n(0) Sair  | (1) Cadastrar produto | (2) Listar Produtos");

        return sc.nextInt();

    }

    public void opcaoInvalida(){
        System.out.println("Opção inválida");
    }
}
