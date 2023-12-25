package com.lab6.surveyapi.Services.Implementations;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lab6.surveyapi.Model.SurveyInstance;
import com.lab6.surveyapi.Model.SurveyItem;
import com.lab6.surveyapi.Model.SurveyItemInstance;
import com.lab6.surveyapi.Payloads.SurveyItemInstanceDto;
import com.lab6.surveyapi.Repository.SurveyInstanceRepository;
import com.lab6.surveyapi.Repository.SurveyItemInstanceRepository;
import com.lab6.surveyapi.Repository.SurveyItemRepository;
import com.lab6.surveyapi.Services.SurveyItemInstanceService;

@Service
public class SurveyItemInstanceServiceImpl implements SurveyItemInstanceService{

    @Autowired
    private SurveyItemInstanceRepository surveyItemInstanceRepository;

    @Autowired
    private SurveyInstanceRepository surveyInstanceRepository;

    @Autowired
    private SurveyItemRepository surveyItemRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public SurveyItemInstanceDto createSurveyItemInstance(Integer surveyItemId, Integer surveyInstanceId) {
        SurveyItemInstance temp = surveyItemInstanceRepository.findBySurveyInstanceIdAndSurveyItemId(surveyInstanceId, surveyItemId);

        if (temp != null) {
            throw new RuntimeException("SurveyItemInstance with surveyInstanceId " + surveyInstanceId + " and surveyItemId " + surveyItemId + " already exists");
        }

        SurveyInstance surveyInstance = surveyInstanceRepository.findById(surveyInstanceId)
                .orElseThrow(() -> new RuntimeException("Survey with id " + surveyInstanceId + " not found"));

        SurveyItem surveyItem = surveyItemRepository.findById(surveyItemId)
                .orElseThrow(() -> new RuntimeException("Survey with id " + surveyItemId + " not found"));

        SurveyItemInstance surveyItemInstance = new SurveyItemInstance();
        surveyItemInstance.setSurveyInstance(surveyInstance);
        surveyItemInstance.setSurveyItem(surveyItem);

        surveyItemInstanceRepository.save(surveyItemInstance);

        return modelMapper.map(surveyItemInstance, SurveyItemInstanceDto.class);
    }

    @Override
    public SurveyItemInstanceDto recordAnswer(Integer surveyInstanceId, Integer surveyItemId, String answer) {
        SurveyInstance surveyInstance = surveyInstanceRepository.findById(surveyInstanceId)
                .orElseThrow(() -> new RuntimeException("Survey with id " + surveyInstanceId + " not found"));
        if (surveyInstance.getState().equals("Completed")) {
            throw new RuntimeException("SurveyInstance with id " + surveyInstanceId + " is already completed");
        }

        SurveyItemInstance surveyItemInstance = surveyItemInstanceRepository.findBySurveyInstanceIdAndSurveyItemId(surveyInstanceId, surveyItemId);
        if (surveyItemInstance == null) {
            throw new RuntimeException("SurveyItemInstance with surveyInstanceId " + surveyInstanceId + " and surveyItemId " + surveyItemId + " not found");
        }

        if (surveyItemInstance.getState().equals("Completed")) {
            throw new RuntimeException("SurveyItemInstance with surveyInstanceId " + surveyInstanceId + " and surveyItemId " + surveyItemId + " is already completed");
        }

        surveyItemInstance.setSelectedAnswer(answer);
        surveyItemInstance = surveyItemInstanceRepository.save(surveyItemInstance);

        if (surveyInstance.getState().equals("Created")) {
            surveyInstance.setState("In progress");
            surveyInstanceRepository.save(surveyInstance);
        }

        Boolean allCompleted = true;
        for (SurveyItemInstance temp : surveyInstance.getSurveyItemInstances()) {
            if (!temp.getState().equals("Completed")) {
                allCompleted = false;
                break;
            }
        }

        if (allCompleted) {
            surveyInstance.Complete();
            surveyInstanceRepository.save(surveyInstance);
        }

        return modelMapper.map(surveyItemInstance, SurveyItemInstanceDto.class);
    }
    
}
