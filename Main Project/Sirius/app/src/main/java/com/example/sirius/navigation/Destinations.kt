package com.example.sirius.navigation

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.example.sirius.R

@Composable
fun createDestination(route: String, selectedIcon: Int, iconTextId: Int): Destinations {
    return Destinations(
        route = route,
        selectedIcon = selectedIcon,
        unselectedIcon = selectedIcon,
        iconTextId = iconTextId,
    )
}

@Composable
fun createDestinations(): List<Destinations> {
    return listOf(
        createDestination(Routes.HOME, getIcon(R.drawable.home_icon, R.drawable.home_icon_wht), R.string.home),
        createDestination(Routes.ANIMALS, getIcon(R.drawable.animals_icon, R.drawable.animals_icon_wht), R.string.animals),
        createDestination(Routes.DONATIONS, getIcon(R.drawable.donations_icon, R.drawable.donations_icon_wht), R.string.donations),
        createDestination(Routes.ABOUTUS, getIcon(R.drawable.aboutus_icon, R.drawable.aboutus_icon_wht), R.string.aboutUs),
    )
}

@Composable
private fun getIcon(lightIcon: Int, darkIcon: Int): Int {
    val isSystemInDarkTheme =
        (LocalContext.current.resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) == Configuration.UI_MODE_NIGHT_YES
    return if (!isSystemInDarkTheme) darkIcon else lightIcon
}

data class Destinations(
    val route: String,
    val selectedIcon: Int,
    val unselectedIcon: Int,
    val iconTextId: Int
)

object Routes {
    const val HOME = "home"
    const val ANIMALS = "animals"
    const val DONATIONS = "donations"
    const val ABOUTUS = "about us"
    const val ANIMALINFO = "animal info"
    const val LOGIN = "login"
    const val SIGNUP = "signup"
    const val LANDINGPAGE = "landing page"
    const val PROFILE = "profile"
    const val LOADING = "loading"
}
