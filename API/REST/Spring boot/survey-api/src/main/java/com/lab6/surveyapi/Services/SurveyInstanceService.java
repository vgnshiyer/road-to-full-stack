package com.lab6.surveyapi.Services;

import com.lab6.surveyapi.Payloads.SurveyInstanceDto;

import java.util.List;

public interface SurveyInstanceService {
    
    public SurveyInstanceDto createSurveyInstance(SurveyInstanceDto surveyInstanceDto);

    public List<SurveyInstanceDto> getAllSurveyInstances();

    public SurveyInstanceDto getSurveyInstanceById(Integer id);

    public List<SurveyInstanceDto> getSurveyInstancesByState(String state);
}
