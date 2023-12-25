package com.lab6.surveyapi.Payloads;

import java.util.List;

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
public class SurveyItemDto {
    
    @JacksonXmlProperty(isAttribute = true)
    private Integer id;

    @JacksonXmlText
    private String questionStem;

    @JacksonXmlElementWrapper(localName = "possibleAnswers")
    private List<String> possibleAnswers;

    @JacksonXmlText
    private String correctAnswer;
}
