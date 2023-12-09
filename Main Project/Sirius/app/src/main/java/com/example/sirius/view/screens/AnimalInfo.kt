package com.example.sirius.view.screens

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.Configuration
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.sirius.R
import com.example.sirius.model.Animal
import com.example.sirius.ui.theme.Orange
import com.example.sirius.ui.theme.Wine
import com.example.sirius.viewmodel.AnimalViewModel
import com.example.sirius.viewmodel.UserViewModel
import kotlinx.coroutines.launch
import java.time.Year

@SuppressLint("DiscouragedApi", "CoroutineCreationDuringComposition")
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AnimalInfo(
    navController: NavController,
    id: Int?,
    viewModel: AnimalViewModel,
    userViewModel: UserViewModel
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        var isFavorite by remember { mutableStateOf(false) }
        val animal by viewModel.getAnimalById(id ?: 0).collectAsState(initial = null)
        val context = LocalContext.current
        val userId = userViewModel.getAuthenticatedUser()?.id

        if (userId != null) {
            userViewModel.viewModelScope.launch {
                userViewModel.getLikedAnimals(userId).collect { likedAnimals ->
                    isFavorite = likedAnimals.any { it.id == (animal?.id ?: -1) }
                }
            }
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            LazyColumn(
                verticalArrangement = Arrangement.Bottom
            ) {
                if (animal != null) {
                    val photoPaths = animal!!.photoAnimal.split(", ").map { it.trim() }
                    item {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(
                                    color = Color.White
                                )
                        ) {
                            CarouselSlider(photoPaths, animal!!, context)
                            Image(
                                painter = painterResource(id = R.drawable.rectangle2),
                                contentDescription = "rectangle",
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .fillMaxHeight(0.32f)
                                    .align(Alignment.BottomStart),
                                colorFilter = ColorFilter.tint(color = colorScheme.background),
                            )
                            // Botón "Adopt me"
                            Button(
                                onClick = { /* Handle adoption */ },
                                modifier = Modifier
                                    .width(200.dp)
                                    .align(Alignment.BottomCenter)
                                    .offset(y = (-25).dp),
                                colors = ButtonDefaults.buttonColors(Orange)
                            ) {
                                Text(
                                    text = "Adopt me!",
                                    style = TextStyle(
                                        fontSize = 13.sp,
                                        fontWeight = FontWeight(400),
                                        color = Color(0xFFFFFFFF),
                                    ),
                                    textAlign = TextAlign.Center
                                )
                            }
                            // Icono de favorito
                            if (userId != null) {
                                if (isFavorite) {
                                    Icon(
                                        imageVector = Icons.Default.Favorite,
                                        contentDescription = null,
                                        tint = Wine,
                                        modifier = Modifier
                                            .align(Alignment.BottomEnd)
                                            .padding(end = 16.dp)
                                            .offset(x = (-40).dp, y = (-37).dp)
                                            .clickable {
                                                isFavorite = !isFavorite
                                                userViewModel.viewModelScope.launch {
                                                    viewModel.removeLikedAnimal(
                                                        animalId = animal!!.id,
                                                        userId = userId
                                                    )
                                                }
                                            }
                                    )
                                } else {
                                    Icon(
                                        imageVector = Icons.Default.FavoriteBorder,
                                        contentDescription = null,
                                        tint = Wine,
                                        modifier = Modifier
                                            .align(Alignment.BottomEnd)
                                            .padding(end = 16.dp)
                                            .offset(x = (-40).dp, y = (-37).dp)
                                            .clickable {
                                                isFavorite = !isFavorite
                                                userViewModel.viewModelScope.launch {
                                                    viewModel.insertLikedAnimal(
                                                        animalId = animal!!.id,
                                                        userId = userId
                                                    )
                                                }
                                            }
                                    )
                                }
                            }
                        }
                    }
                    item {
                        Text(
                            text = animal!!.nameAnimal,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Start,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 20.dp)
                        )
                    }
                    item {
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = animal!!.longInfoAnimal,
                            style = MaterialTheme.typography.bodyMedium,
                            textAlign = TextAlign.Start,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 20.dp, end = 20.dp)
                        )
                    }
                    val birthYear = animal!!.birthDate.substring(0, 4).toInt()
                    val currentYear = Year.now().value
                    var age = currentYear - birthYear
                    if (age == 0) {
                        age = animal!!.birthDate.substring(6, 7).toInt()
                        item {
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = "Age: ${pluralize(age, "month", "months")}",
                                style = MaterialTheme.typography.bodyMedium,
                                textAlign = TextAlign.Start,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(start = 20.dp)
                            )
                        }
                    } else {
                        item {
                            Spacer(modifier = Modifier.height(12.dp))
                            Text(
                                text = "Age: ${pluralize(age, "year", "years")}",
                                style = MaterialTheme.typography.bodyMedium,
                                textAlign = TextAlign.Start,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(start = 20.dp)
                            )
                        }
                    }
//                    item {
//                        IconButton(
//                            onClick = {
//                                navController.popBackStack()
//                            },
//                            modifier = Modifier
//                                .padding(start = 8.dp)
//                                .offset(x = (-16).dp)
//                        ) {
//                            Icon(
//                                imageVector = Icons.Default.ArrowBack,
//                                contentDescription = null
//                            )
//                        }
//                    }
                }
            }
        }
    }
}

@SuppressLint("DiscouragedApi")
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CarouselSlider(photoPaths: List<String>, animal: Animal, context: Context) {
    val pagerState = rememberPagerState()
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        HorizontalPager(
            pageCount = photoPaths.size,
            state = pagerState,
            key = { photoPaths[it] }
        ) { index ->
            val resourceName = photoPaths[index].substringAfterLast("/").replace(".jpg", "")
            val resourceId = context.resources.getIdentifier(
                resourceName, "drawable", context.packageName
            )
            if(resourceId != 0) {
                GetImage(painter = resourceId, description = animal.shortInfoAnimal)
            } else {
                GetImage(painter = R.drawable.image_not_found, description = animal.shortInfoAnimal)
            }
        }
    }
}

@Composable
fun GetImage(painter: Int, description: String) {
    Image(
        painter = painterResource(id = painter),
        contentDescription = description,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .fillMaxSize()
    )
}

fun pluralize(value: Int, singular: String, plural: String): String {
    return if (value == 1) "$value $singular" else "$value $plural"
}

