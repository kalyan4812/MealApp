package com.example.mealapp.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MealCategoryData(
    val categories: ArrayList<Category>?
):Parcelable