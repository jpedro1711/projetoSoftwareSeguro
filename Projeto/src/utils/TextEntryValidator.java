package utils;

import java.text.Normalizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextEntryValidator {
    private static TextEntryValidator instance;
    private TextEntryValidator() {
        instance = null;
    }
    public String validate(String value)
    {

        value = Normalizer.normalize(value, Normalizer.Form.NFKC); // normalizar string, removendo acentos por exemplo

        Pattern pattern = Pattern.compile("[<>]");
        Matcher matcher = pattern.matcher(value);

        if (matcher.find()) {
            throw new SecurityException("Invalid entry");
        }
        return value;
    }

    public static TextEntryValidator getInstance() {
        if (instance == null) {
            return new TextEntryValidator();
        }
        return instance;
    }
}
