package com.example.sirius

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
import com.example.sirius.ui.theme.SiriusTheme
import com.example.sirius.navigation.NavigationController
import com.example.sirius.view.screens.LoginScreen
import com.example.sirius.viewmodel.navigation.AnimalViewModel

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SiriusTheme {
                NavigationController()
                //LandingPage()
                //MyComposable(viewModel = viewModel)
            }
        }
    }
}
