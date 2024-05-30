package views;

import models.viewModels.AuthViewModel;
import utils.TextEntryValidator;

import java.util.Scanner;

public class AuthView {
    private Scanner sc;
    private TextEntryValidator validator;

    public AuthView() {
        sc = new Scanner(System.in);
        validator = TextEntryValidator.getInstance();
    }

    public AuthViewModel getCredentials() {
        System.out.println("Insira seu e-mail e senha para se autenticar: ");

        System.out.println("Digite o e-mail: ");
        String email = validator.validate(sc.nextLine());

        System.out.println("Digite a senha: ");
        String senha = validator.validate(sc.nextLine());

        return new AuthViewModel(email, senha);
    }

    public void showError(String msg) {
        System.out.println("Error: " + msg);
    }

    public void showAlert(String msg) {
        System.out.println(msg);
    }
}
