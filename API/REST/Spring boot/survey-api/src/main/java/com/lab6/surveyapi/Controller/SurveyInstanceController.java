package com.lab6.surveyapi.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lab6.surveyapi.Payloads.SurveyInstanceDto;
import com.lab6.surveyapi.Services.SurveyInstanceService;

@RestController
@RequestMapping(value = "/api/survey-instance", produces = {"application/json", "application/xml"})
public class SurveyInstanceController {

    @Autowired
    private SurveyInstanceService surveyInstanceService;
    
    @PostMapping(value = "/create", produces = {"application/json", "application/xml"})
    public ResponseEntity<SurveyInstanceDto> createSurveyInstance(@Validated @RequestBody SurveyInstanceDto surveyInstanceDto) {
        return new ResponseEntity<>(surveyInstanceService.createSurveyInstance(surveyInstanceDto), HttpStatus.CREATED);
    }

    @GetMapping(value = "/", produces = {"application/json", "application/xml"})
    public ResponseEntity<List<SurveyInstanceDto>> getAllSurveyInstances() {
        return new ResponseEntity<>(surveyInstanceService.getAllSurveyInstances(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = {"application/json", "application/xml"})
    public ResponseEntity<SurveyInstanceDto> getSurveyInstanceById(@PathVariable Integer id) {
        return new ResponseEntity<>(surveyInstanceService.getSurveyInstanceById(id), HttpStatus.OK);
    }

    @GetMapping(value = {"/state", "/state/{state}"}, produces = {"application/json", "application/xml"})
    public ResponseEntity<List<SurveyInstanceDto>> getSurveyInstancesByState(@PathVariable(required = false) String state) {
        if (state == null) {
            return new ResponseEntity<>(surveyInstanceService.getAllSurveyInstances(), HttpStatus.OK);
        }
        return new ResponseEntity<>(surveyInstanceService.getSurveyInstancesByState(state), HttpStatus.OK);
    }
    
}
