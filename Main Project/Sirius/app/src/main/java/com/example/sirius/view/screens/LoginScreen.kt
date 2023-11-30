package com.example.sirius.view.screens

import android.content.res.Configuration
import android.util.Log
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
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.sirius.R
import com.example.sirius.navigation.Routes
import com.example.sirius.ui.theme.Green1
import com.example.sirius.view.components.CustomSnackbar
import com.example.sirius.viewmodel.UserViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(navController: NavController, userViewModel: UserViewModel) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var logInButtonClicked by remember { mutableStateOf(false) }
    var errorMessage by rememberSaveable { mutableStateOf<String?>(null) }
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
                .padding(16.dp)
                .offset(y = 80.dp),
//            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(id = if (isSystemInDarkTheme) R.drawable.sirius_name
                                                   else R.drawable.sirius_name_wht),
                    contentDescription = null,
                )
                Text(
                    text = stringResource(id = R.string.login),
                    style = MaterialTheme.typography.headlineMedium,
                    modifier = Modifier.align(Alignment.CenterVertically)
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
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
                    .padding(8.dp)
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
                    focusedBorderColor = if (logInButtonClicked && username.isBlank()) Color.Red else Green1,
                    unfocusedBorderColor = if (logInButtonClicked && username.isBlank()) Color.Red else Green1,
                )
            )
            Spacer(modifier = Modifier.height(8.dp))
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
                    .padding(8.dp)
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
                    focusedBorderColor = if (logInButtonClicked && username.isBlank()) Color.Red else Green1,
                    unfocusedBorderColor = if (logInButtonClicked && username.isBlank()) Color.Red else Green1,
                ),
            )
            Spacer(modifier = Modifier.height(8.dp))
            // Sign Up
            TextButton(
                onClick = { navController.navigate(Routes.SIGNUP)  },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .offset(y = (-8).dp)
            ) {
                Text(
                    stringResource(id = R.string.account_signup),
                    style = TextStyle(color = if (isSystemInDarkTheme) Color.White else Color.Black),
                    textAlign = TextAlign.Center
                )
            }
            Spacer(modifier = Modifier.height(20.dp))
            // Log In button
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .offset(y = (-80).dp)
                    .zIndex(-1f),
                contentAlignment = Alignment.Center
            ) {
                TextButton(
                    onClick = {
                        userViewModel.viewModelScope.launch {
                            logInButtonClicked = true
                            val success = userViewModel.login(username, password)
                            if (success) {
                                navController.navigate(Routes.HOME)
                            } else {
                                errorMessage = "Invalid username or password"
                            }
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .offset(y = 23.dp),
                ) {
                    Text(
                        stringResource(id = R.string.login),
                        color = Color.White,
                        fontSize = 25.sp
                    )
                }
                // Center - Log In button
                Image(
                    painter = painterResource(id = R.drawable.paw2),
                    contentDescription = null,
                    modifier = Modifier
                        .size(230.dp)
                        .zIndex(-10f)
                        .offset(x = 16.dp)
                )
            }
//            TextButton(
//                onClick = {
//                    userViewModel.viewModelScope.launch {
//                        val success = userViewModel.login(username, password)
//                        if (success) {
//                            navController.navigate(Routes.HOME)
//                        } else {
//                            errorMessage = "Invalid username or password"
//                        }
//                    }
//                },
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(8.dp)
//                    .offset(y = 35.dp),
//                ) {
//                Text(
//                    stringResource(id = R.string.login),
//                    color = Color.White,
//                    fontSize = 25.sp
//                )
//            }
            // Error Snackbar
            errorMessage?.let { message ->
                CustomSnackbar(
                    message = message,
                    onDismiss = { errorMessage = null },
                    isSystemInDarkTheme = isSystemInDarkTheme
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
//        // Center - Log In button
//        Image(
//            painter = painterResource(id = R.drawable.paw2),
//            contentDescription = null,
//            modifier = Modifier
//                .align(Alignment.Center)
//                .size(230.dp)
//                .offset(x = 16.dp, y = 130.dp)
//                .zIndex(-1f)
//        )
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
                .align(Alignment.TopEnd)
                .size(120.dp)
                .offset(x = 20.dp, y = 152.dp)
                .zIndex(-2f)
        )
    }
}
