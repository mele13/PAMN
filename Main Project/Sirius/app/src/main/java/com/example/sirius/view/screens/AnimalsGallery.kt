package com.example.sirius.view.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.sirius.R
import com.example.sirius.viewmodel.AnimalViewModel
import kotlinx.coroutines.flow.map

@Composable
fun AnimalsGallery(animalList: List<Animal>) {
    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier.fillMaxSize()
    ) {
        items(animalList.chunked(2)) { chunk ->
            LazyRow(
                contentPadding = PaddingValues(20.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                items(chunk) { animal ->
                    AnimalCard(animal = animal)
                }
            }
        }
    }
}

@Composable
fun AnimalCard(animal: Animal) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f),
        shape = MaterialTheme.shapes.medium,
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .width(152.dp)
                .clip(MaterialTheme.shapes.medium), // Edge rounding
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = animal.imageRes),
                contentDescription = animal.description,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
                    .aspectRatio(1f)
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = animal.description,
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Center
        )
    }
}

data class Animal(val imageRes: Int, val description: String)

@SuppressLint("FlowOperatorInvokedInComposition")
@Preview
@Composable
fun AnimalGalleryPreview() {
    val viewModel: AnimalViewModel = viewModel(factory = AnimalViewModel.factory)
    val animalList by viewModel.getAllAnimals()
        .map { animalModelList ->
            animalModelList.map { convertAnimalModelToView(it) }
        }
        .collectAsState(emptyList())
}

fun convertAnimalModelToView(animal: com.example.sirius.model.Animal): Animal {
    return Animal(
        imageRes = R.drawable.dog1,
        description = animal.shortInfoAnimal
    )
}


@Composable
fun generateSampleAnimalList(viewModel: AnimalViewModel): List<Animal> {
    val animalList by viewModel.getAllAnimals().collectAsState(emptyList())
    //println("holaaaaa $animalList")
    return animalList.map { animal ->
        Animal(
            imageRes = R.drawable.dog1,
//            description = "Name: ${animal.nameAnimal} \nAge: ${animal.ageAnimal} \nDescription: ${animal.shortInfoAnimal} "
            description = "${animal.nameAnimal}, ${animal.ageAnimal}\n${animal.shortInfoAnimal}"
        )
    }
}