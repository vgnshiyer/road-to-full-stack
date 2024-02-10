from django.http import HttpResponse, JsonResponse

# request param is a must
def home_page(request):
    print("home_page")
    friends = ["Rolf", "Anne", "Charlie"]
    return JsonResponse(friends, safe=False)