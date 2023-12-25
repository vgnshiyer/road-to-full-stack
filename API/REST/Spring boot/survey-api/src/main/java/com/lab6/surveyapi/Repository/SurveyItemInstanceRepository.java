package com.lab6.surveyapi.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lab6.surveyapi.Model.SurveyItemInstance;

public interface SurveyItemInstanceRepository extends JpaRepository<SurveyItemInstance, Integer>{
    
    public SurveyItemInstance findBySurveyInstanceIdAndSurveyItemId(Integer surveyInstanceId, Integer surveyItemId);
}
