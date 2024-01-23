package com.example.mealapp.ui.meals

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.example.mealapp.models.Category


@Composable
fun MealScreen(cardClickEvent: (Category?) -> Unit) {
    val mealViewModel: MealViewModel =
        hiltViewModel() // tied to composable ,will retrieve same instance on recomposition and
    //  we should use this  if @HiltViewModel is scoped to the navigation graph// .
    val mealsData = mealViewModel.mealData.value
    LazyColumn {
        items(mealsData) { meal ->
            MealComponent(data = meal, cardClickEvent)
        }
    }
}

@Composable
private fun MealComponent(data: Category, cardClickEvent: (Category) -> Unit) {
    var isExpanded by remember {
        mutableStateOf(false)
    }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .background(color = Color.White)
            .clickable {
                cardClickEvent.invoke(data)
            },
        elevation = CardDefaults.elevatedCardElevation(),
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {
        Row(
            modifier = Modifier
                .animateContentSize()
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            MealIcon(data.strCategoryThumb)
            MealContent(data, isExpanded)
            MealArrowIcon(
                isExpanded,
                modifier = Modifier
                    .align(if (isExpanded) Alignment.Bottom else Alignment.CenterVertically)
                    .clickable {
                        isExpanded = isExpanded.not()
                    }
                    .padding(5.dp),
            )
        }
    }
}

@Composable
fun MealArrowIcon(expanded: Boolean, modifier: Modifier) {
    Icon(
        imageVector = if (expanded) Icons.Filled.KeyboardArrowUp
        else Icons.Filled.KeyboardArrowDown, contentDescription = "arrowIcon", modifier = modifier
    )
}

@Composable
fun MealContent(data: Category, isExpanded: Boolean) {
    Column(
        modifier = Modifier
            .padding(5.dp)
            .fillMaxWidth(0.8f),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Text(
            text = data.strCategory,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = data.strCategoryDescription,
            modifier = Modifier.padding(top = 2.dp, bottom = 5.dp),
            maxLines = if (isExpanded) 20 else 2,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.bodySmall
        )
    }
}

@Composable
fun MealIcon(strCategoryThumb: String) {
    Image(
        painter = rememberAsyncImagePainter(model = strCategoryThumb),
        contentDescription = "mealIcon",
        modifier = Modifier
            .size(90.dp)
            .padding(5.dp)
    )
}
