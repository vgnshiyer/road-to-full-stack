# Survey API

- **Version**: 1.0.0
- **Author**: Vignesh Iyer
- **ASU ID**: 1227792802
- **ASURite**: viyer10
- **Contact**: vgnshiyer@asu.edu
- **Base URL**: `http://localhost:8080/api`

# Endpoints

## SurveyController

### GET /api/survey/

Fetches all surveys.

- Method: `GET`
- URL: `/api/survey/`
- Response: `200 OK`
- Response Body: List of `SurveyDto` objects

### GET /api/survey/{surveyId}/items

Fetches all items for a specific survey.

- Method: `GET`
- URL: `/api/survey/{surveyId}/items`
- Path Variables:
  - `surveyId` (required): ID of the survey
- Response: `200 OK`
- Response Body: List of `SurveyItemDto` objects

### GET /api/survey/{surveyId}

Fetches a survey by its ID.

- Method: `GET`
- URL: `/api/survey/{surveyId}`
- Path Variables:
  - `surveyId` (required): ID of the survey
- Response: `200 OK`
- Response Body: `SurveyDto` object

### GET /api/survey/{surveyId}/instances

Fetches all instances for a specific survey.

- Method: `GET`
- URL: `/api/survey/{surveyId}/instances`
- Path Variables:
  - `surveyId` (required): ID of the survey
- Response: `200 OK`
- Response Body: List of `SurveyInstanceDto` objects

### PUT /api/survey/{surveyId}

Updates a survey by its ID.

- Method: `PUT`
- URL: `/api/survey/{surveyId}`
- Path Variables:
  - `surveyId` (required): ID of the survey
- Request Body: `SurveyDto` object
- Response: `200 OK`
- Response Body: Updated `SurveyDto` object

### POST /api/survey/

Creates a new survey.

- Method: `POST`
- URL: `/api/survey/`
- Request Body: `SurveyDto` object
- Response: `201 Created`
- Response Body: Created `SurveyDto` object

### POST /api/survey/addSurveyItem

Adds a survey item to a survey.

- Method: `POST`
- URL: `/api/survey/addSurveyItem`
- Request Parameters:
  - `surveyId` (required): ID of the survey
  - `surveyItemId` (required): ID of the survey item
- Response: `200 OK`
- Response Body: Updated `SurveyDto` object

### POST /api/survey/{surveyId}/items

Adds a new item to a specific survey.

- Method: `POST`
- URL: `/api/survey/{surveyId}/items`
- Path Variables:
  - `surveyId` (required): ID of the survey
- Request Body: `SurveyItemDto` object
- Response: `201 Created`
- Response Body: Created `SurveyItemDto` object

### DELETE /api/survey/{surveyId}

Deletes a survey by its ID.

- Method: `DELETE`
- URL: `/api/survey/{surveyId}`
- Path Variables:
  - `surveyId` (required): ID of the survey
- Response: `200 OK`
- Response Body: Deleted `SurveyDto` object

### DELETE /api/survey/{surveyId}/items/{itemId}

Deletes an item from a specific survey.

- Method: `DELETE`
- URL: `/api/survey/{surveyId}/items/{itemId}`
- Path Variables:
  - `surveyId` (required): ID of the survey
  - `itemId` (required): ID of the survey item
- Response: `200 OK`
- Response Body: Deleted `SurveyItemDto` object

## SurveyItemController

### GET /api/surveyItem/

Fetches all survey items.

- Method: `GET`
- URL: `/api/surveyItem/`
- Response: `200 OK`
- Response Body: List of `SurveyItemDto` objects

### GET /api/surveyItem/{itemId}/instances

Fetches all instances for a specific survey item.

- Method: `GET`
- URL: `/api/surveyItem/{itemId}/instances`
- Path Variables:
  - `itemId` (required): ID of the survey item
- Response: `200 OK`
- Response Body: List of `SurveyInstanceDto` objects

### PUT /api/surveyItem/{itemId}

Updates a specific survey item by its ID.

- Method: `PUT`
- URL: `/api/surveyItem/{itemId}`
- Path Variables:
  - `itemId` (required): ID of the survey item
- Request Body: `SurveyItemDto` object
- Response: `200 OK`
- Response Body: Updated `SurveyItemDto` object

### POST /api/surveyItem/

Creates a new survey item.

- Method: `POST`
- URL: `/api/surveyItem/`
- Request Body: `SurveyItemDto` object
- Response: `201 Created`
- Response Body: Created `SurveyItemDto` object

### DELETE /api/surveyItem/{itemId}

Deletes a specific survey item by its ID.

- Method: `DELETE`
- URL: `/api/surveyItem/{itemId}`
- Path Variables:
  - `itemId` (required): ID of the survey item
- Response: `200 OK`
- Response Body: Deleted `SurveyItemDto` object

## SurveyInstanceController

### GET /api/survey-instance/

Fetches all survey instances.

- Method: `GET`
- URL: `/api/survey-instance/`
- Response: `200 OK`
- Response Body: List of `SurveyInstanceDto` objects

### GET /api/survey-instance/{id}

Fetches a survey instance by its ID.

- Method: `GET`
- URL: `/api/survey-instance/{id}`
- Path Variables:
  - `id` (required): ID of the survey instance
- Response: `200 OK`
- Response Body: `SurveyInstanceDto` object

### GET /api/survey-instance/{id}/instances

Fetches all item instances for a specific survey instance.

- Method: `GET`
- URL: `/api/survey-instance/{id}/instances`
- Path Variables:
  - `id` (required): ID of the survey instance
- Response: `200 OK`
- Response Body: List of `SurveyInstanceDto` objects

### GET /api/survey-instance/state/{state}

Fetches survey instances by their state. If no state is provided, it fetches all survey instances.

- Method: `GET`
- URL: `/api/survey-instance/state/{state}`
- Path Variables:
  - `state` (optional): State of the survey instances
- Response: `200 OK`
- Response Body: List of `SurveyInstanceDto` objects

### GET /api/survey-instance/user/{username}

Fetches all survey instances for a specific user.

- Method: `GET`
- URL: `/api/survey-instance/user/{username}`
- Path Variables:
  - `username` (required): username of the user
- Response: `200 OK`
- Response Body: List of `SurveyInstanceDto`

### PUT /api/survey-instance/{id}

Updates a survey instance by its ID.

- Method: `PUT`
- URL: `/api/survey-instance/{id}`
- Path Variables:
  - `id` (required): ID of the survey instance
- Request Body: `SurveyInstanceDto` object
- Response: `200 OK`
- Response Body: Updated `SurveyInstanceDto` object

### POST /api/survey-instance/create

Creates a new survey instance.

- Method: `POST`
- URL: `/api/survey-instance/create`
- Request Body: `SurveyInstanceDto` object
- Response: `201 Created`
- Response Body: Created `SurveyInstanceDto` object

### DELETE /api/survey-instance/{id}

Deletes a survey instance by its ID.

- Method: `DELETE`
- URL: `/api/survey-instance/{id}`
- Path Variables:
  - `id` (required): ID of the survey instance
- Response: `200 OK`
- Response Body: Deleted `SurveyInstanceDto` object

## SurveyItemInstanceController

### GET /api/surveyItemInstance/{id}

Fetches a specific survey item instance by its ID.

- Method: `GET`
- URL: `/api/surveyItemInstance/{id}`
- Path Variables:
  - `id` (required): ID of the survey item instance
- Response: `200 OK`
- Response Body: `SurveyItemInstanceDto` object

### PUT /api/surveyItemInstance/{id}

Updates a specific survey item instance by its ID.

- Method: `PUT`
- URL: `/api/surveyItemInstance/{id}`
- Path Variables:
  - `id` (required): ID of the survey item instance
- Request Body: `SurveyItemInstanceDto` object
- Response: `200 OK`
- Response Body: Updated `SurveyItemInstanceDto` object

### POST /api/surveyItemInstance/answer

Records an answer for a specific survey item in a specific survey instance.

- Method: `POST`
- URL: `/api/surveyItemInstance/answer`
- Request Parameters:
  - `surveyInstanceId` (required): ID of the survey instance
  - `surveyItemId` (required): ID of the survey item
  - `answer` (required): The answer to record
- Response: `200 OK`
- Response Body: Updated `SurveyItemInstanceDto` object

### DELETE /api/surveyItemInstance/{id}

Deletes a specific survey item instance by its ID.

- Method: `DELETE`
- URL: `/api/surveyItemInstance/{id}`
- Path Variables:
  - `id` (required): ID of the survey item instance
- Response: `200 OK`
- Response Body: Deleted `SurveyItemInstanceDto` object