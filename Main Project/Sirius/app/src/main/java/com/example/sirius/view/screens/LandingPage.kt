package com.example.sirius.view.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sirius.R
import com.example.sirius.ui.theme.Gold

@Composable
fun LandingPage() {
    Surface(
        color = MaterialTheme.colorScheme.background,
        modifier = Modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Image(
                painter = painterResource(id = R.drawable.image_landing_page),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.TopCenter)
                    .padding(top = 32.dp),
                contentAlignment = Alignment.Center
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.aboutus_icon),
                        contentDescription = "Logo",
                        contentScale = ContentScale.FillBounds,
                        modifier = Modifier.size(39.dp) // Ajusta el tamaño de la imagen según tus preferencias
                    )
                    Text(
                        text = "Sirius",
                        fontSize = 40.sp,
                        fontWeight = FontWeight(400),
                        color = Color(0xFF000000),
                    )
                }
            }
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                RoundButton("Login / Sign Up")
                Spacer(modifier = Modifier.height(15.dp))
                RoundButton("Join as a guest")
                Spacer(modifier = Modifier.height(32.dp))
            }
        }
    }
}

@Composable
fun RoundButton(text: String) {
    Button(
        onClick = { /* Acción cuando se hace clic en el botón */ },
        modifier = Modifier.width(219.dp)
            .size(30.dp),
        shape = MaterialTheme.shapes.large,
        contentPadding = PaddingValues(0.dp),
        colors = ButtonDefaults.buttonColors(Gold.copy(alpha = 0.61f))
    ) {
        Text(
            text = text,
            fontSize = 14.sp,
            fontWeight = FontWeight(400),
            color = Color(0xFF000000),


            )
    }
}

@Composable
@Preview
fun LandingPagePreview() {
    LandingPage()
}