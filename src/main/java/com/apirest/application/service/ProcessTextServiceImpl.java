package com.apirest.application.service;

import com.apirest.application.model.response.TextResponseDto;
import com.apirest.utils.TextFormatter;
import com.apirest.utils.ValidatorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class ProcessTextServiceImpl implements ProcessTextService {

    @Autowired
    TextFormatter textFormatter;

    @Override
    public List<TextResponseDto> validateText(String textToProcess) {

        List<TextResponseDto> newFormatText;

        ValidatorResponse validatorTexContent = textFormatter.validators.validateTextContent(textToProcess);

        if (!validatorTexContent.isValid()) {
            return Collections.emptyList();
        }

        newFormatText = textFormatter.changeFormatText(textToProcess);

        return newFormatText;
    }
}
