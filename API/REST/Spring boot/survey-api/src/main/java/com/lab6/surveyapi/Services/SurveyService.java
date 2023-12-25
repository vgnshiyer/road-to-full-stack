package com.lab6.surveyapi.Services;

import java.util.List;
import org.springframework.stereotype.Service;

import com.lab6.surveyapi.Payloads.SurveyDto;

@Service
public interface SurveyService {
    
    public List<SurveyDto> getAllSurveys();
    
    public SurveyDto createSurvey(SurveyDto surveyDto);

    public SurveyDto addSurveyItem(Integer surveyId, Integer itemId);

    public SurveyDto getSurveyById(Integer surveyId);

    public SurveyDto deleteSurvey(Integer surveyId);
}
