package com.example.sirius.view.screens

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
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.sirius.R
import com.example.sirius.model.Animal
import com.example.sirius.ui.theme.Gold
import com.example.sirius.ui.theme.Green1
import com.example.sirius.viewmodel.navigation.AnimalViewModel

@Composable
fun AnimalsGallery(
    animalList: List<Animal>,
    ageList: List<Int>,
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
                text = "Age",
                options = ageList.map { it.toString() },
                selectedOption = selectedAge,
                onOptionSelected = { selectedAge = it },
                expanded = ageDropdownExpanded,
                onExpandedChange = { expanded ->
                    ageDropdownExpanded = expanded
                },
                viewModel = viewModel,
                originalText = "Age"
            )

            ClearFilterIconButton(
                onClick = { selectedAge = "" },
                onOptionSelected = { selectedAge = it },
                selectedOption = selectedAge
            )

            DropdownButton(
                text = "Breed",
                options = breedList.map { it.toString() },
                selectedOption = selectedBreed,
                onOptionSelected = { selectedBreed = it },
                expanded = breedDropdownExpanded,
                onExpandedChange = { expanded ->
                    breedDropdownExpanded = expanded
                },
                viewModel = viewModel,
                originalText = "Breed"
            )

            ClearFilterIconButton(
                onClick = { selectedBreed = "" },
                onOptionSelected = { selectedBreed = it },
                selectedOption = selectedBreed
            )

            DropdownButton(
                text = "Type",
                options = typeList.map { it.toString() },
                selectedOption = selectedType,
                onOptionSelected = { selectedType = it },
                expanded = typeDropdownExpanded,
                onExpandedChange = { expanded ->
                    typeDropdownExpanded = expanded
                },
                viewModel = viewModel,
                originalText = "Type"
            )

            ClearFilterIconButton(
                onClick = { selectedType = "" },
                onOptionSelected = { selectedType = it },
                selectedOption = selectedType
            )
        }

        val animalsByAge = if (selectedAge.isNotBlank()) {
            val age = selectedAge.toIntOrNull()
            if (age != null) {
                viewModel.getAnimalsByAgeASC(selectedAge.toInt()).collectAsState(emptyList()).value
            } else {
                emptyList()
            }
        } else {
            viewModel.getAllAnimals().collectAsState(emptyList()).value
        }

        val animalsByBreed = if (selectedBreed.isNotBlank()) {
            viewModel.getAnimalsByBreed(selectedBreed).collectAsState(emptyList()).value
        } else {
            viewModel.getAllAnimals().collectAsState(emptyList()).value // Otra acción apropiada si no se ha seleccionado una raza
        }

        val animalsByType = if (selectedType.isNotBlank()) {
            viewModel.getAnimalsByTypeAnimal(selectedType).collectAsState(emptyList()).value
        } else {
            viewModel.getAllAnimals().collectAsState(emptyList()).value// Otra acción apropiada si no se ha seleccionado un tipo
        }

        val filteredAnimals = animalsByAge.intersect(animalsByBreed.toSet()).intersect(animalsByType.toSet())

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
        ) {
            items(filteredAnimals.chunked(2)) { chunk ->
                LazyRow(
                    contentPadding = PaddingValues(20.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    items(chunk) { animal ->
                        AnimalCard(animal = animal)
                    }
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

@Composable
fun DropdownButton(
    text: String,
    options: List<String>,
    selectedOption: String,
    onOptionSelected: (String) -> Unit,
    expanded: Boolean,
    onExpandedChange: (Boolean) -> Unit,
    viewModel: AnimalViewModel,
    originalText: String // Add originalText parameter
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
            Text(text = if (selectedOption.isNotBlank()) selectedOption else originalText)
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
                            "Age" -> {
                                if (option.isNotBlank()) {
                                    val result = viewModel.getAnimalsByAgeASC(option.toInt())
                                    println("Consulta por edad: $result")
                                } else {
                                    println("La opción seleccionada está vacía")
                                }
                            }

                            "Breed" -> {
                                if (option.isNotBlank()) {
                                    val result = viewModel.getAnimalsByBreed(option)
                                    println("Consulta por raza: $result")
                                } else {
                                    println("La opción seleccionada está vacía")
                                }

                            }

                            "Type" -> {
                                if (option.isNotBlank()) {
                                    val result = viewModel.getAnimalsByTypeAnimal(option)
                                    println("Consulta por tipo: $result")
                                } else {
                                    println("La opción seleccionada está vacía")
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


@Composable
fun AnimalCard(animal: Animal) {
    var isFavorite by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f),
        colors = CardDefaults.cardColors(
            containerColor = Green1,
        ),
        border = BorderStroke(2.dp, Gold),
        shape = MaterialTheme.shapes.medium,
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .width(152.dp)
                .clip(MaterialTheme.shapes.medium)
        ) {
            Box(
                modifier = Modifier
                    .padding(4.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.dog1),
                    contentDescription = animal.shortInfoAnimal,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxSize()
                        .aspectRatio(1f)
                )
                if (isFavorite) {
                    Icon(
                        imageVector = Icons.Default.Favorite,
                        contentDescription = null,
                        tint = Color.Black,
                        modifier = Modifier.align(Alignment.TopEnd)
                            .clickable {isFavorite = !isFavorite}
                    )
                } else {
                    Icon(
                        imageVector = Icons.Default.FavoriteBorder,
                        contentDescription = null,
                        tint = Color.Black,
                        modifier = Modifier.align(Alignment.TopEnd)
                            .clickable {isFavorite = !isFavorite}
                    )
                }
            }

            Text(
                text = animal.shortInfoAnimal,
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center,
                color = Color.Black
            )
        }
    }
}

@Composable
fun generateSampleAnimalList(viewModel: AnimalViewModel): List<Animal> {
    val animalList by viewModel.getAllAnimals().collectAsState(emptyList())
    return animalList.map { animal ->
        Animal(
            id = animal.id,
            nameAnimal = animal.nameAnimal,
            ageAnimal = animal.ageAnimal,
            sexAnimal = animal.sexAnimal,
            stateAnimal = animal.stateAnimal,
            shortInfoAnimal = animal.shortInfoAnimal,
            longInfoAnimal = animal.longInfoAnimal,
            breedAnimal = animal.breedAnimal,
            typeAnimal = animal.typeAnimal,
            timeShelter = animal.timeShelter
        )
    }
}

//@Composable
//fun MyComposable(viewModel: AnimalViewModel) {
//    val ages by viewModel.getAge().collectAsState(emptyList())
//    val breeds by viewModel.getBreed().collectAsState(emptyList())
//    val typeAnimals by viewModel.getTypeAnimal().collectAsState(emptyList())
//
//    val animalsByAge = viewModel.getAnimalsByAgeASC(2).collectAsState(emptyList()).value
//    val animalsByBreed = viewModel.getAnimalsByBreed("Siamese").collectAsState(emptyList()).value
//    val animalsByType = viewModel.getAnimalsByTypeAnimal("DOG").collectAsState(emptyList()).value
//
//    LazyColumn {
//
//        items(animalsByAge) { animal ->
//            AnimalItem(animal)
//        }
//    }
//}
//
//fun AnimalItem(animal: com.example.sirius.model.Animal) {
//    Card(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(16.dp),
//    ) {
//        Column(
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(16.dp)
//        ) {
//            Text(text = "Edad: ${animal.typeAnimal}")
//            Text(text = "Edad: ${animal.ageAnimal}")
//            Text(text = "Edad: ${animal.breedAnimal}")
//        }
//    }
//}
