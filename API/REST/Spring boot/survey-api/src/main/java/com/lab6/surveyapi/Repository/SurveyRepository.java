package com.lab6.surveyapi.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lab6.surveyapi.Model.Survey;

public interface SurveyRepository extends JpaRepository<Survey, Integer>{
    
}
