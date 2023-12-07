package com.example.projetsessionandroid.data.service

import com.example.projetsessionandroid.data.model.User
import retrofit2.Call
import retrofit2.http.*

//Fichier qui permet de tracer les routes vers l'api pour le model User
interface UserService {
    //creer un User
    @POST("user/")
    fun createUser(@Body user: User)

    //recuperer un user en fonction de son id
    @GET("user/{id}")
    suspend fun getUser(@Path("id") id: String): User

    //recuperer un user en fonction de sa ville de residence
    @GET("user/city/{city}")
    suspend fun getUsersByCity(@Path("city") city: String): List<User>

    //recuperer tous les users
    @GET("user/")
    suspend fun getAllUsers(): List<User>

    //modifier un user en fonction de son id
    @PUT("user/{id}")
    fun updateUser(@Path("id") id: String, @Body user: User)

    //supprimer un user
    @DELETE("user/{id}")
    fun deleteUser(@Path("id") id: String): Call<Void>

}