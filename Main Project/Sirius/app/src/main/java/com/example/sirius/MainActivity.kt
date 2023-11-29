package com.example.sirius

import android.content.Context
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.sirius.data.dao.UserDao
import com.example.sirius.ui.theme.SiriusTheme
import com.example.sirius.navigation.NavigationController
import com.example.sirius.view.screens.LoginScreen
import com.example.sirius.viewmodel.UserViewModel
import com.example.sirius.viewmodel.navigation.AnimalViewModel



class MainActivity : ComponentActivity() {
    private val userViewModel: UserViewModel by lazy {
//        val animalApplication = application as AnimalApplication
//        animalApplication.initContext(applicationContext)
        UserViewModel((application as AnimalApplication).userDao)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        val sharedPreferences = applicationContext.getSharedPreferences("auth_prefs", Context.MODE_PRIVATE)
//        val isAuthenticated = sharedPreferences.getBoolean("is_authenticated", false)
//        userViewModel.isAuthenticated = isAuthenticated

        setContent {
            SiriusTheme {
                NavigationController(userViewModel)
                //LandingPage()
                //MyComposable(viewModel = viewModel)
            }
        }
    }
}
