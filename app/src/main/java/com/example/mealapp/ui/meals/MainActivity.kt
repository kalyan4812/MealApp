package com.example.mealapp.ui.meals

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mealapp.ui.common.MealSharedViewModel
import com.example.mealapp.ui.details.MealDetailScreen
import com.example.mealapp.ui.theme.MealAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MealAppTheme {
                MealApp()
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
private fun MealApp() {
    val navController = rememberNavController()
    val sharedViewModel: MealSharedViewModel = viewModel()
    NavHost(navController = navController, startDestination = "mealList") {
        composable(route = "mealList") {
            MealScreen { category ->
                sharedViewModel.setSelectedCategory(category)
                navController.navigate(route = "mealDetail")
            }
        }
        composable(
            route = "mealDetail"
        ) {
            val data = sharedViewModel.selectedCategory.value
            MealDetailScreen(
                data = data
            )
        }
    }
}

