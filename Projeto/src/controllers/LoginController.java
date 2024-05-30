package controllers;

import exceptions.AuthException;
import models.viewModels.AuthViewModel;
import utils.Auth;
import views.AuthView;

public class LoginController {
    private AuthView authView;
    private Auth authHandler;

    public LoginController() throws AuthException {
        authView = new AuthView();
        authHandler = Auth.getInstance();

        AuthViewModel credentials = authView.getCredentials();

        boolean isAuthenticated = authHandler.login(credentials.getUsername(), credentials.getPassword());

        if (!isAuthenticated) {
            throw new AuthException("Usuário não autorizado");
        }
    }
}
