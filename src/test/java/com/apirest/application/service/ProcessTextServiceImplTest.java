package com.apirest.application.service;

import com.apirest.utils.TextFormatter;
import com.apirest.utils.ValidatorResponse;
import com.apirest.utils.Validators;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProcessTextServiceImplTest {

    @Mock
    private Validators validators;

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
}