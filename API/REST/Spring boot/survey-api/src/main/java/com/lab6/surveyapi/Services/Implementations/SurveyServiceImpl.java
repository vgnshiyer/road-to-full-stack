package com.lab6.surveyapi.Services.Implementations;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import com.lab6.surveyapi.Model.Survey;
import com.lab6.surveyapi.Model.SurveyItem;
import com.lab6.surveyapi.Payloads.SurveyDto;
import com.lab6.surveyapi.Payloads.SurveyItemDto;
import com.lab6.surveyapi.Repository.SurveyItemRepository;
import com.lab6.surveyapi.Repository.SurveyRepository;
import com.lab6.surveyapi.Services.SurveyService;

@Service
public class SurveyServiceImpl implements SurveyService {

    @Autowired
    private SurveyRepository surveyRepository;

    @Autowired
    private SurveyItemRepository surveyItemRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<SurveyDto> getAllSurveys() {
        return surveyRepository.findAll().stream()
                .map(survey -> modelMapper.map(survey, SurveyDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public SurveyDto createSurvey(SurveyDto surveyDto) {

        return modelMapper.map(
                surveyRepository.save(
                        modelMapper.map(surveyDto, Survey.class)),
                SurveyDto.class);
    }

    @Override
    public SurveyDto addSurveyItem(Integer surveyId, Integer itemId) {
        SurveyItem surveyItem = surveyItemRepository.findById(itemId).orElseThrow(
                () -> new ResourceNotFoundException("SurveyItem with id " + itemId + " not found"));

        Survey survey = surveyRepository.findById(surveyId).orElseThrow(
                () -> new ResourceNotFoundException("Survey with id " + surveyId + " not found"));

        if (survey.getState() != "Created")
            throw new IllegalStateException("Survey may be completed or deleted");

        survey.getSurveyItems().add(surveyItem);

        SurveyDto surveyDto = modelMapper.map(surveyRepository.save(survey), SurveyDto.class);
        surveyDto.setSurveyItems(survey.getSurveyItems().stream()
                .map(item -> modelMapper.map(item, SurveyItemDto.class))
                .collect(Collectors.toSet()));

        return surveyDto;
    }

    @Override
    public SurveyDto getSurveyById(Integer surveyId) {
        Survey survey = surveyRepository.findById(surveyId).orElseThrow(
                () -> new ResourceNotFoundException("Survey with id " + surveyId + " not found"));

        return modelMapper.map(survey, SurveyDto.class);
    }

    @Override
    public SurveyDto deleteSurvey(Integer surveyId) {
        Survey survey = surveyRepository.findById(surveyId).orElseThrow(
                () -> new ResourceNotFoundException("Survey with id " + surveyId + " not found"));

        if (survey.getState() == "Deleted")
            throw new IllegalStateException("Survey already deleted");

        survey.markForDeletion();
        surveyRepository.save(survey);
        
        return modelMapper.map(survey, SurveyDto.class);
    }
}
