package com.apirest.utils;

import org.springframework.http.HttpStatus;

import java.util.List;

public class GenericResponseDto<TGeneric> {

    private final TGeneric result;
    private final HttpStatus statusCode;

    private final String statusDescription;

    private final List<String> listErrors;

    public GenericResponseDto(TGeneric result, HttpStatus statusCode, String statusDescription, List<String> errors) {
        this.result = result;
        this.statusCode = statusCode;
        this.statusDescription = statusDescription;
        this.listErrors = errors;
    }

    public TGeneric getResult() {
        return result;
    }

    public HttpStatus getStatusCode() {
        return statusCode;
    }

    public String getStatusDescription() {
        return statusDescription;
    }

    public List<String> getListErrors() {
        return listErrors;
    }
}
