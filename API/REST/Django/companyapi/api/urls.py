from django.contrib import admin
from django.urls import path, include
from api.views import CompanyViewSet
from rest_framework.routers import DefaultRouter

# creates all http methods (create, retrieve, update, partial_update, destroy) 
router = DefaultRouter()
router.register(r'companies', CompanyViewSet)

urlpatterns = [
    path('', include(router.urls)),
]
