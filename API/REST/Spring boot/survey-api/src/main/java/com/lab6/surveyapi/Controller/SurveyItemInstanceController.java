package com.lab6.surveyapi.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lab6.surveyapi.Payloads.SurveyItemInstanceDto;
import com.lab6.surveyapi.Services.SurveyItemInstanceService;

@RestController
@RequestMapping(value = "/api/surveyItemInstance", produces = {"application/json", "application/xml"})
public class SurveyItemInstanceController {

    @Autowired
    private SurveyItemInstanceService surveyItemInstanceService;
    
    @PostMapping(value = "/answer", produces = {"application/json", "application/xml"})
    public ResponseEntity<SurveyItemInstanceDto> recordAnswer(@RequestParam(required = true) Integer surveyInstanceId, @RequestParam(required = true) Integer surveyItemId, @RequestParam(required = true) String answer) {
        SurveyItemInstanceDto surveyItemInstanceDto = surveyItemInstanceService.recordAnswer(surveyInstanceId, surveyItemId, answer);
        return new ResponseEntity<>(surveyItemInstanceDto, HttpStatus.OK);
    }
}
