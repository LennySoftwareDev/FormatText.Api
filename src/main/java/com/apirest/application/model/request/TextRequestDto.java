package com.apirest.application.model.request;

public class TextRequestDto {

    private String text;

    public TextRequestDto() {
    }
    public TextRequestDto(String text) {
        this.text = text;
    }

    public String getText(){
        return this.text;
    }
}
