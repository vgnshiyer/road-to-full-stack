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

**Some differences**

- Apps in django are self contained modules for your project.
- The main idea behind module is to make the apps reusable across the entire project. 
- Each app has its own models, views, settings, serializers, etc.
- There are built in apps provided by django like auth, messages, etc.
- All apps are required to be added to `INSTALLED_APPS` in the projects main settings.py

### Notes:

- Run `python3 manage.py startapp appname` -> creates a new app in the project.
- Create models in models.py inside the app.
- Create serializers in serializers.py file (converts objects to json)
- add views
- setup urls
- finally add app to installed apps
- run `makemigrations` -->  makes migration files based on changes to models --> used by django to track changes to schema
- run `migrate` --> applies the changes tracked in the migration file to the database --> can also be used to undo changes to db schema
- admin panel is used to manage the data in the database and to manage the users of the system

Model --> Data models
Serializers --> convert data
Views/ViewSet --> binds models and serializers
urls --> rest_framework.default router -> provides all http methods for base url (eg. 'companies')
