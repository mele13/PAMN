package com.example.sirius.view.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
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
import androidx.navigation.NavController
import com.example.sirius.R
import com.example.sirius.model.Animal
import com.example.sirius.model.TypeAnimal
import com.example.sirius.viewmodel.navigation.AnimalViewModel

@Composable
fun AnimalInfo(navController: NavController, id: Int?, viewModel: AnimalViewModel) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {

        val animal by viewModel.getAnimalById(id ?: 0).collectAsState(initial = null)

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .width(152.dp)
                .clip(MaterialTheme.shapes.medium), // Edge rounding
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            item {
                if (animal != null) {
                    val imageResource = when (animal!!.typeAnimal) {
                        TypeAnimal.DOG -> R.drawable.dog1
                        TypeAnimal.CAT -> R.drawable.cat1
                        TypeAnimal.BIRD -> R.drawable.bird1
                        else -> {
                            R.drawable.dog1
                        }
                    }

                    Image(
                        painter = painterResource(id = imageResource),
                        contentDescription = animal!!.longInfoAnimal,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxSize()
                            .aspectRatio(1f)
                    )
                }
            }
            item {
                Spacer(modifier = Modifier.height(8.dp))
                if (animal != null) {
                    Text(
                        text = animal!!.longInfoAnimal,
                        style = MaterialTheme.typography.bodyMedium,
                        textAlign = TextAlign.Center
                    )
                }
            }
            item {
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
