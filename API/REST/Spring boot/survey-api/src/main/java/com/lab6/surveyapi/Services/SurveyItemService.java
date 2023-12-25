package com.lab6.surveyapi.Services;

import java.util.List;

import com.lab6.surveyapi.Payloads.SurveyItemDto;

public interface SurveyItemService {
    
    public List<SurveyItemDto> getAllSurveyItems();

    public SurveyItemDto createSurveyItem(SurveyItemDto surveyItemDto);
}
