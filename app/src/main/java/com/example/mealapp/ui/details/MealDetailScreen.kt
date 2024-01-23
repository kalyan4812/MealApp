package com.example.mealapp.ui.details

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import androidx.compose.ui.unit.min
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.transform.CircleCropTransformation
import com.example.mealapp.models.Category


@Composable
fun MealDetailScreen(data: Category?) {
    if (data == null) return
    var isExpanded by remember {
        mutableStateOf(false)
    }
    val scrollState = rememberLazyListState()
    val offset = kotlin.math.min(
        1f,
        1f - (remember { derivedStateOf { scrollState.firstVisibleItemScrollOffset / 600 + scrollState.firstVisibleItemIndex } }).value

    ) // collapse for first item and remain same until first item is visible again.
    val cardSize by animateDpAsState(targetValue = max(200.dp, 300.dp * offset), label = "")
    val imageWidthSizeInDp: Dp by
    animateDpAsState(
        targetValue = if (isExpanded) 500.dp else 250.dp,
        label = "imageSize"
    )
    Column(
        modifier = Modifier
            .animateContentSize()
            .fillMaxWidth()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        CardComposable(cardSize, data, imageWidthSizeInDp)
        ButtonComposable {
            isExpanded = isExpanded.not()
        }
        ListComposable(scrollState)
    }
}

@Composable
private fun ButtonComposable(isExpanded: () -> Unit) {
    Button(
        modifier = Modifier.padding(top = 30.dp, bottom = 30.dp),
        onClick = { isExpanded.invoke() }) {
        Text(text = "Change Profile Pic Size")
    }
}

@Composable
private fun ListComposable(scrollState: LazyListState) {
    val dummyData = listOf(
        "abc",
        "def",
        "abc",
        "def",
        "abc",
        "def",
        "abc",
        "def",
        "abc",
        "def",
        "abc",
        "def",
        "abc",
        "def",
        "abc",
        "def",
        "abc",
        "def",
        "abc",
        "def",
        "abc",
        "def",
        "abc",
        "def",
        "abc",
        "def",
    )
    LazyColumn(state = scrollState) {
        items(dummyData) {
            Column(
                modifier = Modifier.height(50.dp),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 20.dp, end = 20.dp),
                    text = it,
                    textAlign = TextAlign.Start
                )

                Divider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(1.dp)
                        .background(Color.Gray)
                )
            }
        }
    }
}

@Composable
private fun CardComposable(
    cardSize: Dp,
    data: Category,
    imageWidthSizeInDp: Dp
) {
    Card(
        shape = RoundedCornerShape(10.dp),
        border = BorderStroke(2.dp, color = Color.LightGray)
    ) {
        Column(
            modifier = Modifier
                .wrapContentWidth()
                .height(cardSize),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Image(
                painter = rememberAsyncImagePainter(
                    ImageRequest.Builder(LocalContext.current)
                        .data(data = data.strCategoryThumb)
                        .apply(block = fun ImageRequest.Builder.() {
                            transformations(CircleCropTransformation())
                        }).build()
                ),
                contentDescription = "mealDetailImage",
                modifier = Modifier.size(width = imageWidthSizeInDp, height = 200.dp)
            )
            Text(text = data.strCategory)
        }
    }
}