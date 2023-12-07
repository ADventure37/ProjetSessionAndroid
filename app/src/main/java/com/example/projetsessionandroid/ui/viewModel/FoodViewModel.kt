package com.example.projetsessionandroid.ui.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.projetsessionandroid.data.model.Food
import com.example.projetsessionandroid.data.service.FoodService
import com.example.projetsessionandroid.data.service.UserService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class FoodViewModel: ViewModel() {
    //Initialisation des variables qui vont nous permettrent de stocker les foods recuperer de l'api
    val foods = MutableStateFlow<List<Food>>(listOf())
    val _food = MutableLiveData<Food>()
    val food: LiveData<Food> get() = _food

    //Creation du lien entre l'app android et l'api
    val service = Retrofit.Builder()
        .baseUrl("http://10.0.2.2:3000/api/")
        .addConverterFactory(MoshiConverterFactory.create())
        .build()
        .create(FoodService::class.java)

    //Fonction pour recuperer tous les foods
    fun getAllFood(){
        viewModelScope.launch {
            try {
                foods.value = service.getAllFoods()
            } catch (e: Exception) {
                // Gérer les erreurs
                e.printStackTrace()
            }
        }
    }

    //Fonction pour recuperer un food en fonction de son id
    fun getFoodById(id : String){
        viewModelScope.launch {
            try {
                _food.value = service.getFoodById(id)
            } catch (e: Exception) {
                // Gérer les erreurs
                e.printStackTrace()
            }
        }
    }

    //Fonction pour ajouter un food dans la base de donnee
    fun createFood(food : Food){
        viewModelScope.launch {
            try {
                println(1)
                var food1 = service.createFood(food)
                println(food1)
            } catch (e: Exception) {
                // Gérer les erreurs
                e.printStackTrace()
            }
        }
    }

    //Fonction pour modifier un food stocker dans la base de donnee
    fun updateFood(food : Food){
        viewModelScope.launch {
            try {
                service.updateFood(food._id,food)
            } catch (e: Exception) {
                // Gérer les erreurs
                e.printStackTrace()
            }
        }
    }

    //Fonction pour supprimer un food de la base de donnee
    fun deleteFood(food : Food){
        viewModelScope.launch {
            try {
                service.deleteFood(food._id)
            } catch (e: Exception) {
                // Gérer les erreurs
                e.printStackTrace()
            }
        }
    }


}