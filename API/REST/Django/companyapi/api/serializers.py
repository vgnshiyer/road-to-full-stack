from rest_framework import serializers
from api.models import Company

class CompanySerializer(serializers.HyperlinkedModelSerializer):
    company_id = serializers.ReadOnlyField()
    class Meta:
        model = Company
        # fields to be included while serializing
        fields = "__all__"