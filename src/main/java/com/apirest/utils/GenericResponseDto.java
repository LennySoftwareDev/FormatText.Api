package com.apirest.utils;

import org.springframework.http.HttpStatus;

import java.util.List;

public class GenericResponseDto<TGeneric> {

    private TGeneric result;
    private HttpStatus statusCode;

    private String statusDescription;

    private List<String> listErrors;

    public GenericResponseDto(TGeneric result, HttpStatus statusCode, String statusDescription, List<String> errors) {
        this.result = result;
        this.statusCode = statusCode;
        this.statusDescription = statusDescription;
        this.listErrors = errors;
    }

    public TGeneric getResult() {
        return result;
    }

    public void setResult(TGeneric result) {
        this.result = result;
    }

    public HttpStatus getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(HttpStatus statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusDescription() {
        return statusDescription;
    }

    public void setStatusDescription(String statusDescription) {
        this.statusDescription = statusDescription;
    }

    public List<String> getListErrors() {
        return listErrors;
    }

    public void setListErrors(List<String> listErrors) {
        this.listErrors = listErrors;
    }
}
