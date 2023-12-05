package com.example.projetsessionandroid.data.service

import com.example.projetsessionandroid.data.model.User
import retrofit2.Call
import retrofit2.http.*

interface UserService {
    @POST("/")
    fun createUser(@Body user: User)

    @GET("/{id}")
    fun getUser(@Path("id") id: String): User

    @GET("/")
    fun getAllUsers(): List<User>

    @PUT("/{id}")
    fun updateUser(@Path("id") id: String, @Body user: User)

    @DELETE("/{id}")
    fun deleteUser(@Path("id") id: String): Call<Void>

    @GET("/login")
    fun loginUser(): Call<User>
}