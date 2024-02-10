from django.contrib import admin
from randomfactapi.models import RandomFact

# Register your models here.

class RandomFactAdmin(admin.ModelAdmin):
    list_display = ('fact',)
    search_fields = [('fact')]

admin.site.register(RandomFact, RandomFactAdmin)