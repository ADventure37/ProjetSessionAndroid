package com.example.projetsessionandroid.data.model

import com.google.gson.annotations.SerializedName
import java.util.Date

class Comment (
    @SerializedName("id")
    val id:String,
    @SerializedName("title")
    val title:String,
    @SerializedName("description")
    val description:String,
    @SerializedName("mark")
    val mark:Int,
    @SerializedName("date")
    val date: Date,
    @SerializedName("idCommentator")
    val idCommentator:String,
    @SerializedName("idTarget")
    val idTarget:String,
)