package com.lab6.surveyapi.Model;

import java.util.List;
import java.util.Set;

import com.lab6.surveyapi.Utilities.ListToStringConverter;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class SurveyItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "question_stem", nullable = false)
    private String questionStem;

    @Convert(converter = ListToStringConverter.class)
    @Column(name = "possible_answers", nullable = false)
    @Size(min = 4, max = 4, message = "Must have 4 possible answers")
    private List<String> possibleAnswers;

    @Column(name = "correct_answer", nullable = false)
    private String correctAnswer;

    @OneToMany(mappedBy = "surveyItem", cascade = CascadeType.ALL)
    private Set<SurveyItemInstance> surveyItemInstances;

    @ManyToMany(mappedBy = "surveyItems", cascade = CascadeType.ALL)
    private Set<Survey> surveys;
}
