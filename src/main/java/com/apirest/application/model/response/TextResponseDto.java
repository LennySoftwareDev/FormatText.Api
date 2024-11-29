package com.apirest.application.model.response;

public class TextResponseDto {

    private final String processedText;

    public TextResponseDto(String processedText){
        this.processedText = processedText;
    }

    public String getProcessedText(){
        return this.processedText;
    }
}
