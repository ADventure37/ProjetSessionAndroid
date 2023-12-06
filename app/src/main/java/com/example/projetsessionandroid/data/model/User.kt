package com.example.projetsessionandroid.data.model
import com.google.gson.annotations.SerializedName

class User (
    @SerializedName("_id")
    val _id:String,
    @SerializedName("username")
    val username:String,
    @SerializedName("lastname")
    val lastname:String,
    @SerializedName("email")
    val email:String,
    @SerializedName("password")
    val password:String,
    @SerializedName("location")
    val location:String,
    @SerializedName("postcode")
    val postcode:String,
    @SerializedName("city")
    val city:String,
    @SerializedName("solde")
    val solde:Int,
)