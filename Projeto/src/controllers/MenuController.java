package controllers;

import views.MenuView;

public class MenuController {
    private MenuView menuView;
    private CadastrarProdutoController cadastrarProdutoController;
    private ListarProdutosController listarProdutosController;


    public MenuController() {
        menuView = new MenuView();


        int op = -1;

        while (op != 0) {
            op = menuView.mostrarMenu();

            if (op == 1){
                cadastrarProdutoController = new CadastrarProdutoController();
            }
            else if (op == 2) {
                listarProdutosController = new ListarProdutosController();
            }
            else if (op == 0) {
                break;
            }
            else {
                menuView.opcaoInvalida();
            }
        }
    }
}
