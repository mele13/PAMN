package com.example.sirius.view.screens

import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.SemanticsProperties.ImeAction
import androidx.compose.ui.semantics.SemanticsProperties.Text
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import com.example.sirius.R
import com.example.sirius.navigation.Routes
import com.example.sirius.ui.theme.Green1


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignupScreen(navController: NavController) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    val isSystemInDarkTheme = (LocalContext.current.resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) == Configuration.UI_MODE_NIGHT_YES

    Box(
        modifier = Modifier
            .fillMaxSize()
//            .background(color = Color.Yellow)
//            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(14.dp)
                .offset(y = 80.dp),
//            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.sirius_name),
                    contentDescription = null,
                )
                Text(
                    text = stringResource(id = R.string.signup),
                    style = MaterialTheme.typography.headlineMedium,
                    modifier = Modifier.align(Alignment.CenterVertically)
                )
            }
            Spacer(modifier = Modifier.height(4.dp))
            // Username
            OutlinedTextField(
                value = username,
                onValueChange = { username = it },
                label = {
                    Text(
                        stringResource(id = R.string.username),
                        style = TextStyle(color = if (isSystemInDarkTheme) Color.White else Color.Black)
                    )
                },
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(3.dp)
                    .background(
                        MaterialTheme.colorScheme.background.copy(alpha = 0.3f),
                        MaterialTheme.shapes.medium
                    ),
                textStyle = LocalTextStyle.current.copy(color = LocalContentColor.current),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = null
                    )
                },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Green1,
                    unfocusedBorderColor = Green1,
                )
            )
            Spacer(modifier = Modifier.height(3.dp))
            // Email
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = {
                    Text(
                        stringResource(id = R.string.email),
                        style = TextStyle(color = if (isSystemInDarkTheme) Color.White else Color.Black)
                    )
                },
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(3.dp)
                    .background(
                        MaterialTheme.colorScheme.background.copy(alpha = 0.3f),
                        MaterialTheme.shapes.medium
                    ),
                textStyle = LocalTextStyle.current.copy(color = LocalContentColor.current),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Email,
                        contentDescription = null
                    )
                },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Green1,
                    unfocusedBorderColor = Green1,
                )
            )
            Spacer(modifier = Modifier.height(3.dp))
            // Password
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = {
                    Text(
                        stringResource(id = R.string.password),
                        style = TextStyle(color = if (isSystemInDarkTheme) Color.White else Color.Black)
                    )
                },
                singleLine = true,
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(3.dp)
                    .background(
                        MaterialTheme.colorScheme.background.copy(alpha = 0.3f),
                        MaterialTheme.shapes.medium
                    ),
                textStyle = LocalTextStyle.current.copy(color = LocalContentColor.current),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Edit,
                        contentDescription = null
                    )
                },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Green1,
                    unfocusedBorderColor = Green1,
//                    textColor = LocalContentColor.current,
                )
            )
            Spacer(modifier = Modifier.height(3.dp))
            // Log In
            TextButton(
                onClick = { navController.navigate(Routes.LOGIN) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(3.dp)
                    .offset(y = (-8).dp)
            ) {
                Text(
                    stringResource(id = R.string.account_login),
                    style = TextStyle(color = if (isSystemInDarkTheme) Color.White else Color.Black),
                    textAlign = TextAlign.Center
                )
            }
            Spacer(modifier = Modifier.height(20.dp))
            // Sign Up button
            TextButton(
                onClick = { },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .offset(y = 23.dp),
            ) {
                Text(
                    stringResource(id = R.string.signup),
                    color = Color.White,
                    fontSize = 25.sp
                )
            }
        }
        // Bottom left
        Image(
            painter = painterResource(id = R.drawable.paw1),
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.BottomStart)
                .size(230.dp)
                .absoluteOffset((-6).dp)
        )
        // Center - Sign Up button
        Image(
            painter = painterResource(id = R.drawable.paw2),
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.Center)
                .size(230.dp)
                .offset(x = 16.dp, y = 130.dp)
                .zIndex(-1f)
        )
        // Top right big
        Image(
            painter = painterResource(id = R.drawable.paw3),
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .size(150.dp)
                .offset(x = 10.dp, y = (-30).dp)
                .zIndex(-2f)
        )
        // Top right small
        Image(
            painter = painterResource(id = R.drawable.paw4),
            contentDescription = null,
            modifier = Modifier
//                .background(color = Color.Red)
                .align(Alignment.TopEnd)
                .size(120.dp)
                .offset(x = 20.dp, y = 152.dp)
                .zIndex(-2f)
        )
    }
}
