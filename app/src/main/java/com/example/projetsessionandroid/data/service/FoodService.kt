package com.example.projetsessionandroid.data.service

import com.example.projetsessionandroid.data.model.Food
import retrofit2.Call
import retrofit2.http.*

interface FoodService {
    @GET("food/")
    fun getAllFoods(): List<Food>

    @POST("food/")
    suspend fun createFood(@Body food: Food): Food

    @GET("food/{id}")
    fun getFoodById(@Path("id") id: String): Food

    @PUT("food/{id}")
    fun updateFood(@Path("id") id: String, @Body food: Food): Call<Food>

    @DELETE("food/{id}")
    fun deleteFood(@Path("id") id: String): Call<Void>
}