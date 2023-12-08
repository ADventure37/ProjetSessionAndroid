package com.example.projetsessionandroid.ui.screen.accueilScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable

import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.projetsessionandroid.data.model.Food
import com.example.projetsessionandroid.data.model.User
import com.example.projetsessionandroid.ui.screen.navigation.BottomBar
import com.example.projetsessionandroid.ui.viewModel.FoodViewModel
import com.example.projetsessionandroid.ui.viewModel.UserViewModel
import kotlinx.coroutines.flow.map
import kotlin.time.Duration.Companion.minutes

//Page d'accueil de l'application
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AccueilScreenView(navController: NavHostController, user: User)  {
    //Structure de la page
    Scaffold(
        //haut de la page
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        text = "Favor Food",
                        color = Color.White
                    )
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color.Red)            )
        },
        containerColor = Color.White,
        //Bas de la page: barre de navigation
        bottomBar ={ BottomBar(navController = navController)}
    ){ innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
        ) {
            //Corps de la page
            MySearchScreen(navController)
        }
    }
}

//Barre de recherche
@Composable
fun SearchBar(onSearchClicked: (String) -> Unit) {
    var text by remember { mutableStateOf(TextFieldValue()) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        OutlinedTextField(
            value = text,
            onValueChange = { newText ->
                text = newText
            },
            modifier = Modifier
                .weight(1f)
                .padding(end = 8.dp),
            textStyle = MaterialTheme.typography.bodyMedium,
            singleLine = true,
            placeholder = { Text("Recherche par Ville") }
        )
        Button(
            onClick = { onSearchClicked(text.text) },
            modifier = Modifier
                .height(IntrinsicSize.Min)
        ) {
            Text(text = "Rechercher")
        }


    }
}

@Composable
fun MySearchScreen(navController: NavHostController) {
    //Valeur initial de la barre de recherche
    var search by remember { mutableStateOf("") }

    //Variables permettant de recuperer et de stocker les donnees de l'api
    val foodViewModel : FoodViewModel = viewModel()
    val foods by foodViewModel.foods.collectAsState()
    if (foods.isEmpty()) foodViewModel.getAllFood()

    // Votre logique de recherche et d'affichage ici
    SearchBar(onSearchClicked = { searchText ->
        search = searchText
    })

    //Algo d'affichage des annonces en fonction de la ville rentrer dans la barre de recherche
    for(food in foods){
        if(search == ""){
            ProductItem(food, navController)
        }else{
            val userViewModel : UserViewModel = viewModel()
            val users by userViewModel.users.collectAsState()
            if (users.isEmpty()) userViewModel.getUsersByCity(search)
            if(users.isEmpty()){
                Text(text = "")
            }else{
                for(user in users){
                    if(user._id == food.idDonator){
                        ProductItem(food, navController)
                    }
                }
            }
        }
    }

}

//Carte qui permet d'afficher les informations essentiel d'une annonce
@Composable
fun ProductItem(food:Food, navController: NavHostController) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            //Affichage des informations importantes de l'annonce
            Text(text = "${food.name}", style = MaterialTheme.typography.headlineMedium)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Quantity: ${food.quantity}g", style = MaterialTheme.typography.bodyLarge)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Allergens: ${food.allergen}", style = MaterialTheme.typography.bodyLarge)
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Date d'Expiration : ${food.expiryDate}",
                style = MaterialTheme.typography.bodyLarge
            )
            Spacer(modifier = Modifier.height(8.dp))
            if(food.idClient == null){
                Text(
                    text = "Status : Libre",
                    style = MaterialTheme.typography.bodyLarge
                )
            }else{
                Text(
                    text = "Status : Reserver",
                    style = MaterialTheme.typography.bodyLarge
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            //Redirection vers la page detail de cette annonce
            Button(onClick = {
                navController.navigate("detail_screen/${food._id}")
            }) {
                Text(text = "Détails")
            }
        }
    }
}