package com.example.ganasteapp.domain.model

import com.example.ganasteapp.data.database.entities.BankEntity
import com.example.ganasteapp.data.model.BankModel

data class Bank(
    val name: String,
    val description: String,
    val age: String,
    val url: String
)

fun BankModel.toDomain() = Bank(
    name = bankName,
    description = description,
    age = age,
    url = url
)

fun BankEntity.toDomain() = Bank(
    name = name,
    description = description,
    age = age,
    url = url
)
