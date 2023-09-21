package com.example.ganasteapp.domain.usecase

import com.example.ganasteapp.data.database.entities.toDatabase
import com.example.ganasteapp.data.repository.BanksRepository
import com.example.ganasteapp.domain.model.Bank
import javax.inject.Inject

class GetBanks @Inject constructor(private val repository: BanksRepository) {

    suspend operator fun invoke(): Result<List<Bank>> {
        val banksFromApi = repository.getAllBanksFromApi()

        return if (banksFromApi.getOrThrow().isNotEmpty()) {
            repository.clearBanks()
            repository.insertBanks(banksFromApi.getOrThrow().map { it.toDatabase() })
            banksFromApi
        } else {
            val banksDataBase = repository.getAllBanksFromDatabase()
            Result.success(banksDataBase)
        }
    }
}
