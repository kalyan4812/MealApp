package com.example.mealapp.models

import retrofit2.http.GET
import java.lang.reflect.Array


interface MealApi {

    @GET("categories.php")
    suspend fun getMealCategoryData(): MealCategoryData?
}