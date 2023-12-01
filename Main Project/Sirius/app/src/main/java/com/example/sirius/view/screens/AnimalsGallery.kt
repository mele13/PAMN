package com.example.sirius.view.screens

import android.annotation.SuppressLint
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.sirius.model.Animal
import com.example.sirius.navigation.Routes
import com.example.sirius.ui.theme.Gold
import com.example.sirius.ui.theme.Green1
import com.example.sirius.ui.theme.Orange
import com.example.sirius.viewmodel.AnimalViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AnimalsGallery(
    navController: NavController,
    ageList: List<String>,
    breedList: List<String>,
    typeList: List<String>
) {

    val viewModel: AnimalViewModel = viewModel(factory = AnimalViewModel.factory)

    var selectedAge by remember { mutableStateOf("") }
    var selectedBreed by remember { mutableStateOf("") }
    var selectedType by remember { mutableStateOf("") }

    var ageDropdownExpanded by remember { mutableStateOf(false) }
    var breedDropdownExpanded by remember { mutableStateOf(false) }
    var typeDropdownExpanded by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            DropdownButton(
                text = "Arrival year",
                options = ageList.map { it.toString() },
                selectedOption = selectedAge,
                onOptionSelected = { selectedAge = it },
                expanded = ageDropdownExpanded,
                onExpandedChange = { expanded ->
                    ageDropdownExpanded = expanded
                },
                viewModel = viewModel,
                originalText = "Arrival year",
                color = Color.White,
            )

            ClearFilterIconButton(
                onClick = { selectedAge = "" },
                onOptionSelected = { selectedAge = it },
                selectedOption = selectedAge
            )

            DropdownButton(
                text = "Breed",
                options = breedList.map { it },
                selectedOption = selectedBreed,
                onOptionSelected = { selectedBreed = it },
                expanded = breedDropdownExpanded,
                onExpandedChange = { expanded ->
                    breedDropdownExpanded = expanded
                },
                viewModel = viewModel,
                originalText = "Breed",
                color = Color.White,
            )

            ClearFilterIconButton(
                onClick = { selectedBreed = "" },
                onOptionSelected = { selectedBreed = it },
                selectedOption = selectedBreed
            )

            DropdownButton(
                text = "Type",
                options = typeList.map { it },
                selectedOption = selectedType,
                onOptionSelected = { selectedType = it },
                expanded = typeDropdownExpanded,
                onExpandedChange = { expanded ->
                    typeDropdownExpanded = expanded
                },
                viewModel = viewModel,
                originalText = "Type",
                color = Color.White,
            )

            ClearFilterIconButton(
                onClick = { selectedType = "" },
                onOptionSelected = { selectedType = it },
                selectedOption = selectedType
            )
        }

        val animalsByAge = if (selectedAge.isNotBlank()) {
            val age = selectedAge
            if (age != null) {
                viewModel.getAnimalsByAgeDesc(age).collectAsState(emptyList()).value
            } else {
                emptyList()
            }
        } else {
            viewModel.getAllAnimals().collectAsState(emptyList()).value
        }

        val animalsByBreed = if (selectedBreed.isNotBlank()) {
            viewModel.getAnimalsByBreed(selectedBreed).collectAsState(emptyList()).value
        } else {
            viewModel.getAllAnimals().collectAsState(emptyList()).value
        }

        val animalsByType = if (selectedType.isNotBlank()) {
            viewModel.getAnimalsByTypeAnimal(selectedType).collectAsState(emptyList()).value
        } else {
            viewModel.getAllAnimals().collectAsState(emptyList()).value
        }

        val filteredAnimals = animalsByAge.intersect(animalsByBreed.toSet()).intersect(animalsByType.toSet())
        println("filteredAnimals: ${filteredAnimals}")
        val columns = 2 // Número de columnas en el grid

        LazyVerticalGrid(
            columns = GridCells.Fixed(columns),
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(filteredAnimals.size) { index ->
                val animal = filteredAnimals.elementAtOrNull(index)
                animal?.let { animal ->
                    AnimalCard(
                        animal = animal,
                        navController = navController
                    )
                }
            }
        }
    }
}

@Composable
fun ClearFilterIconButton(
    onClick: () -> Unit,
    onOptionSelected: (String) -> Unit,
    selectedOption: String
) {
    if (selectedOption.isNotBlank()) {
        IconButton(
            onClick = {
                println("Clear filter clicked")
                onClick()
                onOptionSelected("")
            },
            modifier = Modifier
                .padding(start = 8.dp)
                .offset(x = (-16).dp)
        ) {
            Icon(imageVector = Icons.Default.Clear, contentDescription = null)
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DropdownButton(
    text: String,
    options: List<String>,
    selectedOption: String,
    onOptionSelected: (String) -> Unit,
    expanded: Boolean,
    onExpandedChange: (Boolean) -> Unit,
    viewModel: AnimalViewModel,
    originalText: String,
    color: Color,
) {
    Box {
        Button(
            onClick = { onExpandedChange(!expanded) },
            modifier = Modifier
                .height(50.dp)
                .padding(5.dp),
            shape = RoundedCornerShape(20.dp),
            colors = ButtonDefaults.buttonColors(Gold),
            contentPadding = PaddingValues(5.dp)
        ) {
            Text(
                text = selectedOption.ifBlank { originalText },
                color = color
            )
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { onExpandedChange(false) },
            modifier = Modifier
                .width(IntrinsicSize.Max)
                .background(Gold)
                .border(1.dp, Color.Black)
                .clip(RoundedCornerShape(20.dp))
        ) {
            options.forEachIndexed { index, option ->
                if (index > 0) {
                    Divider(color = Color.Black, thickness = 1.dp)
                }
                DropdownMenuItem(
                    {
                        Text(text = option)
                    },
                    onClick = {
                        when (text) {
                            "Arrival year" -> {
                                if (option.isNotBlank()) {
                                    val year = getYearFromStringDate(option)
                                    print(year)
                                    viewModel.getAnimalsByAgeDesc(year)
                                }
                            }


                            "Breed" -> {
                                if (option.isNotBlank()) {
                                    viewModel.getAnimalsByBreed(option)
                                }
                            }

                            "Type" -> {
                                if (option.isNotBlank()) {
                                    viewModel.getAnimalsByTypeAnimal(option)
                                }
                            }
                        }
                        onOptionSelected(option)
                        onExpandedChange(false)
                    }
                )
            }
        }
    }
}

@SuppressLint("DiscouragedApi")
@Composable
fun AnimalCard(animal: Animal,
               navController: NavController,
) {

    var isFavorite by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(0.65f)
            .padding(horizontal = 8.dp, vertical = 4.dp)
            .clickable {
                navController.navigate(route = Routes.ANIMALINFO + "/" + animal.id)
            },
        colors = CardDefaults.cardColors(
            containerColor = Green1,
        ),
        border = BorderStroke(2.dp, Gold),
        shape = MaterialTheme.shapes.medium,

        ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .clip(MaterialTheme.shapes.medium),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .padding(4.dp)
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
                        contentDescription = animal.shortInfoAnimal,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxSize()
                            .aspectRatio(1f)
                    )
                } else {
                    Log.e("AnimalImage", "Recurso no encontrado para ${animal.photoAnimal}")
                }
                if (isFavorite) {
                    Icon(
                        imageVector = Icons.Default.Favorite,
                        contentDescription = null,
                        tint = Color.Black,
                        modifier = Modifier.align(Alignment.TopEnd)
                            .clickable { isFavorite = !isFavorite }
                    )
                } else {
                    Icon(
                        imageVector = Icons.Default.FavoriteBorder,
                        contentDescription = null,
                        tint = Color.Black,
                        modifier = Modifier.align(Alignment.TopEnd)
                            .clickable { isFavorite = !isFavorite }
                    )
                }
            }
            Spacer(Modifier.padding(8.dp))
            Column(
                modifier = Modifier.padding(8.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                // Texto de adopción
                val adoptionText = if (animal.waitingAdoption == 1) {
                    "Adoption"
                } else {
                    "Pre Adoption"
                }
                Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                    Text(
                        text = adoptionText,
                        style = TextStyle(
                            fontSize = 10.sp,
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        ),
                        modifier = Modifier
                            .background(color = Orange, shape = RoundedCornerShape(4.dp))
                            .padding(horizontal = 2.dp, vertical = 4.dp)
                    )

                    if (animal.fosterCare == 1) {
                        Text(
                            text = "In Foster Care",
                            style = TextStyle(
                                fontSize = 10.sp,
                                color = Color.White,
                                fontWeight = FontWeight.Bold
                            ),
                            modifier = Modifier
                                .background(color = Orange, shape = RoundedCornerShape(4.dp))
                                .padding(horizontal = 2.dp, vertical = 4.dp)
                        )
                    }
                }

                Text(
                    text = animal.shortInfoAnimal,
                    style = MaterialTheme.typography.bodyMedium,
                    textAlign = TextAlign.Center,
                    color = Color.Black,
                    modifier = Modifier
                        .padding(5.dp)
                        .align(Alignment.CenterHorizontally)
                )
            }
        }
    }
}


fun getYearFromStringDate(dateString: String): String {
    // Extrae los primeros cuatro caracteres de la cadena (el año)
    return dateString.take(4)
}
