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

    val users = MutableStateFlow<List<User>>(listOf())
    val _user = MutableLiveData<User>()
    val user: LiveData<User> get() = _user

    val service = Retrofit.Builder()
        .baseUrl("http://10.0.2.2:3000/api/user")
        .addConverterFactory(MoshiConverterFactory.create())
        .build()
        .create(UserService::class.java)

    fun getAllUser(){
        viewModelScope.launch {
            try {
                users.value = service.getAllUsers()
            } catch (e: Exception) {
                // Gérer les erreurs
                e.printStackTrace()
            }
        }
    }

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

    fun updateUser(user : User){
        viewModelScope.launch {
            try {
                service.updateUser(user.id,user)
            } catch (e: Exception) {
                // Gérer les erreurs
                e.printStackTrace()
            }
        }
    }

    fun deleteUser(user : User){
        viewModelScope.launch {
            try {
                service.deleteUser(user.id)
            } catch (e: Exception) {
                // Gérer les erreurs
                e.printStackTrace()
            }
        }
    }
}