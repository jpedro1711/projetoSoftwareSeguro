package utils;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProvider;
import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProviderClientBuilder;
import com.amazonaws.services.cognitoidp.model.*;

import java.util.HashMap;
import java.util.Map;

public class Auth {
    private final String USER_POOL_ID = "us-east-2_iQXkdWdWe";
    private final String CLIENT_ID = "46a4vckr35ejmle97d800ibqro";
    private final AWSCognitoIdentityProvider cognitoClient;
    private static Auth instance = null;
    private Auth()
    {
        cognitoClient = AWSCognitoIdentityProviderClientBuilder.standard()
                .withRegion(Regions.US_EAST_2)
                .build();
    }

    public boolean register(String email, String password)
    {
        boolean sucess = false;
        try {
            SignUpRequest signUpRequest = new SignUpRequest()
                    .withClientId(CLIENT_ID)
                    .withUsername(email)
                    .withPassword(password);

            SignUpResult result = cognitoClient.signUp(signUpRequest);

            System.out.println("User registration sucessfull. Status: " + result.getUserConfirmed());

            sucess = true;
        } catch (InvalidParameterException e) {
            System.out.println("E-mail inválido");
        } catch (InvalidPasswordException e) {
            System.out.println("Senha inválida");
        } catch (UsernameExistsException e) {
            System.out.println("Usuário já existe");
        } catch (Exception e) {
            System.out.println("Erro de cadastro");
        }


        return sucess;
    }

    public boolean login(String username, String password)
    {
        Map<String, String> authParams = new HashMap();
        authParams.put("USERNAME", username);
        authParams.put("PASSWORD", password);

        InitiateAuthRequest authRequest = new InitiateAuthRequest()
                .withAuthFlow(AuthFlowType.USER_PASSWORD_AUTH)
                .withAuthParameters(authParams)
                .withClientId(CLIENT_ID);

        InitiateAuthResult authResponse = cognitoClient.initiateAuth(authRequest);

        AuthenticationResultType authResult = authResponse.getAuthenticationResult();

        if (authResult == null)
        {
            throw new UnauthorizedException("Login Error");
        }

        return true;
    }

    public static Auth getInstance() {
        if (instance == null) return new Auth();

        return instance;
    }
}
