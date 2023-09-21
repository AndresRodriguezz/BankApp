package com.example.ganasteapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.ganasteapp.data.database.dao.BankDao
import com.example.ganasteapp.data.database.entities.BankEntity

@Database(entities = [BankEntity::class], version = 1)
abstract class BankDataBase: RoomDatabase() {

    abstract fun getBankDao(): BankDao
}