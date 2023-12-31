package com.example.projetsessionandroid.routes

//la classe des routes
sealed class ScreensRoutes(val route: String) {
    //Route de la page d'accueil
    object HomeScreen: ScreensRoutes("home_screen")
    //Route de la page Post
    object PostScreen: ScreensRoutes("post_screen")
    //Route de la page du Profil
    object ProfileScreen: ScreensRoutes("profile_screen")
    //Route de la page historique des commandes
    object HistoricCommandScreen: ScreensRoutes("historic_command_screen")
    //Route de la page historique des dons
    object HistoricDonScreen: ScreensRoutes("historic_don_screen")

}