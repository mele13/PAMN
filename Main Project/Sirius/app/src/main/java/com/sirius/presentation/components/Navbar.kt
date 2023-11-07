package com.sirius.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.sirius.navigation.Destinations
import com.sirius.navigation.Routes
import com.sirius.presentation.screens.AnimalsGallery
import com.sirius.presentation.screens.generateSampleAnimalList
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.composable
import com.sirius.navigation.createDestinations
import com.sirius.presentation.screens.HomeScreenPreview

@Composable
fun NavigationContent(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    selectedDestination: String,
    navigateDestination: (Destinations) -> Unit
) {
    Row(
        modifier = modifier.fillMaxSize()
    ) {
        Column(
            modifier = modifier.fillMaxSize()
        ) {
            NavHost(
                modifier = Modifier.weight(1f),
                navController = navController,
                startDestination = Routes.HOME
            ) {
                composable(Routes.HOME) {
                    HomeScreenPreview()
                }
                composable(Routes.ANIMALS) {
                    AnimalsGallery(animalList = generateSampleAnimalList())
                }
//                composable(Routes.DONATIONS) {}
//                composable(Routes.ABOUTUS) {}
            }
            Navbar(
                selectedDestination = selectedDestination,
                navigateDestination = navigateDestination,
            )
        }
    }
}

@Composable
fun Navbar(
    selectedDestination: String,
    navigateDestination: (Destinations) -> Unit,
) {
    val destinations = createDestinations()

    NavigationBar(
        modifier = Modifier.fillMaxWidth()
    ) {
        destinations.forEach {
                destinations -> NavigationBarItem(
            selected = selectedDestination == destinations.route,
            onClick = { navigateDestination(destinations) },
            icon = {
                Icon(
                    imageVector = destinations.selectedIcon,
                    contentDescription = stringResource(id = destinations.iconTextId)
                )
            }
        )
        }
    }
}

class NavigationActions(private val navController: NavHostController) {
    fun navigateTo(destination: Destinations) {
        navController.navigate(destination.route) {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
        }
    }
}
