package com.example.ganasteapp.data.network

import com.example.ganasteapp.data.model.BankModel
import javax.inject.Inject

class BankService @Inject constructor(private val apiClient: BankApiClient) {
    suspend fun getBanks(): List<BankModel>? {
        val response = apiClient.getAllBanks()
        return response.body()
    }
}
