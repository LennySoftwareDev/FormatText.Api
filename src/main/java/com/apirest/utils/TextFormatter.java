package com.apirest.utils;

import com.apirest.application.model.response.TextResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public final class TextFormatter {
    @Autowired
    Validators validators;
    public List<TextResponseDto> changeFormatText(String textToProcess) {

        List<TextResponseDto> newTextFormat = new ArrayList<>();

        String character = "\\";

        String[] text = textToProcess.split("\n");

        for (String line : text) {

            validators.validateFormatInitialText(line);

            ValidatorResponse validateNumberInitialByEachLineOfText = validators.validateGetNumberInitial(line);

            String changeFormatText =
                    line.replaceAll(RegularExpression.mandatoryFormatFinalText, "").toLowerCase();

            String[] numberOfWords = line.split(RegularExpression.splitWhiteSpaces);

            boolean isEqualNumberAndNumberWords =
                    (Integer) validateNumberInitialByEachLineOfText.result() == numberOfWords.length;

            String createNewText =
                    changeFormatText.concat(character).concat(String.valueOf(isEqualNumberAndNumberWords));

            newTextFormat.add(new TextResponseDto(createNewText));
        }

        return newTextFormat;
    }
}
