package com.example.sirius.view.components

import AboutUsScreen
import DonationsScreen
import android.content.res.Configuration
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.example.sirius.ui.theme.Black
import com.example.sirius.ui.theme.Green3
import com.example.sirius.view.screens.HomeScreen
import com.example.sirius.viewmodel.NewsViewModel
import com.example.sirius.viewmodel.AnimalViewModel
import com.example.sirius.R
import com.example.sirius.ui.theme.White
import com.example.sirius.view.screens.AnimalInfo
import com.example.sirius.view.screens.LandingPage
import com.example.sirius.view.screens.LoadingPage
import com.example.sirius.view.screens.LoginScreen
import com.example.sirius.view.screens.ProfileScreen
import com.example.sirius.view.screens.SignupScreen
import com.example.sirius.viewmodel.UserViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NavigationContent(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    userViewModel: UserViewModel,
    selectedDestination: String,
    navigateDestination: (Destinations) -> Unit
) {
    Box(modifier = modifier.fillMaxSize()) {
        val currentRoute = navController.currentBackStackEntry?.destination?.route
        if (currentRoute !in listOf(Routes.LOGIN, Routes.SIGNUP, Routes.LANDINGPAGE, Routes.LOADING)) {
            ProfileButton(
                onClick = {
                    if (userViewModel.getAuthenticatedUser() != null)
                        navController.navigate(Routes.PROFILE)
                    else {

                        navController.navigate(Routes.LOGIN)
                    }
                },
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(16.dp)
                    .zIndex(99f)
            )
        }
        Column(
            modifier = modifier.fillMaxSize()
        ) {
            NavHost(
                modifier = Modifier.weight(1f),
                navController = navController,
                startDestination = if (userViewModel.getAuthenticatedUser() != null) Routes.HOME
                                   else Routes.LOADING
            ) {
                composable(route = Routes.HOME) {
                    //HomeScreenPreview()
                    val animalVm: AnimalViewModel = viewModel(factory = AnimalViewModel.factory)
                    val animalList by animalVm.getAllAnimalsOrderedByDaysEntryDate().collectAsState(initial = emptyList())
                    val newsVm: NewsViewModel = viewModel(factory = NewsViewModel.factory)
                    val newsList by newsVm.getNews().collectAsState(initial = emptyList())

                    HomeScreen(navController = navController, animalList = animalList, newsList = newsList)
                }
                composable(route = Routes.ANIMALS) {
                    val viewModel: AnimalViewModel = viewModel(factory = AnimalViewModel.factory)
                    val ageList by viewModel.getBirthYears().collectAsState(emptyList())
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
                    DonationsScreen(navController = navController)
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
                composable(route = Routes.LOGIN) {
                    LoginScreen(navController = navController, userViewModel = userViewModel)
                }
                composable(route = Routes.SIGNUP) {
                    SignupScreen(navController = navController, userViewModel = userViewModel)
                }
                composable(route = Routes.LANDINGPAGE) {
                    LandingPage(navController = navController)
                }
                composable(route = Routes.LOADING){
                    LoadingPage(navController = navController, 0)
                }
                composable(route = Routes.LOADING + "/{id}",
                    arguments = listOf(navArgument(name = "id") {
                        type = NavType.IntType
                        defaultValue = -1
                    })
                ) { navBackStackEntry ->
                    val id = navBackStackEntry.arguments?.getInt("id") ?: -1
                    LoadingPage(navController, id)
                }
                composable(route = Routes.PROFILE) {
                    ProfileScreen(navController = navController, userViewModel = userViewModel)
                }
            }
            if (currentRoute !in listOf(
                    Routes.LANDINGPAGE, Routes.SIGNUP, Routes.LOGIN,
                    Routes.LOADING, Routes.LOADING + "/{id}"
                )
            ) {
                Navbar(
                    selectedDestination = selectedDestination,
                    navigateDestination = navigateDestination,
                )
            }
        }
    }
}

@Composable
fun Navbar(
    selectedDestination: String,
    navigateDestination: (Destinations) -> Unit,
) {
    val isSystemInDarkTheme =
        (LocalContext.current.resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) == Configuration.UI_MODE_NIGHT_YES
    val destinations = createDestinations()

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        destinations.forEach { destination ->
            val selected = selectedDestination == destination.route
            val textColor = if (selected) Green3 else if (!isSystemInDarkTheme) Color.Black else Color.White

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .clickable {
                        navigateDestination(destination)
                    }
                    .weight(1f)
                    .background(if (selected) Green3.copy(alpha = 0.2f) else Color.Transparent)
                    .fillMaxWidth()
                    .fillMaxHeight()
            ) {
                Spacer(modifier = Modifier.height(8.dp))
                Icon(
                    painter = painterResource(
                        id = if (selected) {
                            destination.selectedIcon
                        } else {
                            destination.unselectedIcon
                        }
                    ),
                    contentDescription = stringResource(id = destination.iconTextId),
                    tint = if (selected) Green3 else if (!isSystemInDarkTheme) Color.Black else Color.White,
                    modifier = Modifier.size(24.dp),
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = stringResource(id = destination.iconTextId),
                    color = textColor,
                )
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }


//    NavigationBar(
//        modifier = Modifier.fillMaxWidth(),
//    ) {
//        destinations.forEach { destination ->
//            val selected = selectedDestination == destination.route

//            NavigationBarItem(
//                selected = selected,
//                onClick = { navigateDestination(destination) },
//                icon = {
//                    Column(
//                        horizontalAlignment = Alignment.CenterHorizontally,
//                        verticalArrangement = Arrangement.Center,
//                        modifier = Modifier
//                            .background(if (selected) Green3.copy(alpha = 0.2f) else Color.Transparent)
//                            .fillMaxSize()
//                    ) {
//                        Icon(
//                            painter = painterResource(id = if (selected) {
//                                destination.selectedIcon
//                            } else {
//                                destination.unselectedIcon
//                            }),
//                            contentDescription = stringResource(id = destination.iconTextId),
//                            tint = if (selected) Green3
//                                   else if (!isSystemInDarkTheme) Color.Black else Color.White,
//                            modifier = Modifier.size(24.dp),
//                        )
//                        Text(
//                            text = stringResource(id = destination.iconTextId),
//                            color = if (selected) Green3
//                                    else if (!isSystemInDarkTheme) Color.Black else Color.White
//                        )
//                    }
//                },
//            )

//        }
//    }
//    }
}

@Composable
fun ProfileButton(onClick: () -> Unit, modifier: Modifier) {
    Icon(
        imageVector = Icons.Default.Person,
        contentDescription = "Profile",
        modifier = modifier.clickable { onClick() }
    )
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
