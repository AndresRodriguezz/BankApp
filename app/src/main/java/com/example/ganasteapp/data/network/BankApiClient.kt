package com.example.ganasteapp.data.network

import com.example.ganasteapp.data.model.BankModel
import retrofit2.Response
import retrofit2.http.GET

interface BankApiClient {
    @GET("catom/api/challenge/banks")
    suspend fun getAllBanks(): Response<List<BankModel>>
}
