package com.example.projetsessionandroid.data.service

import com.example.projetsessionandroid.data.model.Comment
import retrofit2.Call
import retrofit2.http.*

//Fichier qui permet de tracer les routes vers l'api pour le model Comment

interface CommentService {
    //recuperer tous les comments
    @GET("/")
    fun getAllComments(): List<Comment>

    //creer un Comment
    @POST("/")
    fun createComment(@Body comment: Comment): Call<Comment>

    //recuperer un comment en fonction de son id
    @GET("/{id}")
    fun getCommentById(@Path("id") id: String): Comment

    //modifier un comment en fonction de son id
    @PUT("/{id}")
    fun updateComment(@Path("id") id: String, @Body comment: Comment): Call<Comment>

    //supprimer un comment
    @DELETE("/{id}")
    fun deleteComment(@Path("id") id: String): Call<Void>
}