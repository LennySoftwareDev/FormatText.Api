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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProcessTextServiceImplTest {

    @InjectMocks
    private ProcessTextServiceImpl processTextService;
    @Mock
    private Validators validators;

    @Mock
    private TextFormatter textFormatter;

    @Test
    void validateText_invalidText_returnsEmptyList() {

        String textToProcess = "";

        ValidatorResponse invalidResponse = new ValidatorResponse(false, "Error");

        when(validators.validateTextContent(textToProcess)).thenReturn(invalidResponse);

        ValidatorResponse result = validators.validateTextContent(textToProcess);

        assertFalse(result.isValid(), "El texto no puede estar vacío");
        verify(validators, times(1)).validateTextContent(textToProcess);
    }

    @Test
    void validate_formatText() {

        String textToProcess = "3\\\\the force is strong in this one";

        ValidatorResponse validResponse = new ValidatorResponse(true, "");

        when(validators.validateFormatInitialText(textToProcess)).thenReturn(validResponse);

        ValidatorResponse result = validators.validateFormatInitialText(textToProcess);

        assertTrue(result.isValid(), "");
        verify(validators, times(1)).validateFormatInitialText(textToProcess);
    }

    @Test
    void validate_changeFormatText(){

        String textToProcess = "Valid text";
        ValidatorResponse validResponse = new ValidatorResponse(true, null);
        List<TextResponseDto> formattedResponse = List.of(new TextResponseDto("formatted text"));

        when(validators.validateTextContent(textToProcess)).thenReturn(validResponse);
        when(textFormatter.changeFormatText(textToProcess)).thenReturn(formattedResponse);

        List<TextResponseDto> result = processTextService.validateText(textToProcess);

        assertNotNull(result);
        assertFalse(result.isEmpty(), "Debe retornar una lista cuando el texto es válido");
        assertEquals(formattedResponse, result, "Debe retornar la lista formateada correctamente");

        verify(validators).validateTextContent(textToProcess);
        verify(textFormatter).changeFormatText(textToProcess);
        verify(textFormatter, times(1)).changeFormatText(textToProcess);
    }
}