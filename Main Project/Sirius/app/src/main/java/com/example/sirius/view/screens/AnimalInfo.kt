package com.example.sirius.view.screens

import android.annotation.SuppressLint
import android.content.res.Configuration
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
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
import androidx.navigation.NavController
import com.example.sirius.R
import com.example.sirius.ui.theme.Orange
import com.example.sirius.viewmodel.AnimalViewModel
import java.time.Year

@SuppressLint("DiscouragedApi")
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AnimalInfo(navController: NavController, id: Int?, viewModel: AnimalViewModel) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        var isFavorite by remember { mutableStateOf(false) }
        val animal by viewModel.getAnimalById(id ?: 0).collectAsState(initial = null)
        val isSystemInDarkTheme =
            (LocalContext.current.resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) == Configuration.UI_MODE_NIGHT_YES

        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxHeight(0.75f)
            ){
                val animal by viewModel.getAnimalById(id ?: 0).collectAsState(initial = null)
                if (animal != null) {
                    val context = LocalContext.current

                    // Obtener el nombre del recurso sin la ruta
                    val resourceName = animal!!.photoAnimal.substringAfterLast("/")

                    // Obtener el ID del recurso sin la ruta
                    val resourceId = context.resources.getIdentifier(
                        resourceName.replace(".jpg", ""), "drawable", context.packageName
                    )

                    if (resourceId != 0) {
                        // Si se encontró el recurso, cargar la imagen
                        val painter = painterResource(id = resourceId)
                        Image(
                            painter = painter,
                            contentDescription = animal!!.longInfoAnimal,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxSize()
                        )
                    } else {
                        Log.e(
                            "AnimalImage",
                            "Recurso no encontrado para ${animal!!.photoAnimal}"
                        )
                    }
                }

                Column (
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp, bottom = 25.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Bottom
                ){
                    Row (
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        // Botón sobre la imagen
                        Button(
                            onClick = { },
                            modifier = Modifier
                                .width(200.dp),
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
                        if (isFavorite) {
                            Icon(
                                imageVector = Icons.Default.Favorite,
                                contentDescription = null,
                                tint = Color.Black,
                                modifier = Modifier
                                    .clickable { isFavorite = !isFavorite }
                            )
                        } else {
                            Icon(
                                imageVector = Icons.Default.FavoriteBorder,
                                contentDescription = null,
                                tint = Color.Black,
                                modifier = Modifier
                                    .clickable { isFavorite = !isFavorite }
                            )
                        }
                    }
                }
            }
        }
        Column (
            verticalArrangement = Arrangement.Bottom
        ) {
            Image(
                painter = painterResource(id = R.drawable.rectangle),
                contentDescription = "rectangle",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.32f),
                colorFilter = ColorFilter.tint(color = if (!isSystemInDarkTheme) Color.White else Color.Black)
            )
        }

        Column (
            verticalArrangement = Arrangement.Bottom,
            modifier = Modifier
                .padding(bottom = 35.dp)
        ) {
            if (animal != null) {
                Text(
                    text = animal!!.nameAnimal,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Start,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 20.dp)
                )
                Text(
                    text = animal!!.longInfoAnimal,
                    style = MaterialTheme.typography.bodyMedium,
                    textAlign = TextAlign.Start,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 20.dp)
                )
                val birthYear = animal!!.birthDate.substring(0, 4).toInt()
                val currentYear = Year.now().value
                var age = currentYear - birthYear
                if (age == 0) {
                    age = animal!!.birthDate.substring(6, 7).toInt()
                    Text(
                        text = "Age: $age months",
                        style = MaterialTheme.typography.bodyMedium,
                        textAlign = TextAlign.Start,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 20.dp)
                    )
                } else {
                    Text(
                        text = "Age: $age years",
                        style = MaterialTheme.typography.bodyMedium,
                        textAlign = TextAlign.Start,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 20.dp)
                    )
                }

                IconButton(
                    onClick = {
                        navController.popBackStack()
                    },
                    modifier = Modifier
                        .padding(start = 8.dp)
                        .offset(x = (-16).dp)
                ) {
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
                }
            }
        }
    }
}
