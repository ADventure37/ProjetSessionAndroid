package com.example.projetsessionandroid.data.service

import com.example.projetsessionandroid.data.model.Comment
import retrofit2.Call
import retrofit2.http.*

interface CommentService {
    @GET("/")
    fun getAllComments(): List<Comment>

    @POST("/")
    fun createComment(@Body comment: Comment): Call<Comment>

    @GET("/{id}")
    fun getCommentById(@Path("id") id: String): Comment

    @PUT("/{id}")
    fun updateComment(@Path("id") id: String, @Body comment: Comment): Call<Comment>

    @DELETE("/{id}")
    fun deleteComment(@Path("id") id: String): Call<Void>
}