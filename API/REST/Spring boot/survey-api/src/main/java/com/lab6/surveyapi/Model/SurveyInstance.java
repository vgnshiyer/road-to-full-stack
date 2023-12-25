package com.lab6.surveyapi.Model;

import java.util.Set;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class SurveyInstance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "username", nullable = false)
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "Username must be alphanumeric")
    @NotNull(message = "Username cannot be null")
    private String username;

    @OneToMany(mappedBy = "surveyInstance", cascade = CascadeType.ALL)
    private Set<SurveyItemInstance> surveyItemInstances;

    @Column(name = "state")
    @Pattern(regexp = "^(Created|In progress|Completed)$", message = "State must be one of Created, In progress, or Completed")
    private String state = "Created"; // Created, In progress, Completed

    @ManyToOne
    @JoinColumn(name = "survey_id", nullable = false)
    @NotNull(message = "Survey cannot be null")
    private Survey survey;

    public void Complete() {
        this.state = "Completed";
    }
}
