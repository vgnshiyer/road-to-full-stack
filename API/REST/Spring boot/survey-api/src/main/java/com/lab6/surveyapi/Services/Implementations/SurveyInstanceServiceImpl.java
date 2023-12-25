package com.lab6.surveyapi.Services.Implementations;

import java.util.List;
import java.util.HashSet;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lab6.surveyapi.Model.Survey;
import com.lab6.surveyapi.Model.SurveyInstance;
import com.lab6.surveyapi.Model.SurveyItem;
import com.lab6.surveyapi.Payloads.SurveyInstanceDto;
import com.lab6.surveyapi.Payloads.SurveyItemInstanceDto;
import com.lab6.surveyapi.Repository.SurveyInstanceRepository;
import com.lab6.surveyapi.Repository.SurveyRepository;
import com.lab6.surveyapi.Services.SurveyInstanceService;
import com.lab6.surveyapi.Services.SurveyItemInstanceService;

@Service
public class SurveyInstanceServiceImpl implements SurveyInstanceService {

    @Autowired
    private SurveyInstanceRepository surveyInstanceRepository;

    @Autowired
    private SurveyRepository surveyRepository;

    @Autowired
    private SurveyItemInstanceService surveyItemInstanceService;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public SurveyInstanceDto createSurveyInstance(SurveyInstanceDto surveyInstanceDto) {
        Integer surveyId = surveyInstanceDto.getSurveyId();
        String username = surveyInstanceDto.getUsername();

        SurveyInstance temp = surveyInstanceRepository.findBySurveyIdAndUsername(surveyId, username);
        if (temp != null) {
            throw new RuntimeException("SurveyInstance with surveyId " + surveyId + " and username " + username + " already exists");
        }

        Survey survey = surveyRepository.findById(surveyId)
                .orElseThrow(() -> new RuntimeException("Survey with id " + surveyId + " not found"));

        survey.Complete();

        surveyRepository.save(survey);
        
        SurveyInstance surveyInstance = new SurveyInstance();
        surveyInstance.setSurvey(survey);
        surveyInstance.setUsername(username);

        surveyInstance = surveyInstanceRepository.save(surveyInstance);

        SurveyInstanceDto surveyInstanceDtoResponse = modelMapper.map(surveyInstance, SurveyInstanceDto.class);
        surveyInstanceDtoResponse.setSurveyItemInstances(new HashSet<>());

        for (SurveyItem surveyItem : survey.getSurveyItems()) {
            SurveyItemInstanceDto surveyItemInstanceDto = surveyItemInstanceService.createSurveyItemInstance(surveyItem.getId(), surveyInstance.getId());
            surveyInstanceDtoResponse.getSurveyItemInstances().add(surveyItemInstanceDto);
        }

        return surveyInstanceDtoResponse;
    }

    @Override
    public List<SurveyInstanceDto> getAllSurveyInstances() {
        return surveyInstanceRepository.findAll().stream()
                .map(surveyInstance -> modelMapper.map(surveyInstance, SurveyInstanceDto.class))
                .toList();        
    }

    @Override
    public SurveyInstanceDto getSurveyInstanceById(Integer id) {
        return modelMapper.map(surveyInstanceRepository.findById(id).orElseThrow(
            () -> new RuntimeException("SurveyInstance with id " + id + " not found")
        ), SurveyInstanceDto.class);
    }

    @Override
    public List<SurveyInstanceDto> getSurveyInstancesByState(String state) {
        if (state.equals("Created") || state.equals("In progress") || state.equals("Completed")) {
            return surveyInstanceRepository.findByState(state).stream()
                .map(surveyInstance -> modelMapper.map(surveyInstance, SurveyInstanceDto.class))
                .toList();
        } else {
            throw new RuntimeException("Invalid state! Please use one of the following: Created, In progress, Completed");
        }
    }

}
