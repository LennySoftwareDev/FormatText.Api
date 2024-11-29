package com.apirest.controller;

import com.apirest.application.model.request.TextRequestDto;
import com.apirest.utils.GenericResponseDto;
import com.apirest.application.model.response.TextResponseDto;
import com.apirest.application.service.ProcessTextService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("api/text")
public class ProcessTextController {

    @Autowired
    ProcessTextService processTextService;

    @CrossOrigin
    @PostMapping("/processText")
    public ResponseEntity<GenericResponseDto<List<TextResponseDto>>> processText(@RequestBody TextRequestDto textRequestDto) {

        try {
            String text = textRequestDto.getText();

            List<TextResponseDto> textResponse = processTextService.validateText(text);

            GenericResponseDto<List<TextResponseDto>> response = new GenericResponseDto<>(
                    textResponse,
                    HttpStatus.ACCEPTED,
                    "Proceso exitoso",
                    Collections.emptyList()
            );
            return ResponseEntity.ok(response);

        } catch (Exception e) {

            List<String> listErrors = new ArrayList<>();

            listErrors.add(e.getMessage());

            GenericResponseDto<List<TextResponseDto>> response = new GenericResponseDto<>(
                    null,
                    HttpStatus.BAD_REQUEST,
                    "Ocurrió un error",
                    listErrors
            );
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }
}
