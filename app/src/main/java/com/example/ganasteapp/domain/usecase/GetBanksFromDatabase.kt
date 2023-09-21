package com.example.ganasteapp.domain.usecase

import com.example.ganasteapp.data.repository.BanksRepository
import com.example.ganasteapp.domain.model.Bank
import javax.inject.Inject

class GetBanksFromDatabase @Inject constructor(private val repository: BanksRepository) {
    suspend operator fun invoke(): List<Bank> {
        return repository.getAllBanksFromDatabase()
    }
}