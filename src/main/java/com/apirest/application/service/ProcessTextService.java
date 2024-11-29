package com.apirest.application.service;

import com.apirest.application.model.response.TextResponseDto;

import java.util.List;

public interface ProcessTextService {
    List<TextResponseDto> validateText(String text);
}
