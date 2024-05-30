package controllers;

import dao.VendaDAO;
import exceptions.VendaException;
import views.VendaView;

public class BuscarValorTotalVendidoController {
    private VendaDAO dao;
    private VendaView view;
    public BuscarValorTotalVendidoController() {
        dao = new VendaDAO();
        view = new VendaView();


        try {
            double valor = dao.getValorTotalVendido();

            view.valorTotalVendido(valor);
        } catch (VendaException ex) {
            view.showError(ex.getMessage());
        }


    }
}
