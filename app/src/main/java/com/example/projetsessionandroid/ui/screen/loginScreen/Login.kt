package com.example.projetsessionandroid.ui.screen.loginScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberImagePainter
import com.example.projetsessionandroid.R
import com.example.projetsessionandroid.data.model.User
import com.example.projetsessionandroid.ui.viewModel.FoodViewModel
import com.example.projetsessionandroid.ui.viewModel.UserViewModel
import java.io.File

//Page de login de l'application
@Composable
fun LoginPage(onLogin: (mail: String, password: String, user: User) -> Unit) {
    //Declaration des variables aui permettront de stocker les informations recuperer de l'api
    val userViewModel: UserViewModel = viewModel()
    val users by userViewModel.users.collectAsState()
    if (users.isEmpty()) userViewModel.getAllUser()

    //Declaration des variables qui permettront de stocker les informations rentrer par l'utilisateur
    var mail by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    var userConnected: User? = null



    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        //Affichage du logo de notre entreprise
        Image(
            painter = painterResource(id = R.mipmap.logo),
            contentDescription = "Sample Image",
            modifier = Modifier.size(200.dp).padding(bottom = 16.dp) // Modifier la taille de l'image selon vos besoins
        )
        //Zone de texte pour renter l'email et le mot de passe
        TextField(
            value = mail,
            onValueChange = { mail = it },
            label = { Text("Adresse email") }
        )
        TextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Mot de passe") },
            visualTransformation = PasswordVisualTransformation()
        )
        //Bouton de connexion
        Button(
            onClick = {
                //Verification de l'authenticite de la connexion
                var verif = false
                for(user in users){
                    if(user.email == mail && user.password == password){
                        verif = true
                        userConnected = user
                    }
                }
                if (verif  === true){
                    userConnected?.let { onLogin(mail, password, it) }
                }
                      },
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text("Connexion")
        }
    }
}