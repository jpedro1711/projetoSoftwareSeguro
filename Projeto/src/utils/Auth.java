package utils;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProvider;
import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProviderClientBuilder;
import com.amazonaws.services.cognitoidp.model.*;
import io.github.cdimascio.dotenv.Dotenv;

import java.util.HashMap;
import java.util.Map;

public class Auth {
    private final String USER_POOL_ID;
    private final String CLIENT_ID;
    private final AWSCognitoIdentityProvider cognitoClient;
    private static Auth instance = null;

    private Auth() {
        Dotenv dotenv = Dotenv.load();
        USER_POOL_ID = dotenv.get("AWS_USER_POOL_ID");
        CLIENT_ID = dotenv.get("AWS_CLIENT_ID");

        cognitoClient = AWSCognitoIdentityProviderClientBuilder.standard()
                .withRegion(Regions.US_EAST_2)
                .build();
    }

    public boolean register(String email, String password) {
        boolean success = false;
        try {
            SignUpRequest signUpRequest = new SignUpRequest()
                    .withClientId(CLIENT_ID)
                    .withUsername(email)
                    .withPassword(password);

            SignUpResult result = cognitoClient.signUp(signUpRequest);

            System.out.println("User registration successful. Status: " + result.getUserConfirmed());

            success = true;
        } catch (InvalidParameterException e) {
            System.out.println("E-mail inválido");
        } catch (InvalidPasswordException e) {
            System.out.println("Senha inválida");
        } catch (UsernameExistsException e) {
            System.out.println("Usuário já existe");
        } catch (Exception e) {
            System.out.println("Erro de cadastro");
        }

        return success;
    }

    public boolean login(String username, String password) {
        Map<String, String> authParams = new HashMap<>();
        authParams.put("USERNAME", username);
        authParams.put("PASSWORD", password);

        InitiateAuthRequest authRequest = new InitiateAuthRequest()
                .withAuthFlow(AuthFlowType.USER_PASSWORD_AUTH)
                .withAuthParameters(authParams)
                .withClientId(CLIENT_ID);

        InitiateAuthResult authResponse = cognitoClient.initiateAuth(authRequest);

        AuthenticationResultType authResult = authResponse.getAuthenticationResult();

        if (authResult == null) {
            throw new UnauthorizedException("Login Error");
        }

        return true;
    }

    public static Auth getInstance() {
        if (instance == null) {
            instance = new Auth();
        }
        return instance;
    }
}
