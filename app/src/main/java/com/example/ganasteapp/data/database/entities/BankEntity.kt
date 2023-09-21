package com.example.ganasteapp.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.ganasteapp.domain.model.Bank

@Entity(tableName = "bank_table")
data class BankEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int = 0,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "description")
    val description: String,
    @ColumnInfo(name = "age")
    val age: String,
    @ColumnInfo(name = "url")
    val url: String
)

fun Bank.toDatabase() = BankEntity(
    name = name,
    description = description,
    age = age,
    url = url
)