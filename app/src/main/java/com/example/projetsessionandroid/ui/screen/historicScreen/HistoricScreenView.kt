package com.example.projetsessionandroid.ui.screen.historicScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.projetsessionandroid.data.model.User
import com.example.projetsessionandroid.ui.screen.accueilScreen.ProductItem
import com.example.projetsessionandroid.ui.screen.navigation.BottomBar
import com.example.projetsessionandroid.ui.viewModel.FoodViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HistoricScreenView(navController: NavHostController, user: User, historic : String){
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
                .verticalScroll(rememberScrollState())

        ) {
            // Affichage des dons ou des commands en fonction de l'historique souhaité
            if(historic == "command") {
                itemHistoricCommand(user, navController)
            }
            if(historic =="don"){
                itemHistoricDon(user, navController)
            }
        }
    }
}

@Composable
fun itemHistoricCommand(user:User, navController: NavHostController){
    //Variable aui permet de stocker les informations recuperer de l'api
    val foodViewModel : FoodViewModel = viewModel()
    val foods by foodViewModel.foods.collectAsState()
    if (foods.isEmpty()) foodViewModel.getAllFood()

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Mes commandes",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )
        //Récupération des commandes de l'utilisateur connecté et affichage
        for(food in foods) {
            if (food.idClient == user._id){
                ProductItem(food, navController)
            }
        }
    }
}

@Composable
fun itemHistoricDon(user:User, navController: NavHostController){
    //Declaration des variables aui permettront de stocker les informations recuperer de l'api
    val foodViewModel : FoodViewModel = viewModel()
    val foods by foodViewModel.foods.collectAsState()
    if (foods.isEmpty()) foodViewModel.getAllFood()

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Mes dons",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )
        // Récupérer les dons de l'utilsateur et affichage
        for(food in foods) {
            if (user._id == food.idDonator){
                ProductItem(food, navController)
            }
        }
    }
}