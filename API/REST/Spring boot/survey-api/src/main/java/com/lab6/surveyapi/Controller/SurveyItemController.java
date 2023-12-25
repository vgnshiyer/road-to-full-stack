package com.lab6.surveyapi.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lab6.surveyapi.Payloads.SurveyItemDto;
import com.lab6.surveyapi.Services.SurveyItemService;

@RestController
@RequestMapping(value = "/api/surveyItem", produces = {"application/json", "application/xml"})
public class SurveyItemController {

    @Autowired
    private SurveyItemService surveyItemService;
    
    @GetMapping(value = "/", produces = {"application/json", "application/xml"})
    public ResponseEntity<List<SurveyItemDto>> getAllSurveyItems() {
        return new ResponseEntity<>(surveyItemService.getAllSurveyItems(), HttpStatus.OK);
    }

    @PostMapping(value = "/", produces = {"application/json", "application/xml"})
    public ResponseEntity<SurveyItemDto> createSurveyItem(@Validated @RequestBody SurveyItemDto surveyItemDto) {
        return new ResponseEntity<>(surveyItemService.createSurveyItem(surveyItemDto), HttpStatus.CREATED);
    }
}
