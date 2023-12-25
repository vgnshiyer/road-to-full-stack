package com.lab6.surveyapi.Payloads;

import com.fasterxml.jackson.dataformat.xml.annotation.*;

import lombok.Getter;
import lombok.Setter;

@JacksonXmlRootElement
@Getter
@Setter
public class ApiResponse {
    
    @JacksonXmlText
    private String message;

    @JacksonXmlProperty(isAttribute = true)
    private Boolean success;

    public ApiResponse(String message, Boolean success) {
        this.message = message;
        this.success = success;
    }
}
