package com.example.projetsessionandroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.projetsessionandroid.routes.NavGraph
import com.example.projetsessionandroid.ui.screen.loginScreen.LoginPage
import com.example.projetsessionandroid.ui.theme.ProjetSessionAndroidTheme
import com.example.projetsessionandroid.ui.viewModel.UserViewModel

class MainActivity : ComponentActivity() {
    private var isLoggedIn by mutableStateOf(true)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProjetSessionAndroidTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    if (isLoggedIn) {
                        println("log reussi")
                        // Utilisateur connecté, afficher le reste de l'application
                        val navController = rememberNavController()
                        NavGraph(navController = navController)
                    } else {
                        // Afficher la page de connexion
                        LoginPage { mail, password ->
                            // Ici, vous pouvez vérifier les informations d'identification
                            println(mail)
                            println(password)
                            // Si les informations d'identification sont correctes, définissez isLoggedIn sur true
                            isLoggedIn = true
                        }
                    }
            }
        }
    }
}}

