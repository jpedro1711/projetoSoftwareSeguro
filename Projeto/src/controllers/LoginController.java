package controllers;

import com.amazonaws.services.cognitoidp.model.UnauthorizedException;
import models.viewModels.AuthViewModel;
import utils.Auth;
import views.AuthView;

public class LoginController {
    private AuthView authView;
    private Auth authHandler;

    public LoginController() {
        authView = new AuthView();
        authHandler = Auth.getInstance();

        AuthViewModel credentials = authView.getCredentials();

        authHandler.login(credentials.getUsername(), credentials.getPassword());

    }
}
