# Survey API - Lab 6

This is a Spring Boot project.

## Prerequisites

You will need the following tools:

- Java Development Kit (JDK) 17
- Gradle 7.2 or later

## Running the Project

1. Build the project

`./gradlew build`

2. Run the project

`./gradlew bootRun`

The application should be running on http://localhost:8080

**Please access the below URL to view the API documentation**

http://localhost:8080/api-docs

Below are the 10 API endpoints asked in the specification supported by the application:

1. Create a survey item.
2. Create a survey.
3. Add a survey item to a survey (note that a survey item may be on more than one survey).
4. Get the set of all surveys.
5. Get a specific survey and the set of all survey items in that survey
6. Create a survey instance of a survey for a user, with the associated set of survey item instances.
7. Accept an answer for a survey item instance on a specific survey instance.
8. Retrieve the set of all survey instances in a given state; if no state is given return all survey instances.
9. Retrieve a specific survey instance with all of the survey item instances contained with it.
10. Delete a specific survey.

**Message for grader**

- EC1: I have added XML responses for all the above API's. Please use header `Accept: application/xml` for each API request you make to see the output in XML.
- EC2: I added multiple endpoints to the documentation. Please visit localhost:8080/api-docs