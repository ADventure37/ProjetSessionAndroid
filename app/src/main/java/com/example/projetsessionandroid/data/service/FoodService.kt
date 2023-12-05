package com.example.projetsessionandroid.data.service

import com.example.projetsessionandroid.data.model.Food
import retrofit2.Call
import retrofit2.http.*

interface FoodService {
    @GET("/")
    fun getAllFoods(): List<Food>

    @POST("/")
    fun createFood(@Body food: Food): Call<Food>

    @GET("/{id}")
    fun getFoodById(@Path("id") id: String): Food

    @PUT("/{id}")
    fun updateFood(@Path("id") id: String, @Body food: Food): Call<Food>

    @DELETE("/{id}")
    fun deleteFood(@Path("id") id: String): Call<Void>
}