package com.example.projetsessionandroid.routes

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.projetsessionandroid.data.model.User
import com.example.projetsessionandroid.ui.screen.accueilScreen.AccueilScreenView
import com.example.projetsessionandroid.ui.screen.detailScreen.DetailsScreenView
import com.example.projetsessionandroid.ui.screen.historicScreen.HistoricScreenView
import com.example.projetsessionandroid.ui.screen.postScreen.PostScreenView
import com.example.projetsessionandroid.ui.screen.profileScreen.ProfileScreenView

//Composant permettant la navigation entre les pages
@Composable
fun NavGraph (navController: NavHostController, user: User){
    NavHost(
        navController = navController,
        startDestination = ScreensRoutes.HomeScreen.route
    )
    {
        //Pour la page d'accueil
        composable(route = ScreensRoutes.HomeScreen.route){
            AccueilScreenView(navController = navController, user)
        }
        //Pour la page de detail des annonces
        composable(route = "detail_screen/{foodId}") { backStackEntry ->
            // Récupérer le paramètre matchId
            val foodId = backStackEntry.arguments?.getString("foodId")
            DetailsScreenView(navController, user, foodId)
        }
        //Page pour ajouter une annonce
        composable(route = ScreensRoutes.PostScreen.route){
            PostScreenView(navController = navController, user)
        }
        //Page pour consulter son profil
        composable(route = ScreensRoutes.ProfileScreen.route){
            ProfileScreenView(navController = navController, user)
        }
        //Page pour consulter son historique de commandes
        composable(route = ScreensRoutes.HistoricCommandScreen.route){
            HistoricScreenView(navController = navController, user, historic="command")
        }
        //Page pour consulter son historique de dons
        composable(route = ScreensRoutes.HistoricDonScreen.route){
            HistoricScreenView(navController = navController, user, historic="don")
        }
    }
}