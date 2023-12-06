package com.example.projetsessionandroid.ui.screen.loginScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.projetsessionandroid.ui.viewModel.FoodViewModel
import com.example.projetsessionandroid.ui.viewModel.UserViewModel

@Composable
fun LoginPage(onLogin: (mail: String, password: String) -> Unit) {
    val userViewModel: UserViewModel = viewModel()

    val users by userViewModel.users.collectAsState()
    if (users.isEmpty()) userViewModel.getAllUser()

    var mail by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = mail,
            onValueChange = { mail = it },
            label = { Text("Nom d'utilisateur") }
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
                    }
                }
                if (verif  === true){
                    onLogin(mail, password)
                }
                      },
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text("Connexion")
        }
    }
}