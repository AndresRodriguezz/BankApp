package com.example.ganasteapp.data.model

import com.google.gson.annotations.SerializedName

data class BankModel(
    @SerializedName("bankName")
    val bankName: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("age")
    val age: String,
    @SerializedName("url")
    val url: String
)
