package com.lab6.surveyapi;

import java.util.List;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.lab6.surveyapi.Model.Survey;
import com.lab6.surveyapi.Model.SurveyInstance;
import com.lab6.surveyapi.Model.SurveyItem;
import com.lab6.surveyapi.Model.SurveyItemInstance;
import com.lab6.surveyapi.Repository.SurveyInstanceRepository;
import com.lab6.surveyapi.Repository.SurveyItemInstanceRepository;
import com.lab6.surveyapi.Repository.SurveyItemRepository;
import com.lab6.surveyapi.Repository.SurveyRepository;

@SpringBootApplication
public class SurveyApiApplication implements CommandLineRunner {

	@Autowired
	private SurveyItemRepository surveyItemRepository;

	@Autowired
	private SurveyRepository surveyRepository;

	@Autowired
	private SurveyInstanceRepository surveyInstanceRepository;

	@Autowired
	private SurveyItemInstanceRepository surveyItemInstanceRepository;

	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}

	public static void main(String[] args) {
		SpringApplication.run(SurveyApiApplication.class, args);
	}

    @Override
    public void run(String... args) throws Exception {

		// Create Sample Survey Items
		SurveyItem surveyItem1 = new SurveyItem();
		surveyItem1.setQuestionStem("Which planet has the most moons?");
		surveyItem1.setPossibleAnswers(List.of("Saturn", "Jupiter", "Uranus", "Neptune"));
		surveyItem1.setCorrectAnswer("Jupiter");

		SurveyItem surveyItem2 = new SurveyItem();
		surveyItem2.setQuestionStem("What is the capital of the India?");
		surveyItem2.setPossibleAnswers(List.of("New Delhi", "Mumbai", "Kolkata", "Chennai"));
		surveyItem2.setCorrectAnswer("New Delhi");

		SurveyItem surveyItem3 = new SurveyItem();
		surveyItem3.setQuestionStem("What is your favorite color?");
		surveyItem3.setPossibleAnswers(List.of("Red", "Blue", "Green", "Yellow"));
		surveyItem3.setCorrectAnswer("Blue");

		SurveyItem surveyItem4 = new SurveyItem();
		surveyItem4.setQuestionStem("How many continents are there?");
		surveyItem4.setPossibleAnswers(List.of("5", "6", "7", "8"));
		surveyItem4.setCorrectAnswer("7");

		SurveyItem surveyItem5 = new SurveyItem();
		surveyItem5.setQuestionStem("In which country is the Taj Mahal located?");
		surveyItem5.setPossibleAnswers(List.of("India", "Pakistan", "Bangladesh", "Nepal"));
		surveyItem5.setCorrectAnswer("India");

		SurveyItem surveyItem6 = new SurveyItem();
		surveyItem6.setQuestionStem("What is the largest country in the world?");
		surveyItem6.setPossibleAnswers(List.of("Russia", "Canada", "China", "USA"));
		surveyItem6.setCorrectAnswer("Russia");

		surveyItemRepository.saveAll(List.of(surveyItem1, surveyItem2, surveyItem3, surveyItem4, surveyItem5, surveyItem6));

		// Create Sample Survey
		Survey survey1 = new Survey();
		survey1.setState("Created"); // survey is being authored

		surveyRepository.save(survey1);

		survey1.setSurveyItems(Set.of(surveyItem1, surveyItem2, surveyItem3));
		survey1.setState("Completed"); // survey is ready to be published
		surveyRepository.save(survey1);

		Survey survey2 = new Survey();
		survey2.setState("Created"); // survey is being authored

		surveyRepository.save(survey2);

		survey2.setSurveyItems(Set.of(surveyItem4, surveyItem5, surveyItem6));
		survey2.setState("Completed"); // survey is ready to be published
		surveyRepository.save(survey2);

		// Create Sample Survey Instances
		SurveyInstance surveyInstance1 = new SurveyInstance();
		surveyInstance1.setSurvey(survey1);
		surveyInstance1.setUsername("user1");

		surveyInstanceRepository.save(surveyInstance1);

		// Create Sample Survey Item Instances
		for (SurveyItem surveyItem : survey1.getSurveyItems()) {
			SurveyItemInstance surveyItemInstance = new SurveyItemInstance();
			surveyItemInstance.setSurveyItem(surveyItem);
			surveyItemInstance.setSurveyInstance(surveyInstance1);
			surveyItemInstanceRepository.save(surveyItemInstance);
		}
    }
}
