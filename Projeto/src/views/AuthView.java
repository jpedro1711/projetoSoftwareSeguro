package views;

import models.viewModels.AuthViewModel;

import java.util.Scanner;

public class AuthView {
    private Scanner sc;

    public AuthView() {
        sc = new Scanner(System.in);
    }

    public AuthViewModel getCredentials() {
        System.out.println("Insira seu e-mail e senha para se autenticar: ");

        System.out.println("Digite o e-mail: ");
        String email = sc.nextLine();

        System.out.println("Digite a senha: ");
        String senha = sc.nextLine();

        return new AuthViewModel(email, senha);
    }
}
