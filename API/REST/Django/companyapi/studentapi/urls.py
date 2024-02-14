from django.contrib import admin
from django.urls import path, include
from studentapi.views import CourseViewSet, StudentViewSet, TeacherViewSet
from rest_framework.routers import DefaultRouter

router = DefaultRouter()
router.register(r'courses', CourseViewSet)
router.register(r'students', StudentViewSet)
router.register(r'teachers', TeacherViewSet)

urlpatterns = [
    path('', include(router.urls)),
]