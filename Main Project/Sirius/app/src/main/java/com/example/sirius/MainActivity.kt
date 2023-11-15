package com.example.sirius

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.example.sirius.ui.theme.SiriusTheme
import com.example.sirius.navigation.NavigationController
import com.example.sirius.viewmodel.navigation.AnimalViewModel

class MainActivity : ComponentActivity() {
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