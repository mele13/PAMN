package com.example.sirius.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import com.example.sirius.R

@Composable
fun createDestination(route: String, selectedIcon: Int, iconTextId: Int): Destinations {
    return Destinations(
        route = route,
        selectedIcon = ImageVector.vectorResource(id = selectedIcon),
        iconTextId = iconTextId,
    )
}

@Composable
fun createDestinations(): List<Destinations> {
    return listOf(
//        createDestination(Routes.HOME, R.drawable.home_icon, R.string.home),
//        createDestination(Routes.ANIMALS, R.drawable.animals_icon, R.string.animals),
//        createDestination(Routes.DONATIONS, R.drawable.donations_icons, R.string.donations),
//        createDestination(Routes.ABOUTUS, R.drawable.sirius_no_name, R.string.aboutUs),
        Destinations(
            route = Routes.HOME,
            selectedIcon = Icons.Default.Home,
            iconTextId = R.string.home,
        ),
        Destinations(
            route = Routes.ANIMALS,
            selectedIcon = Icons.Default.Favorite,
            iconTextId = R.string.animals,
        )
    )
}

data class Destinations(
    val route: String,
    val selectedIcon: ImageVector,
    val iconTextId: Int
)

object Routes {
    const val HOME = "home"
    const val ANIMALS = "animals"
//    const val DONATIONS = "donations"
//    const val ABOUTUS = "about us"
}