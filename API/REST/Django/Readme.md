# Django 

REST API framework written in python.

## Company API

### Endpoints

- `/companies` GET all companies
- `/companies/{id}` GET company by id
- `/companies/{id}` PUT update company
- `/companies` POST create new company
- `/companies/{id}/employee` GET all employees by company id
- `/emploees` GET all employees
- `/employees/{id}` GET employee by id
- `/employees/{id}` PUT update employee
- `/employees/{id}` DELETE delete employee
- `/emploees` POST create employee

### Requirements

- Python3
- Django
- Django Rest Framework (DRF)
- MySQL

**Similarities with spring boot**

- models <--> entities : Data models storing data as objects which map to the database and define the schema
- views <--> controllers : Entry points for the API (http calls)
- serializers <--> modelmappers : Convert data from json to objects and vice versa