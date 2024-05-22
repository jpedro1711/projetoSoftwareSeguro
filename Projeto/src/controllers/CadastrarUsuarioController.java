package controllers;

import models.viewModels.AuthViewModel;
import utils.Auth;
import views.AuthView;

public class CadastrarUsuarioController {
    private AuthView authView;
    private Auth authHandler;

    public CadastrarUsuarioController() {
        authView = new AuthView();
        authHandler = Auth.getInstance();

        AuthViewModel credentials = authView.getCredentials();

        boolean isRegistered = authHandler.register(credentials.getUsername(), credentials.getPassword());

        if (!isRegistered) {
            System.out.println("Erro ao cadastrar usuario");
        } else {
            System.out.println("Usuário cadastrado com sucesso");
        }
    }


}
