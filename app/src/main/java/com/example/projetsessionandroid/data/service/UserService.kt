package com.example.projetsessionandroid.data.service

import com.example.projetsessionandroid.data.model.User
import retrofit2.Call
import retrofit2.http.*

interface UserService {
    @POST("user/")
    fun createUser(@Body user: User)

    @GET("user/{id}")
    fun getUser(@Path("id") id: String): User

    @GET("user/city/{city}")
    suspend fun getUsersByCity(@Path("city") city: String): List<User>

    @GET("user/")
    suspend fun getAllUsers(): List<User>

    @PUT("user/{id}")
    suspend fun updateUser(@Path("id") id: String, @Body user: User)

    @DELETE("user/{id}")
    fun deleteUser(@Path("id") id: String): Call<Void>

    @GET("user/login")
    fun loginUser(): Call<User>
}