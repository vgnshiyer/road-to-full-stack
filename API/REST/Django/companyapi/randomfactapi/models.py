from django.db import models

# Create your models here.

class RandomFact(models.Model):
    fact = models.CharField(max_length=300)

    def __str__(self):
        return self.fact