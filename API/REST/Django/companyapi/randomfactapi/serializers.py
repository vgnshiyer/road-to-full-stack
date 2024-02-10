from rest_framework import serializers
from randomfactapi.models import RandomFact

class RandomFactSerializer(serializers.ModelSerializer):
    class Meta:
        model = RandomFact
        fields = '__all__'