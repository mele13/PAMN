package com.example.sirius.view.screens

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.example.sirius.R
import com.example.sirius.navigation.Routes
import com.example.sirius.ui.theme.Green1
import com.example.sirius.view.components.CustomSnackbar
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun LoadingPage (navController: NavHostController, id: Int = -1){
    val isSystemInDarkTheme = (LocalContext.current.resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) == Configuration.UI_MODE_NIGHT_YES
    Surface(
        color = MaterialTheme.colorScheme.background,
        modifier = Modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 161.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                Image(
                    painter = painterResource(id = if(!isSystemInDarkTheme) R.drawable.aboutus_icon
                                                   else R.drawable.aboutus_icon_wht),
                    contentDescription = "Logo",
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier
                        .width(98.dp)
                        .height(99.dp)
                )
            }
            // Overlayed images
            Image(
                painter = painterResource(id = R.drawable.loading_page_image1),
                contentDescription = "Logo",
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .width(50.dp)
                    .height(50.dp)
                    .align(Alignment.BottomEnd)
                    .offset(x = (-52).dp, y = (-58).dp)
            )
            Image(
                painter = painterResource(id = R.drawable.loading_page_image2),
                contentDescription = "Descripción de la imagen",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .width(50.dp)
                    .height(50.dp)
                    .align(Alignment.BottomStart)
                    .offset(x = 24.dp, y = (-68).dp),

                )
            Image(
                painter = painterResource(id = R.drawable.loading_page_image3),
                contentDescription = "Logo",
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .width(50.dp)
                    .height(50.dp)
                    .align(Alignment.TopEnd)
                    .offset(x = -(53).dp, y = 293.dp),
                )
            Image(
                painter = painterResource(id = R.drawable.loading_page_image4),
                contentDescription = "Logo",
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .width(50.dp)
                    .height(50.dp)
                    .align(Alignment.TopStart)
                    .offset(x = 112.dp, y = 309.dp),
            )
            Image(
                painter = painterResource(id = R.drawable.loading_page_image5),
                contentDescription = "Logo",
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .width(50.dp)
                    .height(50.dp)
                    .align(Alignment.TopStart)
                    .offset(x = 24.dp, y = 210.dp),
            )
            Image(
                painter = painterResource(id = R.drawable.loading_page_image6),
                contentDescription = "Logo",
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .width(50.dp)
                    .height(50.dp)
                    .align(Alignment.TopEnd)
                    .offset(y = 85.dp, x = (-85).dp),
            )
            /*Image(
                painter = painterResource(id = R.drawable.loading_page_image7),
                contentDescription = "Logo",
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .width(50.dp)
                    .height(50.dp)
                    .align(Alignment.TopEnd)
            )*/
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .width(47.dp)
                    .height(23.dp)
                    .padding(bottom = 118.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Bottom
            ) {
                Text(
                    text = stringResource(id = R.string.app_name),
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight(700),
                    )
                )
                Text(
                    text = stringResource(id = R.string.slogan),
                    style = TextStyle(
                        fontSize = 12.sp,
                        fontWeight = FontWeight(400),
                    )
                )
            }
        }
    }

    LaunchedEffect(true) {
        // Esperar durante 2 segundos
        delay(2000)
        if (id == 1){
            navController.navigate(route = Routes.HOME)
        } else {
            navController.navigate(route = Routes.LANDINGPAGE)
        }
    }
}
