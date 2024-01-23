package com.example.mealapp.ui.common

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.mealapp.models.Category


class MealSharedViewModel : ViewModel() {
    private val _selectedCategory = mutableStateOf<Category?>(null)
    val selectedCategory: MutableState<Category?> get() = _selectedCategory

    fun setSelectedCategory(category: Category?) {
        _selectedCategory.value = category
    }
}
