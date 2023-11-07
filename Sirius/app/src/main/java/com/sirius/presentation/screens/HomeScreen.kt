package com.sirius.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sirius.R

@Composable
fun HomeScreen(imageList: List<Int>) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text(
                text = stringResource(id = R.string.newsIntro),
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(8.dp)
            )
            LazyRow {
                items(imageList) {imageResource ->
                    Image(
                        painter = painterResource(imageResource),
                        contentDescription = null,
                        modifier = Modifier
                            .padding(8.dp)
                            .size(120.dp)
                            .clip(MaterialTheme.shapes.medium)
                    )
                }
            }
        }
    }
}


@Preview
@Composable
fun HomeScreenPreview() {
    val imageList = listOf(
        R.drawable.dog1,
        R.drawable.dog1,
        R.drawable.dog1,
        R.drawable.dog1,
    )
    HomeScreen(imageList)
}
