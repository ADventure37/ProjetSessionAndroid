package com.example.projetsessionandroid.ui.screen.profileScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.projetsessionandroid.data.model.User
import com.example.projetsessionandroid.ui.screen.navigation.BottomBar
import com.example.projetsessionandroid.ui.viewModel.UserViewModel

// Classe de l'ensemble des données correspondent à la localisation
data class LocationData(
    val newLocation: String,
    val newCity: String,
    val newPostcode: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreenView(navController: NavHostController, user:User)  {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        text = "Profil",
                        color = Color.White
                    )
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color.Red)            )
        },
        containerColor = Color.White,
        bottomBar ={ BottomBar(navController = navController) }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)

        ) {
            profilView(navController, user)
        }
    }
}

@Composable
fun profilView(navController: NavHostController, user : User){
    // Déclaration des variables qui permettent de stocker l'état de l'afficher des vues de modifications
    var passwordUpdateView by remember { mutableStateOf(false) }
    var locationUpdateView by remember { mutableStateOf(false) }

    //variable permettant de recuperer et de stocker les valeurs de l'api
    val userViewModel: UserViewModel = viewModel()
    if (user._id != null) {
        userViewModel.getUserById(user._id)
    }
    val user by userViewModel.user.observeAsState()

    user?.let { userData->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            //Affichage des données du profil
            Text(
                text = "${userData.username}",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(8.dp)) // Ajoute un espace vertical entre les boutons

            Text(
                text = "${userData.location}\n${userData.postcode}, ${userData.city}\nSolde : ${userData.solde}",
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(8.dp)) // Ajoute un espace vertical entre les boutons

            //Bouton pour modifier le mot de passe
            Button(
                onClick = { passwordUpdateView = true },
                modifier = Modifier
                    .padding(8.dp)
                    .background(Color.White)
            ) {
                Text(text = "Changer le mot de passe", modifier = Modifier.padding(8.dp),)
            }

            //Bouton pour modifier la losalisation de l'utilisateur
            Button(
                onClick = { locationUpdateView = true },
                modifier = Modifier
                    .padding(8.dp)
                    .background(Color.White)
            ) {
                Text(text = "Changer la localisation", modifier = Modifier.padding(8.dp))
            }
            //Bouton pour accéder à l'historique des commandes
            Button(
                onClick = {
                    navController.navigate("historic_command_screen")
                },
                modifier = Modifier
                    .padding(8.dp)
                    .background(Color.White)
            ) {
                Text(text = "Mes commandes", modifier = Modifier.padding(8.dp),)
            }
            //Bouton pour accéder à l'historique des dons
            Button(
                onClick = {
                    navController.navigate("historic_don_screen")
                },
                modifier = Modifier
                    .padding(8.dp)
                    .background(Color.White)
            ) {
                Text(text = "Mes dons", modifier = Modifier.padding(8.dp),)
            }

            //Affichage de la fenêtre pour changer de mot de passe
            if (passwordUpdateView == true) {
                updatePassword(
                    onPasswordEntered = { newPassword ->
                        var updatedUser = User(
                            _id = userData._id,
                            username = userData.username,
                            lastname = userData.lastname,
                            email = userData.email,
                            password = newPassword,
                            location = userData.location,
                            postcode = userData.postcode,
                            city = userData.city,
                            solde = userData.solde
                        )
                        userViewModel.updateUser(updatedUser)
                    },
                    onDismiss = { passwordUpdateView = false }
                )
            }
            // Affichage de la fênetre pour changer de localisation
            if (locationUpdateView == true) {
                updateLocation(
                    onLocationEntered = { locationData: LocationData ->
                        var updatedUser = User(
                            _id = userData._id,
                            username = userData.username,
                            lastname = userData.lastname,
                            email = userData.email,
                            password = userData.password,
                            location = locationData.newLocation,
                            postcode = locationData.newPostcode,
                            city = locationData.newCity,
                            solde = userData.solde
                        )
                        userViewModel.updateUser(updatedUser)
                    },
                    onDismiss = { locationUpdateView = false }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun updatePassword(
    onPasswordEntered: (String) -> Unit,
    onDismiss: () -> Unit
) {
    var password by remember { mutableStateOf("") }

    Dialog(
        onDismissRequest = { onDismiss() },
        content = {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0, 0, 0, 150))
                    .graphicsLayer(alpha = 1f)
                    .clipToBounds(),
                contentAlignment = Alignment.Center
            ) {
                Column (modifier = Modifier
                    .padding(16.dp)
                    .background(MaterialTheme.colorScheme.background, RoundedCornerShape(8.dp))
                    .padding(16.dp)
                ){
                    Text(text = "Nouveau mot de passe")
                    OutlinedTextField(
                        value = password,
                        onValueChange = { password = it },
                        label = { Text("Mot de passe") },
                        visualTransformation = PasswordVisualTransformation(),
                        keyboardOptions = KeyboardOptions.Default.copy(
                            imeAction = ImeAction.Done
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    )
                    TextButton(onClick = { onDismiss() }) {
                        Text("Annuler")
                    }
                    TextButton(
                        onClick = {
                            onPasswordEntered(password)
                            onDismiss()
                        }
                    ) {
                        Text("OK")
                    }
                }
            }
        }
    )
}

@Composable
fun updateLocation(
    onLocationEntered: (LocationData) -> Unit,
    onDismiss: () -> Unit
) {

    var location by remember { mutableStateOf("") }
    var city by remember { mutableStateOf("") }
    var postcode by remember { mutableStateOf("") }

    Dialog(
        onDismissRequest = { onDismiss() },
        content = {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0, 0, 0, 150))
                    .graphicsLayer(alpha = 1f)
                    .clipToBounds(),
                contentAlignment = Alignment.Center
            ) {
                Column (modifier = Modifier
                    .padding(16.dp)
                    .background(MaterialTheme.colorScheme.background, RoundedCornerShape(8.dp))
                    .padding(16.dp)
                ){
                    Text(text = "Nouvelle adresse")
                    OutlinedTextField(
                        value = location,
                        onValueChange = { location = it },
                        label = { Text("Location") },
                        keyboardOptions = KeyboardOptions.Default.copy(
                            imeAction = ImeAction.Done
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    )
                    OutlinedTextField(
                        value = city,
                        onValueChange = { city = it },
                        label = { Text("Ville") },
                        keyboardOptions = KeyboardOptions.Default.copy(
                            imeAction = ImeAction.Done
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    )
                    OutlinedTextField(
                        value = postcode,
                        onValueChange = { postcode = it },
                        label = { Text("Code postal") },
                        keyboardOptions = KeyboardOptions.Default.copy(
                            imeAction = ImeAction.Done
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    )
                    TextButton(onClick = { onDismiss() }) {
                        Text("Annuler")
                    }
                    TextButton(
                        onClick = {
                            onLocationEntered(LocationData(location, city, postcode))
                            onDismiss()
                        }
                    ) {
                        Text("OK")
                    }
                }
            }
        }
    )
}


