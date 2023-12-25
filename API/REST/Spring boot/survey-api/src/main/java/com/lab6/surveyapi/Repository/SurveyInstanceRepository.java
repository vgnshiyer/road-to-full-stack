package com.lab6.surveyapi.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lab6.surveyapi.Model.SurveyInstance;

public interface SurveyInstanceRepository extends JpaRepository<SurveyInstance, Integer> {
    
    public List<SurveyInstance> findByState(String state);

    public SurveyInstance findBySurveyIdAndUsername(Integer surveyId, String username);
}
