package com.example.projetsessionandroid.routes

sealed class ScreensRoutes(val route: String) {
    //Screen de l'interface d'accueil
    object HomeScreen: ScreensRoutes("home_screen")
    object PostScreen: ScreensRoutes("post_screen")
    object ProfileScreen: ScreensRoutes("profile_screen")
}