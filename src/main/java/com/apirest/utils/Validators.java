package com.apirest.utils;

import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class Validators {

    public ValidatorResponse validateTextContent(@NotNull String text) {

        boolean initialLength = text.isEmpty() || text.isBlank();

        if (initialLength) {
            return new ValidatorResponse(false, "El texto no puede estar vacío");
        }
        return new ValidatorResponse(true, "");
    }

    public ValidatorResponse validateGetNumberInitial(String line) {

        Pattern pattern = Pattern.compile(RegularExpression.mandatoryFormatInitialText);
        Matcher matcher = pattern.matcher(line);

        if (!matcher.matches()) {
            return new ValidatorResponse(false, "El primer valor del grupo no es un número");
        }

        return new ValidatorResponse(true, Integer.parseInt(matcher.group(1)));
    }

    public ValidatorResponse validateFormatInitialText(String line) {

        if (!Pattern.matches(RegularExpression.mandatoryFormatInitialText, line)) {
            return new ValidatorResponse(false, "Formato de texto inválido");
        }
        return new ValidatorResponse(true, "");
    }
}
