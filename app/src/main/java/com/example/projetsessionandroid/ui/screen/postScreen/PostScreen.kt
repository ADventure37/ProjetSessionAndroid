package com.example.projetsessionandroid.ui.screen.postScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.projetsessionandroid.data.model.Food
import com.example.projetsessionandroid.ui.screen.navigation.BottomBar
import com.example.projetsessionandroid.ui.viewModel.FoodViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PostScreenView(navController: NavHostController)  {

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        text = "Ajouter une annonce",
                        color = Color.White
                    )
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color.Red)            )
        },
        containerColor = Color.White,
        bottomBar ={ BottomBar(navController = navController) }
    ){ innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            itemPostAnnonce()
        }
    }
}


@Composable
fun itemPostAnnonce() {
    val initialTextValue = remember { "" }
    val foodViewModel: FoodViewModel = viewModel()
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        val nameState = remember { mutableStateOf("") }
        val descriptionState = remember { mutableStateOf("") }
        val allergenState = remember { mutableStateOf("") }
        val quantityState = remember { mutableStateOf("") }
        val expiryDateState = remember { mutableStateOf("") }

        OutlinedTextField(
            value = nameState.value,
            onValueChange = { nameState.value = it },
            label = { Text("Nom") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        )

        OutlinedTextField(
            value = descriptionState.value,
            onValueChange = { descriptionState.value = it },
            label = { Text("Description") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        )

        OutlinedTextField(
            value = allergenState.value,
            onValueChange = { allergenState.value = it },
            label = { Text("allergène") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        )

        Row {
            OutlinedTextField(
                value = quantityState.value,
                onValueChange = { quantityState.value = it },
                label = { Text("Quantité (en g)") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier
                    .weight(30F)
                    .padding(bottom = 16.dp)
            )

            OutlinedTextField(
                value = expiryDateState.value,
                onValueChange = { expiryDateState.value = it },
                label = { Text("Date de péremption") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.weight(30F)
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(
                onClick = {
                    var allergen = allergenState.value.split(",")

                        var food = Food("", nameState.value, descriptionState.value,quantityState.value.toInt(),allergen, expiryDateState.value, "656ea0acbd33593193b1858f","")

                        foodViewModel.createFood(food)


                },
                modifier = Modifier.weight(1f).padding(8.dp)
            ) {
                Text("Valider")
            }

            Button(
                onClick = {
                    nameState.value = initialTextValue
                    descriptionState.value = initialTextValue
                    quantityState.value = initialTextValue
                    expiryDateState.value = initialTextValue
                },
                modifier = Modifier.weight(1f).padding(8.dp)
            ) {
                Text("Réinitialiser")
            }
        }

        }

}