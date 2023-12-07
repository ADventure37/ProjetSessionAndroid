package com.example.projetsessionandroid.ui.screen.detailScreen

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.projetsessionandroid.data.model.Food
import com.example.projetsessionandroid.data.model.User
import com.example.projetsessionandroid.ui.screen.accueilScreen.MySearchScreen
import com.example.projetsessionandroid.ui.screen.navigation.BottomBar
import com.example.projetsessionandroid.ui.viewModel.FoodViewModel
import com.example.projetsessionandroid.ui.viewModel.UserViewModel
import org.osmdroid.config.Configuration
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView

// Composant Jetpack Compose pour afficher les détails d'une annonce
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreenView(navController: NavHostController, user: User, foodId : String?) {
    //variables permettant de recuperer et de stocker les valeurs de l'api
    var foodViewModel: FoodViewModel = viewModel()
    var userViewModel: UserViewModel = viewModel()

    if (foodId != null) {
        foodViewModel.getFoodById(foodId)
    }

    val food by foodViewModel.food.observeAsState()

    //Exploitation des donnees fournient par l'api
    food?.let { foodData ->
        //variables permettant de recuperer et de stocker les valeurs de l'api
        userViewModel.getUserById(foodData.idDonator)
        val donator by userViewModel.user.observeAsState()
        //Exploitation des donnees fournient par l'api
        donator?.let { donatorData ->
            //Structure de la page
            Scaffold(
                //haut de la page
                topBar = {
                    TopAppBar(
                        title = {
                            Text(
                                modifier = Modifier.fillMaxWidth(),
                                textAlign = TextAlign.Center,
                                text = foodData.name,
                                color = Color.White
                            )
                        },
                        colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color.Red)            )
                },
                containerColor = Color.White,
                //Bas de la page qui correspond a la barre de navigation
                bottomBar ={ BottomBar(navController = navController) }
            ){ innerPadding ->
                //Corps de la page
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                        .verticalScroll(rememberScrollState())
                ) {
                    //Affichage des informations de l'annonces
                    DetailsDescription(navController, user,donatorData, foodData)
                }
            }
        }
    }
}

//Affichage des informations de l'annonces
@Composable
fun DetailsDescription(navController: NavHostController, user: User,donator: User, food : Food) {
    var foodViewModel: FoodViewModel = viewModel()

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            //Affichage et mise en page des informations
            Text(
                text = food.name,
                style = MaterialTheme.typography.headlineLarge
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Description: ${food.description}",
                style = MaterialTheme.typography.headlineSmall
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Allergènes: ${food.allergen}",
                style = MaterialTheme.typography.headlineSmall
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Quantité: ${food.quantity}g",
                style = MaterialTheme.typography.headlineSmall
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Date de péremption: ${food.expiryDate}",
                style = MaterialTheme.typography.headlineSmall
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Donné par: ${donator.username} ${donator.lastname}",
                style = MaterialTheme.typography.headlineSmall
            )
            //est afficher si et seulement si le plat est reserve
            if(food.idClient != null){
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Mail: ${donator.email} ",
                    style = MaterialTheme.typography.headlineSmall
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "À: ${donator.city} ",
                style = MaterialTheme.typography.headlineSmall
            )
            //est afficher si et seulement si le plat est reserve
            if(food.idClient != null){
                var adresse = donator.location.split(" ")
                Spacer(modifier = Modifier.height(8.dp))
                LinkedText(url = "https://www.google.com/maps/place/" +
                        "${adresse.get(0)}+${adresse.get(1)}+${adresse.get(2)}" +
                        ",+${donator.city},+${donator.postcode}/",
                        text = "Veuillez récupérer la nourriture ici")
            }

            //Tentative d'affichage d'une map
//            Spacer(modifier = Modifier.height(60.dp))
//            MapView pour afficher la mini-map
//            OsmMap()

            Spacer(modifier = Modifier.height(16.dp))
            //est afficher si et seulement si le plat n'est pas reserve
            if(food.idClient == null) {
                //Si l'utilisateur est le proprietaire de l'annonce, il peut la supprimer
                if(user._id == donator._id){
                    Button(onClick = {
                        foodViewModel.deleteFood(food)
                        navController.navigate("home_screen")
                    }) {
                        Text(text = "Supprimer")
                    }
                //Sinon, il peut la reserver
                }else{
                    Button(onClick = {
                        food.idClient = user._id
                        foodViewModel.updateFood(food)
                        navController.navigate("detail_screen/${food._id}")
                    }) {
                        Text(text = "Réserver")
                    }
                }
            }
        }
    }
}

//Tentative d'utilisation d'une map
@SuppressLint("UnrememberedMutableState")
@Composable
fun OsmMap() {

    val context: Context = LocalContext.current
    // Chemin de base pour osmdroid
    var osmdroidBasePath = context.filesDir.absolutePath + "/osmdroid"
    // Chemin du cache des tuiles
    var osmdroidTileCache = osmdroidBasePath + "/tiles"

    // Initialiser la configuration pour OSM
    Configuration.getInstance().apply {
        userAgentValue = context.packageName
        osmdroidBasePath = osmdroidBasePath
        osmdroidTileCache = osmdroidTileCache
    }
    // Créer la vue de la carte
    val mapView = MapView(context).apply {
        // Utiliser OpenStreetMap comme source de tuiles
        setTileSource(TileSourceFactory.DEFAULT_TILE_SOURCE)
        // Configuration supplémentaire de la carte (zoom, etc.)
        setMultiTouchControls(true)
    }

    AndroidView(
        modifier = Modifier.fillMaxSize(),
        factory = { mapView }
    )
}

//Lien cliquable qui renvoie a google map
@Composable
fun LinkedText(url: String, text: String) {
    val context = LocalContext.current
    Text(
        text = text,
        style = TextStyle(color = Color.Blue, fontWeight = FontWeight.Bold),
        modifier = Modifier.clickable {
            // Ouvrir le lien dans un navigateur
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(url)
            context.startActivity(intent)
        }
    )
}


