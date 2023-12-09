package com.example.sirius.view.screens

import android.annotation.SuppressLint
import android.content.res.Configuration
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.room.util.getColumnIndex
import com.example.sirius.R
import com.example.sirius.model.Animal
import com.example.sirius.model.LikedAnimal
import com.example.sirius.model.User
import com.example.sirius.navigation.Routes
import com.example.sirius.ui.theme.Green1
import com.example.sirius.ui.theme.Green2
import com.example.sirius.ui.theme.Green3
import com.example.sirius.viewmodel.AnimalViewModel
import com.example.sirius.viewmodel.UserViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    navController: NavController,
    userViewModel: UserViewModel,
    animalViewModel: AnimalViewModel
) {
    var user by remember { mutableStateOf(userViewModel.getAuthenticatedUser()) }
    var username by remember { mutableStateOf(user?.username ?: "") }
    var email by remember { mutableStateOf(user?.email ?: "") }
    var password by remember { mutableStateOf(user?.password ?: "") }
    var imageUrl by remember { mutableStateOf(user?.photoUser ?: "") }
    val likedAnimals by userViewModel.getLikedAnimals(user?.id ?: -1).collectAsState(emptyList())

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            UserImage(imageUrl = imageUrl, user)
            ProfileItem(label = stringResource(id = R.string.username), value = username)
            ProfileItem(label = stringResource(id = R.string.email), value = email)

            // Change Password Button
            Spacer(modifier = Modifier.height(16.dp))
            ChangePasswordButton(
                onClick = {
                    // Handle change password logic here
                }
            )

            // Log Out Button
            Spacer(modifier = Modifier.height(16.dp))
            LogoutButton(
                onLogoutClick = {
                    userViewModel.viewModelScope.launch {
                        userViewModel.logout()
                        navController.navigate(Routes.LOGIN)
                    }
                }
            )

            // Liked animals
            Spacer(modifier = Modifier.height(16.dp))
            LikedAnimalsSection(likedAnimals, animalViewModel)
        }
    }
}

@Composable
fun ProfileItem(label: String, value: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = label,
//            style = MaterialTheme.typography.labelLarge,
//            color = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.height(4.dp))
        OutlinedTextField(
            value = value,
            onValueChange = { /* Noop - Non-editable field || si es editable: userViewModel.updateUser */ },
            singleLine = true,
            readOnly = true,
            modifier = Modifier.fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Green1,
                unfocusedBorderColor = Green1,
            )
        )
    }
}

@Composable
fun LogoutButton(onLogoutClick: () -> Unit) {
    IconButton(
        onClick = { onLogoutClick() },
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(imageVector = Icons.Outlined.Lock, contentDescription = null)
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = "Logout")
        }
    }
}

@Composable
fun ChangePasswordButton(onClick: () -> Unit) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor  = Green3,
            contentColor = Color.White)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
//            Icon(imageVector = Icons.Outlined.Lock, contentDescription = null)
//            Spacer(modifier = Modifier.width(8.dp))
            Text(text = stringResource(id = R.string.change_password))
        }
    }
}

@Composable
fun UserImage(imageUrl: String, user: User?) {
    val context = LocalContext.current

    // Obtener el nombre del recurso sin la ruta
    val resourceName = user?.photoUser?.substringAfterLast("/")

    // Obtener el ID del recurso sin la ruta
    val resourceId = context.resources.getIdentifier(
        resourceName?.replace(".jpg", ""), "drawable", context.packageName
    )

    if (resourceId != 0) {
        // Si se encontró el recurso, cargar la imagen
        val painter = painterResource(id = resourceId)
        Image(
            painter = painter,
            contentDescription = null,
            modifier = Modifier.size(150.dp)
        )
    } else {
        Log.e("UserImage", "Recurso no encontrado para ${user?.photoUser}")
    }
}

@Composable
fun LikedAnimalsSection(likedAnimals: List<Animal>, animalViewModel: AnimalViewModel) {
    if (likedAnimals.isNotEmpty()) {
        likedAnimals.forEach { likedAnimal ->
            Text(text = likedAnimal.nameAnimal)
        }
    } else {
        Text(text = "No liked animals")
    }
}
