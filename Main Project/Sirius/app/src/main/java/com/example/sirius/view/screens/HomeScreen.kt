package com.example.sirius.view.screens

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.sirius.R
import com.example.sirius.model.News
import com.example.sirius.model.Animal
import com.example.sirius.navigation.Routes
import com.example.sirius.viewmodel.UserViewModel

@SuppressLint("CoroutineCreationDuringComposition", "DiscouragedApi")
@Composable
fun HomeScreen(
    navController: NavController,
    animalList: List<Animal>,
    newsList: List<News>,
    imageList: List<Int>,
    userViewModel: UserViewModel
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
        ) {
            item {
                // What's New
                Text(
                    text = stringResource(id = R.string.newsIntro),
                    style = MaterialTheme.typography.headlineMedium,
                    modifier = Modifier.padding(6.dp)
                )
                LazyRow {
                    items(newsList) {news ->
                        Column(
                            modifier = Modifier.padding(4.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            val context = LocalContext.current

                            // Obtener el nombre del recurso sin la ruta
                            val resourceName = news.photoNews.substringAfterLast("/")

                            // Obtener el ID del recurso sin la ruta
                            val resourceId = context.resources.getIdentifier(
                                resourceName.replace(".jpg", ""), "drawable", context.packageName
                            )

                            if (resourceId != 0) {
                                // Si se encontró el recurso, cargar la imagen
                                val painter = painterResource(id = resourceId)
                                Image(
                                    painter = painter,
                                    contentDescription = null,
                                    modifier = Modifier.size(100.dp)
                                )
                            } else {
                                Log.e("AnimalImage", "Recurso no encontrado para ${news.photoNews}")
                            }
//                            Image(
//                                painter = painterResource(R.drawable.dog1),
//                                contentDescription = null,
//                                modifier = Modifier
//                                    .size(100.dp)
//                                    .clip(MaterialTheme.shapes.medium)
//                            )
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
                            modifier = Modifier.padding(4.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,

                            ) {
                            val context = LocalContext.current

                            // Obtener el nombre del recurso sin la ruta
                            val resourceName = animal.photoAnimal.substringAfterLast("/")

                            // Obtener el ID del recurso sin la ruta
                            val resourceId = context.resources.getIdentifier(
                                resourceName.replace(".jpg", ""), "drawable", context.packageName
                            )

                            if (resourceId != 0) {
                                // Si se encontró el recurso, cargar la imagen
                                val painter = painterResource(id = resourceId)
                                Image(
                                    painter = painter,
                                    contentDescription = null,
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .size(100.dp)
                                        .clickable {
                                            navController.navigate(route = Routes.ANIMALINFO + "/" + animal.id)
                                        }
                                )
                            } else {
                                Log.e("AnimalImage", "Recurso no encontrado para ${animal.photoAnimal}")
                            }
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
                    items(newsList) {new ->
                        Column(
                            modifier = Modifier.padding(4.dp),
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
                                text = new.titleNews,
                                style = MaterialTheme.typography.labelLarge,
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }
            }
        }
    }
}
