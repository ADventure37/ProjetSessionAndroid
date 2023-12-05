package com.example.projetsessionandroid.data.model

import com.google.gson.annotations.SerializedName
import java.util.Date

class Food (
    @SerializedName("id")
    val id:String,
    @SerializedName("name")
    val name:String,
    @SerializedName("description")
    val description:String,
    @SerializedName("quantity")
    val quantity:Int,
    @SerializedName("allergen")
    val allergen:MutableList<String>,
    @SerializedName("expiryDate")
    val expiryDate:Date,
    @SerializedName("idDonator")
    val idDonator:String,
    @SerializedName("idClient")
    val idClient:String,
)