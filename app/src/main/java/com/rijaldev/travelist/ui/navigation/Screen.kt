package com.rijaldev.travelist.ui.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Favorite : Screen("favorite")
    object About : Screen("about")
    object Detail : Screen("home/{tourismId}") {
        fun createRoute(tourismId: Int) = "home/$tourismId"
    }
}
