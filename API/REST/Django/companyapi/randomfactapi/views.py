from django.shortcuts import render
from rest_framework import viewsets
from randomfactapi.models import RandomFact
from randomfactapi.serializers import RandomFactSerializer

# Create your views here.
class RandomFactViewSet(viewsets.ModelViewSet):
    queryset = RandomFact.objects.all()
    serializer_class = RandomFactSerializer