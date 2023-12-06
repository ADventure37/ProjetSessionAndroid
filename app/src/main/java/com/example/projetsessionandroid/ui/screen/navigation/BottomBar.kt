package com.example.projetsessionandroid.ui.screen.navigation

import android.R
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.example.projetsessionandroid.routes.ScreensRoutes

@Composable
fun BottomBar(navController: NavController) {
    NavigationBar (containerColor = Color.LightGray){
        // Les éléments de la barre de navigation
        NavigationBarItem(
            selected = false,
            onClick = { navController.navigate(ScreensRoutes.HomeScreen.route) },
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_dialog_dialer),
                    contentDescription = null,
                    tint = Color.White
                )
            },
            label = { Text("Accueil", color = Color.White) }
        )
        NavigationBarItem(
            selected = false,
            onClick = {navController.navigate(ScreensRoutes.PostScreen.route) },
            icon = {
                Icon(
                    painter = painterResource(id = android.R.drawable.ic_menu_add),
                    contentDescription = null,
                    tint = Color.White
                )
            },
            label = { Text("Post", color = Color.White) }
        )

        NavigationBarItem(
            selected = false,
            onClick = {navController.navigate(ScreensRoutes.ProfileScreen.route) },
            icon = {
                Icon(
                    painter = painterResource(id = android.R.drawable.ic_menu_myplaces),
                    contentDescription = null,
                    tint = Color.White
                )
            },
            label = { Text("Profil", color = Color.White) }
        )

    }
}