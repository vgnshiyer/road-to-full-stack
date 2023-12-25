package com.lab6.surveyapi.Model;

import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Survey {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
        name = "survey_to_survey_item",
        joinColumns = @JoinColumn(name = "survey_id"),
        inverseJoinColumns = @JoinColumn(name = "survey_item_id")
    )
    @Size(min = 0, max = 5, message = "Must have between 0 and 5 survey items")
    private Set<SurveyItem> surveyItems;

    @Column(name = "state")
    @Pattern(regexp = "^(Created|Completed|Deleted)$", message = "State must be one of Created, Completed, or Deleted")
    private String state = "Created"; // Created, Completed, Deleted

    @OneToMany(mappedBy = "survey", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<SurveyInstance> surveyInstances;

    public void Complete() {
        this.state = "Completed";
    }

    public void markForDeletion() {
        this.state = "Deleted";
    }
}
