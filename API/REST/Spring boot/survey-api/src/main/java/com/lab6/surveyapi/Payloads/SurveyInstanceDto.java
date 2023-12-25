package com.lab6.surveyapi.Payloads;

import java.util.Set;

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
public class SurveyInstanceDto {
    
    @JacksonXmlProperty(isAttribute = true)
    private Integer id;

    @JacksonXmlText
    private String username;

    @JacksonXmlText
    private String state;

    @JacksonXmlProperty
    private Integer surveyId;
    
    @JacksonXmlElementWrapper(localName = "surveyItemInstances")
    private Set<SurveyItemInstanceDto> surveyItemInstances;
}
