package com.example.sirius.view.components

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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.sirius.view.screens.AnimalsGallery
import com.example.sirius.view.screens.generateSampleAnimalList
import androidx.navigation.compose.composable
import com.example.sirius.viewmodel.AnimalViewModel
import com.example.sirius.navigation.Destinations
import com.example.sirius.navigation.Routes
import com.example.sirius.navigation.createDestinations
import com.example.sirius.view.screens.HomeScreenPreview

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
                    val viewModel: AnimalViewModel = viewModel(factory = AnimalViewModel.factory)
                    val animalList = generateSampleAnimalList(viewModel)
                    AnimalsGallery(animalList = animalList)
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