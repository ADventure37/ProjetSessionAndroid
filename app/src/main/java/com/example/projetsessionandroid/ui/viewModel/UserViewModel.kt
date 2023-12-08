package com.example.projetsessionandroid.ui.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.projetsessionandroid.data.model.User
import com.example.projetsessionandroid.data.service.UserService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class UserViewModel: ViewModel() {

    //Initialisation des variables qui vont nous permettrent de stocker les users recuperer de l'api
    val users = MutableStateFlow<List<User>>(listOf())
    val _user = MutableLiveData<User>()
    val user: LiveData<User> get() = _user

    //Creation du lien entre l'app android et l'api
    val service = Retrofit.Builder()
        .baseUrl("http://10.0.2.2:3000/api/")
        .addConverterFactory(MoshiConverterFactory.create())
        .build()
        .create(UserService::class.java)

    //Fonction pour recuperer tous les users
    fun getAllUser(){
        viewModelScope.launch {
            users.value = service.getAllUsers()
        }
    }

    //Fonction pour recuperer des users en fonction de leurs villes
    fun getUsersByCity(city :String){
        viewModelScope.launch {
            users.value = service.getUsersByCity(city)
        }
    }

    //Fonction pour recuperer un user en fonction de son id
    fun getUserById(id : String){
        viewModelScope.launch {
            try {
                _user.value = service.getUser(id)
            } catch (e: Exception) {
                // Gérer les erreurs
                e.printStackTrace()
            }
        }
    }

    //Fonction pour ajouter un user dans la base de donnee
    fun createUser(user : User){
        viewModelScope.launch {
            try {
                service.createUser(user)
            } catch (e: Exception) {
                // Gérer les erreurs
                e.printStackTrace()
            }
        }
    }

    //Fonction pour modifier un user stocker dans la base de donnee
    fun updateUser(user : User){
        viewModelScope.launch {
            try {
                service.updateUser(user._id,user)
            } catch (e: Exception) {
                // Gérer les erreurs
                e.printStackTrace()
            }
        }
    }

    //Fonction pour supprimer un user de la base de donnee
    fun deleteUser(user : User){
        viewModelScope.launch {
            try {
                service.deleteUser(user._id)
            } catch (e: Exception) {
                // Gérer les erreurs
                e.printStackTrace()
            }
        }
    }
}