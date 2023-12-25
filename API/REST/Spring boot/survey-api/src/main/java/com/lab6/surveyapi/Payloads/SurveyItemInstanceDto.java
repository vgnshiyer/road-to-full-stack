package com.lab6.surveyapi.Payloads;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.dataformat.xml.annotation.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@JacksonXmlRootElement
@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SurveyItemInstanceDto {
    
    @JacksonXmlProperty(isAttribute = true)
    private Integer id;

    @JacksonXmlText
    private String selectedAnswer;

    @JacksonXmlProperty
    private Boolean isCorrect;

    @JacksonXmlText
    private String state = "Not Completed"; // Completed or Not Completed

    @JacksonXmlProperty
    private Integer surveyItemId;

    @JacksonXmlProperty
    private Integer surveyInstanceId;
}
