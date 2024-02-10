from django.contrib import admin
from django.urls import path, include
from rest_framework.routers import DefaultRouter
from randomfactapi.views import RandomFactViewSet

# creates all http methods (create, retrieve, update, partial_update, destroy) 
router = DefaultRouter()
router.register(r'randomfacts', RandomFactViewSet)

urlpatterns = [
    path('', include(router.urls)),
]
