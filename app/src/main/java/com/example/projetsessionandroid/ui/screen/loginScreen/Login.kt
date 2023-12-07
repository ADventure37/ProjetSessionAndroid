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

@Composable
fun LoginPage(onLogin: (mail: String, password: String, user: User) -> Unit) {
    val userViewModel: UserViewModel = viewModel()

    val users by userViewModel.users.collectAsState()
    if (users.isEmpty()) userViewModel.getAllUser()

    var mail by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var userConnected: User? = null

    val imagePath = "app/src/main/java/com/example/projetsessionandroid/data/photo/logo.png"   // Remplacez ceci par votre chemin d'accès local à l'image

    val file = File(imagePath)
    val painter = rememberImagePainter(
        data = file
    )


    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
//        Image(
//            painter = painter,
//            contentDescription = "Image de profil",
//            modifier = Modifier.fillMaxSize(),
//            contentScale = ContentScale.FillBounds,
//            colorFilter = ColorFilter.tint(Color.White) // Vous pouvez ajouter un filtre de couleur à l'image si nécessaire
//        )
        Image(
            painter = painterResource(id = R.mipmap.logo),
            contentDescription = "Sample Image",
            modifier = Modifier.size(200.dp).padding(bottom = 16.dp) // Modifier la taille de l'image selon vos besoins
        )
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
        Button(
            onClick = {
                var verif = false
                for(user in users){
                    println(user.email + " vs "+ mail)
                    println(user.password + " vs "+ password)
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