package com.sirius

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.sirius.ui.theme.SiriusTheme
import androidx.navigation.compose.rememberNavController
import com.sirius.presentation.screens.HomeScreen
import androidx.compose.runtime.remember
import androidx.navigation.compose.currentBackStackEntryAsState
import com.sirius.navigation.Routes
import com.sirius.presentation.components.NavigationActions
import com.sirius.presentation.components.NavigationContent

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SiriusTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    val navigateAction = remember(navController) {
                        NavigationActions(navController)
                    }

                    val navBackStackEntry by navController.currentBackStackEntryAsState()
                    val selectedDestination = navBackStackEntry?.destination?.route ?: Routes.HOME

                    NavigationContent(
                        navController = navController,
                        selectedDestination = selectedDestination,
                        navigateDestination = navigateAction::navigateTo
                    )
                }
            }
        }
    }
}
