package com.example.mealapp.models

import javax.inject.Inject

class MealRepository @Inject constructor(private val mealApi: MealApi) {

    suspend fun getMealCategoryData() = mealApi.getMealCategoryData()
}