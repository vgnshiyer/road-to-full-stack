package com.lab6.surveyapi.Services.Implementations;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lab6.surveyapi.Model.SurveyItem;
import com.lab6.surveyapi.Payloads.SurveyItemDto;
import com.lab6.surveyapi.Repository.SurveyItemRepository;
import com.lab6.surveyapi.Services.SurveyItemService;

import org.modelmapper.ModelMapper;

@Service
public class SurveyItemServiceImpl implements SurveyItemService {

    @Autowired
    private SurveyItemRepository surveyItemRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<SurveyItemDto> getAllSurveyItems() {
        
        List<SurveyItemDto> surveyItemDtos = surveyItemRepository.findAll().stream()
            .map(surveyItem -> modelMapper.map(surveyItem, SurveyItemDto.class))
            .collect(Collectors.toList());

        return surveyItemDtos;
    }

    @Override
    public SurveyItemDto createSurveyItem(SurveyItemDto surveyItemDto) {

        SurveyItemDto surveyItemDtoResponse = modelMapper.map(
            surveyItemRepository.save(modelMapper.map(surveyItemDto, SurveyItem.class)),
            SurveyItemDto.class
        );
        return surveyItemDtoResponse;
    }
}
