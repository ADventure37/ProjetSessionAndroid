package com.example.projetsessionandroid.routes

sealed class ScreensRoutes(val route: String) {
    //l'interface des routes
    object HomeScreen: ScreensRoutes("home_screen")
    object PostScreen: ScreensRoutes("post_screen")
    object DetailScreen: ScreensRoutes("detail_screen/{foodId}")
    object ProfileScreen: ScreensRoutes("profile_screen")
}