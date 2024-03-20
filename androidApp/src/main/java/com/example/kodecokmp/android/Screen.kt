package com.example.kodecokmp.android

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.Place
import androidx.compose.ui.graphics.vector.ImageVector

//To keep track of your two screens, create a new sealed class named Screen:
//Here, this sealed class is similar to an enum class
    sealed class Screen(val title: String) {
        data object TimeZonesScreen : Screen("Timezones")
        data object FindTimeScreen : Screen("Find Time")
    }


//define a class to handle the bottom navigation item
//This defines a route, an icon for that route and a content description
data class BottomItem( val route: String, val icon: ImageVector, val iconContentDescription: String)

//This list will help you create the bottom navigation UI that you will be using to switch the screens.
// This uses the material icons and the titles from the screen class.
val bottomNavigationItems = listOf(
    BottomItem(
        Screen.TimeZonesScreen.title,
        Icons.Filled.Language,
        "Timezones"
    ),
    BottomItem(
        Screen.FindTimeScreen.title,
        Icons.Filled.Place,
        "Find Time"
    )
)
