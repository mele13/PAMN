package com.example.businesscard_as

import android.graphics.drawable.Icon
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.businesscard_as.ui.theme.BusinessCard_ASTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BusinessCard_ASTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    BusinessCard()
                }
            }
        }
    }
}

@Composable
fun ContactInfoRow(image: Painter, contentDescription: String, text: String) {
    Row {
        Icon(
            painter = image,
            contentDescription = contentDescription,
            tint = Color.Black,
            modifier = Modifier.size(23.dp)
        )
        Text(
            text = text,
            color = Color.Black,
            modifier = Modifier.padding(start = 8.dp)
        )
    }
    Spacer(modifier = Modifier.height(2.dp))
}

@Composable
fun BasicInformation(name: String, title: String) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
//        modifier = Modifier.background(Color.Blue)
    ) {
        Image(
            painter = painterResource(id = R.drawable.programmer),
            contentDescription = "",
//            alpha = 0.5F,
            modifier = Modifier.size(200.dp)
        )
        Text(
            text = name,
            fontSize = 30.sp,
            lineHeight = 30.sp,
            textAlign = TextAlign.Center,
            color = Color.Black
        )
        Spacer(modifier = Modifier.height(2.dp))
        Text(
            text = title,
            fontSize = 20.sp,
            lineHeight = 40.sp,
            textAlign = TextAlign.Center,
            color = Color.Black
        )
    }
}

@Composable
fun ContactInformation(phone: String, github: String, email: String) {
    Column(
        verticalArrangement = Arrangement.Center,
//        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(15.dp)
//            .background(Color.Yellow)
    ) {
        ContactInfoRow(
            image = painterResource(id = R.drawable.email),
            contentDescription = "Email icon", text = email
        )
        ContactInfoRow(
            image = painterResource(id = R.drawable.github),
            contentDescription = "Github icon", text = github
        )
        ContactInfoRow(
            image = painterResource(id = R.drawable.phone),
            contentDescription = "Phone icon", text = phone
        )
    }
}

@Composable
fun BusinessCard() {
    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize().background(Color.White)
        ) {
            BasicInformation(name = "ProgrammerX", title = "A skilled programmer")
            Spacer(modifier = Modifier.height(100.dp))
            ContactInformation(phone = "+00 (00) 000 000", github = "@programmerx",
                email = "programmerx@domail.com")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BusinessCardPreview() {
    BusinessCard_ASTheme {
        BusinessCard()
    }
}