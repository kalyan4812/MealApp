package com.example.mealapp.ui.meals

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mealapp.models.Category
import com.example.mealapp.models.MealRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MealViewModel @Inject constructor(private val repository: MealRepository) : ViewModel() {

    init {
        getMealCategoryData()
    }

    val mealData: MutableState<ArrayList<Category>> = mutableStateOf(arrayListOf())


    private fun getMealCategoryData() {
        viewModelScope.launch(Dispatchers.IO) {
            val data = repository.getMealCategoryData()
            mealData.value = data?.categories ?: arrayListOf()
        }
    }
}