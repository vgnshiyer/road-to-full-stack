from django.contrib import admin
from studentapi.models import Course, Student, Teacher

# Register your models here.

class CourseAdmin(admin.ModelAdmin):
    list_display = ('name', 'teacher',)
    search_fields = [('name')]
    list_filter = ('teacher', 'student')

class TeacherAdmin(admin.ModelAdmin):
    list_display = ('name',)

class StudentAdmin(admin.ModelAdmin):
    list_display = ('name',)
    list_filter = ('course', )


admin.site.register(Course, CourseAdmin)
admin.site.register(Teacher, TeacherAdmin)
admin.site.register(Student, StudentAdmin)