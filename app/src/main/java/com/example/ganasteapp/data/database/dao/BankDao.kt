package com.example.ganasteapp.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.ganasteapp.data.database.entities.BankEntity

@Dao
interface BankDao {
    @Query("SELECT * FROM bank_table ORDER BY name ASC")
    suspend fun getAllBanks(): List<BankEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(bankEntity: List<BankEntity>)

    @Query("DELETE FROM bank_table")
    suspend fun deleteAllBanks()
}
