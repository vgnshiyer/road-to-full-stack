package com.lab6.surveyapi.Services;

import org.springframework.stereotype.Service;

import com.lab6.surveyapi.Payloads.SurveyItemInstanceDto;

@Service
public interface SurveyItemInstanceService {
    
    public SurveyItemInstanceDto createSurveyItemInstance(Integer surveyItemId, Integer surveyInstanceId);

    public SurveyItemInstanceDto recordAnswer(Integer surveyInstanceId, Integer surveyItemId, String answer);
}
