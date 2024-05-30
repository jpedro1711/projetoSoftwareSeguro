package tests;

import exceptions.AuthException;
import org.junit.Test;
import utils.Auth;

import static org.junit.Assert.*;

public class AuthTests {
    @Test
    public void login_Com_Credenciais_Validas_Deve_Retornar_True() {
        Auth auth = Auth.getInstance();

        String usuario = "vendedor@gmail.com";

        String senha = "Senha@Segura123";

        boolean resultado = auth.login(usuario, senha);

        assertTrue(resultado);
    }

    @Test
    public void login_Com_Credenciais_Invalidas_Deve_Retornar_false() {
        Auth auth = Auth.getInstance();

        String usuario = "invalido@gmail.com";

        String senha = "Senha@Invalida123";

        boolean resultado = auth.login(usuario, senha);

        assertFalse(resultado);
    }

    @Test
    public void cadastrar_Usuario_Com_Username_Ja_Existente_Deve_Retornar_False() {
        Auth auth = Auth.getInstance();

        String username = "vendedor@gmail.com";

        String senha = "Senha@Nova123";

        assertThrows(AuthException.class, () -> {
            auth.register(username, senha);
        });
    }
}
