package com.lab6.surveyapi.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lab6.surveyapi.Model.SurveyItem;

public interface SurveyItemRepository extends JpaRepository<SurveyItem, Integer>{
    
}
