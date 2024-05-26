package controllers;

import dao.VendaDAO;
import views.VendaView;

public class BuscarValorTotalVendidoController {
    private VendaDAO dao;
    private VendaView view;
    public BuscarValorTotalVendidoController() {
        dao = new VendaDAO();
        view = new VendaView();

        double valor = dao.getValorTotalVendido();

        view.valorTotalVendido(valor);
    }
}
