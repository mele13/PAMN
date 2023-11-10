package com.example.sirius.view.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.sirius.R
import com.example.sirius.model.News
import com.example.sirius.model.Animal
import com.example.sirius.viewmodel.AnimalViewModel
import com.example.sirius.viewmodel.NewsViewModel
import kotlinx.coroutines.flow.map
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun HomeScreen(animalList: List<Animal>, newsList: List<News>, imageList: List<Int>) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
        ) {
            // What's up
            Text(
                text = stringResource(id = R.string.animalsIntro),
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(6.dp)
            )
            LazyRow {
                items(newsList) {news ->
                    Column(
                        modifier = Modifier.padding(8.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(
                            painter = painterResource(R.drawable.dog1),
                            contentDescription = null,
                            modifier = Modifier
                                .size(100.dp)
                                .clip(MaterialTheme.shapes.medium)
                        )
                        Text(
                            text = news.titleNews,
                            style = MaterialTheme.typography.labelLarge,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
            // Our friends
            Text(
                text = stringResource(id = R.string.animalsIntro),
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(6.dp)
            )
            LazyRow {
                items(animalList) {animal ->
                    Column(
                        modifier = Modifier.padding(8.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(
                            painter = painterResource(R.drawable.dog1),
                            contentDescription = null,
                            modifier = Modifier
                                .size(100.dp)
                                .clip(MaterialTheme.shapes.medium)
                        )
                        Text(
                            text = animal.nameAnimal,
                            style = MaterialTheme.typography.labelLarge,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
            // Lost
            Text(
                text = stringResource(id = R.string.lostIntro),
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(6.dp)
            )
            LazyRow {
                items(imageList) {imageResource ->
                    Image(
                        painter = painterResource(imageResource),
                        contentDescription = null,
                        modifier = Modifier
                            .padding(6.dp)
                            .size(100.dp)
                            .clip(MaterialTheme.shapes.medium)
                    )
                }
            }
            // Good News
            Text(
                text = stringResource(id = R.string.goodNewsIntro),
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(6.dp)
            )
            LazyRow {
                items(imageList) {imageResource ->
                    Image(
                        painter = painterResource(imageResource),
                        contentDescription = null,
                        modifier = Modifier
                            .padding(8.dp)
                            .size(100.dp)
                            .clip(MaterialTheme.shapes.medium)
                    )
                }
            }
        }
    }
}

@Composable
fun NewsItem(news: News) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp)
    ) {
        Text(
            text = news.titleNews,
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.fillMaxWidth()
        )
        Text(
            text = news.shortInfoNews,
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.fillMaxWidth()
        )
        Text(
            text = "Published Date: ${news.publishedDate}",
//            style = MaterialTheme.typography.caption,
            modifier = Modifier.fillMaxWidth()
        )
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

    val animalVm: AnimalViewModel = viewModel(factory = AnimalViewModel.factory)
    val animalList by animalVm.getAllAnimals().collectAsState(initial = emptyList())

    val newsVm: NewsViewModel = viewModel(factory = NewsViewModel.factory)
    val newsList by newsVm.getNews().collectAsState(initial = emptyList())

    HomeScreen(animalList, newsList, imageList)
}