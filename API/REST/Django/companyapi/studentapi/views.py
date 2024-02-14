from django.shortcuts import render
from rest_framework import viewsets
from rest_framework.decorators import action
from studentapi.models import Course, Teacher, Student
from studentapi.serializers import CourseSerializer, TeacherSerializer, StudentSerializer
from rest_framework.response import Response

# Create your views here.

class CourseViewSet(viewsets.ModelViewSet):
    queryset = Course.objects.all()
    serializer_class = CourseSerializer

class TeacherViewSet(viewsets.ModelViewSet):
    queryset = Teacher.objects.all()
    serializer_class = TeacherSerializer

    @action(detail=True, methods=['get'])
    def courses(self, request, pk=None):
        try:
            teacher = Teacher.objects.get(pk=pk)
            courses = Course.objects.filter(teacher=teacher)
            courses_serialized = CourseSerializer(courses, many=True, context={'request': request})
            return Response(courses_serialized.data)
        except Exception as e:
            return Response({'error': str(e)})

class StudentViewSet(viewsets.ModelViewSet):
    queryset = Student.objects.all()
    serializer_class = StudentSerializer

    @action(detail=True, methods=['get'])
    def courses(self, request, pk=None):
        try:
            student = Student.objects.get(pk=pk)
            courses = Course.objects.filter(student=student)
            courses_serialized = CourseSerializer(courses, many=True, context={'request': request})
            return Response(courses_serialized.data)
        except Exception as e:
            return Response({'err': str(e)})