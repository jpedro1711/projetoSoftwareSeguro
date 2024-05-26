package controllers;

import com.amazonaws.services.cognitoidp.model.NotAuthorizedException;
import com.amazonaws.services.cognitoidp.model.UnauthorizedException;
import views.MenuView;

public class MenuController {
    private MenuView menuView;
    private CadastrarProdutoController cadastrarProdutoController;
    private ListarProdutosController listarProdutosController;
    private AtualizarQtdEmEstoqueController atualizarQtdEmEstoqueControler;
    private CadastrarVendaController cadastrarVendaController;
    private CadastrarUsuarioController cadastrarUsuarioController;
    private LoginController loginController;
    private AtualizarQtdMinimaEmEstoqueController atualizarQtdMinimaEmEstoqueController;
    private ListarProdutosAbaixoQtdMinimaEmEstoqueController listarProdutosAbaixoQtdMinimaEmEstoqueController;
    private BuscarValorTotalVendidoController buscarValorTotalVendidoController;

    public MenuController() {
        menuView = new MenuView();

        int op = -1;

        try {
            loginController = new LoginController();
        } catch (NotAuthorizedException e) {
            System.out.println("Credenciais inválidas");
            op = 0;
        } catch (Exception e) {
            System.out.println("Erro de login");
            op = 0;
        }

        while (op != 0) {
            op = menuView.mostrarMenu();

            if (op == 1){
                cadastrarProdutoController = new CadastrarProdutoController();
            }
            else if (op == 2) {
                listarProdutosController = new ListarProdutosController();
            }
            else if (op == 3) {
                atualizarQtdEmEstoqueControler = new AtualizarQtdEmEstoqueController();
            }
            else if (op == 4) {
                cadastrarVendaController  = new CadastrarVendaController();
            }
            else if (op == 5) {
                cadastrarUsuarioController = new CadastrarUsuarioController();
            }
            else if (op ==6) {
                atualizarQtdMinimaEmEstoqueController = new AtualizarQtdMinimaEmEstoqueController();
            }
            else if (op == 7) {
                listarProdutosAbaixoQtdMinimaEmEstoqueController = new ListarProdutosAbaixoQtdMinimaEmEstoqueController();
            }
            else if (op == 8) {
                buscarValorTotalVendidoController = new BuscarValorTotalVendidoController();
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
