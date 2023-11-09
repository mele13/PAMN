package com.example.sirius.view.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview
@Composable
fun AnimalInfoScreen(animal: Animal) {
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