package com.example.ganasteapp.data.repository

import android.util.Log
import com.example.ganasteapp.data.database.dao.BankDao
import com.example.ganasteapp.data.database.entities.BankEntity
import com.example.ganasteapp.data.network.BankService
import com.example.ganasteapp.domain.model.Bank
import com.example.ganasteapp.domain.model.toDomain
import java.io.IOException
import javax.inject.Inject

class BanksRepository @Inject constructor(
    private val bankService: BankService,
    private val bankDao: BankDao
) {
    suspend fun getAllBanksFromApi(): Result<List<Bank>> {
        return try {
            val response = bankService.getBanks()
            val banks = response?.map { it.toDomain() }
            Result.success(banks ?: emptyList())
        } catch (e: IOException) {
            Log.i("NetworkError", "network-related", e)
            Result.failure(Throwable(e))
        } catch (e: Throwable) {
            Log.i("NetworkError", "other exceptions", e)
            Result.failure(Throwable(e))
        }
    }

    suspend fun getAllBanksFromDatabase(): List<Bank> {
        val response = bankDao.getAllBanks()
        return response.map { it.toDomain() }
    }

    suspend fun insertBanks(banks: List<BankEntity>) {
        bankDao.insertAll(banks)
    }

    suspend fun clearBanks() {
        bankDao.deleteAllBanks()
    }
}
