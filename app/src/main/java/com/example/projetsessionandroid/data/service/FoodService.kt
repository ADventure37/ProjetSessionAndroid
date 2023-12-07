package com.example.projetsessionandroid.data.service

import com.example.projetsessionandroid.data.model.Food
import retrofit2.Call
import retrofit2.http.*

interface FoodService {
    @GET("food/")
    suspend fun getAllFoods(): List<Food>

    @POST("food/")
    suspend fun createFood(@Body food: Food): Food

    @GET("food/{id}")
    suspend fun getFoodById(@Path("id") id: String): Food

    @PUT("food/{id}")
    suspend fun updateFood(@Path("id") id: String, @Body food: Food): Food

    @DELETE("food/{id}")
    suspend fun deleteFood(@Path("id") id: String): Void
}