package com.lab6.surveyapi.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lab6.surveyapi.Payloads.SurveyDto;
import com.lab6.surveyapi.Services.SurveyService;

@RestController
@RequestMapping(value = "/api/survey", produces = {"application/json", "application/xml"})
public class SurveyController {

    @Autowired
    private SurveyService surveyService;

    @GetMapping(value = "/", produces = {"application/json", "application/xml"})
    public ResponseEntity<List<SurveyDto>> getAllSurveys() {
        return new ResponseEntity<>(surveyService.getAllSurveys(), HttpStatus.OK);
    }
    
    @PostMapping(value = "/", produces = {"application/json", "application/xml"})
    public ResponseEntity<SurveyDto> createSurvey(@Validated @RequestBody SurveyDto surveyDto) {
        return new ResponseEntity<>(surveyService.createSurvey(surveyDto), HttpStatus.CREATED);
    }

    @PostMapping(value = "/addSurveyItem", produces = {"application/json", "application/xml"})
    public ResponseEntity<SurveyDto> addSurveyItem(@Validated @RequestParam(required = true) Integer surveyId, @Validated @RequestParam(required = true) Integer surveyItemId) {
        return new ResponseEntity<>(surveyService.addSurveyItem(surveyId, surveyItemId), HttpStatus.OK);
    }

    @GetMapping(value = "/{surveyId}", produces = {"application/json", "application/xml"})
    public ResponseEntity<SurveyDto> getSurveyById(@PathVariable Integer surveyId) {
        return new ResponseEntity<>(surveyService.getSurveyById(surveyId), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{surveyId}", produces = {"application/json", "application/xml"})
    public ResponseEntity<SurveyDto> deleteSurvey(@PathVariable(required = true) Integer surveyId) {
        return new ResponseEntity<>(surveyService.deleteSurvey(surveyId), HttpStatus.OK);
    }
}
