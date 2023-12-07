package com.example.projetsessionandroid.data.service

import com.example.projetsessionandroid.data.model.Food
import retrofit2.Call
import retrofit2.http.*

//Fichier qui permet de tracer les routes vers l'api pour le model Food

interface FoodService {
    //recuperer tous les foods
    @GET("food/")
    suspend fun getAllFoods(): List<Food>

    //creer un Food
    @POST("food/")
    suspend fun createFood(@Body food: Food): Food

    //recuperer un food en fonction de son id
    @GET("food/{id}")
    suspend fun getFoodById(@Path("id") id: String): Food

    //modifier un food en fonction de son id
    @PUT("food/{id}")
    suspend fun updateFood(@Path("id") id: String, @Body food: Food): Food

    //supprimer un food
    @DELETE("food/{id}")
    suspend fun deleteFood(@Path("id") id: String): Void
}