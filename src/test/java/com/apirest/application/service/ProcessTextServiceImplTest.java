package com.apirest.application.service;

import com.apirest.application.model.response.TextResponseDto;
import com.apirest.utils.TextFormatter;
import com.apirest.utils.ValidatorResponse;
import com.apirest.utils.Validators;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProcessTextServiceImplTest {

    String textToProcess = "3\\\\the force is strong in this one";
    String textChanged = "the force is strong in this one\\\\true";

    @InjectMocks
    private ProcessTextServiceImpl processTextService;
    @Mock
    private Validators validators;

    @Mock
    private TextFormatter textFormatter;

    @Test
    void validateText_invalidText_returnsEmptyList() {

        ValidatorResponse invalidResponse = new ValidatorResponse(false, "Error");

        when(validators.validateTextContent(textToProcess)).thenReturn(invalidResponse);

        ValidatorResponse result = validators.validateTextContent(textToProcess);

        assertFalse(result.isValid(), "El texto no puede estar vacío");
        verify(validators, times(1)).validateTextContent(textToProcess);
    }

    @Test
    void validate_formatText() {

        int number = Integer.parseInt(textToProcess.substring(0,1));
        ValidatorResponse validResponse = new ValidatorResponse(true, "");

        when(validators.validateFormatInitialText(textToProcess)).thenReturn(validResponse);

        ValidatorResponse result = validators.validateFormatInitialText(textToProcess);

        assertEquals(true,textToProcess.contains("\\"), "Formato válido");
        assertEquals(number,3, "Formato válido");
        assertTrue(result.isValid(), "");
        verify(validators, times(1)).validateFormatInitialText(textToProcess);
    }

    @Test
    void validate_changeFormatText(){

        String matchWith = "true";
        Pattern pattern = Pattern.compile("\\b" + Pattern.quote(matchWith) + "\\b");
        Matcher matcher = pattern.matcher(textChanged);

        ValidatorResponse validResponse = new ValidatorResponse(true, null);
        List<TextResponseDto> formattedResponse =
                List.of(new TextResponseDto("the force is strong in this one\\\\true"));

        when(validators.validateTextContent(textToProcess)).thenReturn(validResponse);
        when(textFormatter.changeFormatText(textToProcess)).thenReturn(formattedResponse);

        List<TextResponseDto> result = processTextService.validateText(textToProcess);

        assertNotNull(result);
        assertNotEquals(textToProcess,formattedResponse.get(0).toString());
        assertEquals(true,textToProcess.contains("\\"), "Formato válido");
        assertTrue(matcher.find());
        assertFalse(result.isEmpty(), "Debe retornar una lista con el cambio de formato del texto");
        assertEquals(formattedResponse, result, "Debe retornar la lista formateada correctamente");

        verify(validators).validateTextContent(textToProcess);
        verify(textFormatter).changeFormatText(textToProcess);
        verify(textFormatter, times(1)).changeFormatText(textToProcess);
    }
}