from django.shortcuts import render
from rest_framework import viewsets
from rest_framework.decorators import action
from api.models import Company, Employee
from api.serializers import CompanySerializer, EmployeeSerializer
from rest_framework.response import Response

# Create your views here.
class CompanyViewSet(viewsets.ModelViewSet):
    queryset = Company.objects.all()
    serializer_class = CompanySerializer

    # add custom url to get employees of a company (company/1/employees)
    # detail=True means it will be a url for a single company -> must have a company id in the url
    @action(detail=True, methods=['get'])
    def employees(self, request, pk=None):
        try:
            company = Company.objects.get(pk=pk)
            emps = Employee.objects.filter(company=company)
            emps_serialized = EmployeeSerializer(emps, many=True, context={'request': request})
            return Response(emps_serialized.data)
        except Exception as e:
            return Response({'error': str(e)})

class EmployeeViewSet(viewsets.ModelViewSet):
    queryset = Employee.objects.all()
    serializer_class = EmployeeSerializer