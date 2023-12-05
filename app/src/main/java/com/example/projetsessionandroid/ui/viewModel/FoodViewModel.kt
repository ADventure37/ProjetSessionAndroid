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
    val foods = MutableStateFlow<List<Food>>(listOf())
    val _food = MutableLiveData<Food>()
    val food: LiveData<Food> get() = _food

    val service = Retrofit.Builder()
        .baseUrl("http://10.0.2.2:3000/api/food")
        .addConverterFactory(MoshiConverterFactory.create())
        .build()
        .create(FoodService::class.java)

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

    fun createFood(food : Food){
        viewModelScope.launch {
            try {
                service.createFood(food)
            } catch (e: Exception) {
                // Gérer les erreurs
                e.printStackTrace()
            }
        }
    }

    fun updateFood(food : Food){
        viewModelScope.launch {
            try {
                service.updateFood(food.id,food)
            } catch (e: Exception) {
                // Gérer les erreurs
                e.printStackTrace()
            }
        }
    }

    fun deleteFood(food : Food){
        viewModelScope.launch {
            try {
                service.deleteFood(food.id)
            } catch (e: Exception) {
                // Gérer les erreurs
                e.printStackTrace()
            }
        }
    }


}