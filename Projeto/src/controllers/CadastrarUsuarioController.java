package controllers;

import exceptions.AuthException;
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

        try {
            boolean isRegistered = authHandler.register(credentials.getUsername(), credentials.getPassword());

            if (!isRegistered) {
                authView.showError("Erro ao cadastrar usuario");
            } else {
                authView.showAlert("Usuário cadastrado com sucesso");
            }
        } catch (AuthException ex) {
            authView.showError(ex.getMessage());
        }

    }


}
