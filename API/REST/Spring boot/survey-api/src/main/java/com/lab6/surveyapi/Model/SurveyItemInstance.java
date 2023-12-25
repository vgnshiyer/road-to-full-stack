package com.lab6.surveyapi.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class SurveyItemInstance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "selected_answer")
    private String selectedAnswer;

    @Column(name = "is_correct")
    private Boolean isCorrect;

    @Column(name = "state")
    private String state = "Not Completed"; // Completed or Not Completed

    @ManyToOne
    @JoinColumn(name = "survey_item_id")
    private SurveyItem surveyItem;

    @ManyToOne
    @JoinColumn(name = "survey_instance_id")
    private SurveyInstance surveyInstance;

    public void setSelectedAnswer(String answer) {
        this.selectedAnswer = answer;
        this.isCorrect = answer.equals(surveyItem.getCorrectAnswer());
        this.state = "Completed";
    }
}
