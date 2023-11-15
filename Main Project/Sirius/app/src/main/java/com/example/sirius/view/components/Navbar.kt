package com.example.sirius.view.components

import AboutUsScreen
import DonationsScreen
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.sirius.view.screens.AnimalsGallery
import androidx.navigation.compose.composable
import com.example.sirius.navigation.Destinations
import com.example.sirius.navigation.Routes
import com.example.sirius.navigation.createDestinations
import com.example.sirius.view.screens.HomeScreenPreview
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.example.sirius.ui.theme.Black
import com.example.sirius.ui.theme.Green3
import com.example.sirius.view.screens.HomeScreen
import com.example.sirius.viewmodel.NewsViewModel
import com.example.sirius.viewmodel.navigation.AnimalViewModel
import kotlinx.coroutines.flow.firstOrNull
import com.example.sirius.R
import com.example.sirius.view.screens.AnimalInfo

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
                composable(route = Routes.HOME) {
                    //HomeScreenPreview()
                    val animalVm: AnimalViewModel = viewModel(factory = AnimalViewModel.factory)
                    val animalList by animalVm.getAllAnimals().collectAsState(initial = emptyList())

                    val newsVm: NewsViewModel = viewModel(factory = NewsViewModel.factory)
                    val newsList by newsVm.getNews().collectAsState(initial = emptyList())

                    val imageList = listOf(
                        R.drawable.dog1,
                        R.drawable.dog1,
                        R.drawable.dog1,
                        R.drawable.dog1,
                    )
                    HomeScreen(animalList = animalList, newsList = newsList, imageList = imageList)
                }
                composable(route = Routes.ANIMALS) {
                    val viewModel: AnimalViewModel = viewModel(factory = AnimalViewModel.factory)

                    val ageList by viewModel.getAge().collectAsState(emptyList())
                    val breedList by viewModel.getBreed().collectAsState(emptyList())
                    val typeList by viewModel.getTypeAnimal().collectAsState(emptyList())

                    AnimalsGallery(
                        navController = navController,
                        ageList = ageList,
                        breedList = breedList,
                        typeList = typeList
                    )
                }

                composable(route = Routes.DONATIONS) {
                    DonationsScreen()
                }
                composable(route = Routes.ABOUTUS) {
                    AboutUsScreen()
                }
                composable(route = Routes.ANIMALINFO + "/{id}",
                    arguments = listOf(navArgument(name = "id") {
                        type = NavType.IntType
                    })) {
                    val viewModel: AnimalViewModel = viewModel(factory = AnimalViewModel.factory)

                    AnimalInfo(navController, it.arguments?.getInt("id"), viewModel)
                }
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
        destinations.forEach { destination ->
            val selected = selectedDestination == destination.route

            NavigationBarItem(
                selected = selected,
                onClick = { navigateDestination(destination) },
                icon = {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .background(if (selected) Green3.copy(alpha = 0.5f) else Color.Transparent)
                            .fillMaxSize()
                    ) {
                        Icon(
                            painter = painterResource(id = if (selected) {
                                destination.selectedIcon
                            } else {
                                destination.unselectedIcon
                            }),
                            contentDescription = stringResource(id = destination.iconTextId),
                            tint = if (selected) {
                                Green3
                            } else {
                                Black
                            }
                        )

                        Text(
                            text = stringResource(id = destination.iconTextId),
                            color = Color.Black
                        )
                    }
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