package com.example.projetsessionandroid.routes

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.projetsessionandroid.data.model.User
import com.example.projetsessionandroid.ui.screen.accueilScreen.AccueilScreenView
import com.example.projetsessionandroid.ui.screen.historicScreen.HistoricScreenView
import com.example.projetsessionandroid.ui.screen.postScreen.PostScreenView
import com.example.projetsessionandroid.ui.screen.profileScreen.ProfileScreenView

@Composable
fun NavGraph (navController: NavHostController, user: User){
    NavHost(
        navController = navController,
        startDestination = ScreensRoutes.HomeScreen.route
    )
    {

        composable(route = ScreensRoutes.HomeScreen.route){
            AccueilScreenView(navController = navController, user)
        }

//        composable(route = "detail_screen/{matchId}") { backStackEntry ->
//            // Récupérer le paramètre matchId
//            val matchId = backStackEntry.arguments?.getString("matchId")
//            MatchDetailScreen(matchId, navController)
//        }
//
        composable(route = ScreensRoutes.PostScreen.route){
            PostScreenView(navController = navController, user)
        }
        composable(route = ScreensRoutes.ProfileScreen.route){
            ProfileScreenView(navController = navController, user)
        }
        composable(route = ScreensRoutes.HistoricCommandScreen.route){
            HistoricScreenView(navController = navController, user, historic="command")
        }
        composable(route = ScreensRoutes.HistoricDonScreen.route){
            HistoricScreenView(navController = navController, user, historic="don")
        }
    }
}