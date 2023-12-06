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
    val allergen:List<String>,
    @SerializedName("expiryDate")
    val expiryDate:String,
    @SerializedName("idDonator")
    val idDonator:String,
    @SerializedName("idClient")
    val idClient:String,
)