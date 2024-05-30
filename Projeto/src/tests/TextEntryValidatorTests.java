package tests;

import org.junit.Test;
import utils.TextEntryValidator;

import static org.junit.Assert.*;

public class TextEntryValidatorTests {
    @Test
    public void validator_Deve_Lancar_Excecao() {
        TextEntryValidator validator = TextEntryValidator.getInstance();

        String entry = "<script/>";

        assertThrows(SecurityException.class, () -> {
            validator.validate(entry);
        });
    }

    @Test
    public void validator_Deve_Validar_Entrada() {
        TextEntryValidator validator = TextEntryValidator.getInstance();

        String entry = "entry";

        var result = validator.validate(entry);

        assertEquals(result, entry);
    }
}
