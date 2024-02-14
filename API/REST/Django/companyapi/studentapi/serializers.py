from rest_framework import serializers
from studentapi.models import Course, Teacher, Student

class StudentSerializer(serializers.HyperlinkedModelSerializer):
    student_id = serializers.ReadOnlyField()
    class Meta:
        model = Student
        fields = "__all__"

class CourseSerializer(serializers.HyperlinkedModelSerializer):
    course_id = serializers.ReadOnlyField()
    class Meta:
        model = Course
        fields = "__all__"

class TeacherSerializer(serializers.HyperlinkedModelSerializer):
    student_id = serializers.ReadOnlyField()
    class Meta:
        model = Student
        fields = "__all__"