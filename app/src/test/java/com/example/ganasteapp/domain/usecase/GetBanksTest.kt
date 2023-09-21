package com.example.ganasteapp.domain.usecase

import com.example.ganasteapp.data.repository.BanksRepository
import com.example.ganasteapp.domain.model.Bank
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class GetBanksTest {
    @RelaxedMockK
    private lateinit var banksRepository: BanksRepository

    private lateinit var getBanks: GetBanks

    private val listBank = listOf(
        Bank(
            name = "Paga Todo",
            description = "Banco Paga Todo es Para Todos",
            age = "10",
            url = "https://firebasestorage.googleapis.com/v0/b/stagingpagatodo-286214.appspot.com/o/Challenge%2Flogo-pagatodo.jpeg?alt=media&token=38b6ac4d-85ac-4288-bada-88eb5a0dec20"
        ),
        Bank(
            name = "BBVA Bancomer",
            description = "BBVA Bancomer Creando Oportunidades",
            age = "11",
            url = "https://firebasestorage.googleapis.com/v0/b/stagingpagatodo-286214.appspot.com/o/Challenge%2Flogo-pagatodo.jpeg?alt=media&token=38b6ac4d-85ac-4288-bada-88eb5a0dec20"
        ),
        Bank(
            name = "Citibanamex",
            description = "Citibanamex, lo mejor de México, lo mejor del mundo.",
            age = "7",
            url = "https://firebasestorage.googleapis.com/v0/b/stagingpagatodo-286214.appspot.com/o/Challenge%2Flogo-pagatodo.jpeg?alt=media&token=38b6ac4d-85ac-4288-bada-88eb5a0dec20"
        ),
    )

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        getBanks = GetBanks(banksRepository)
    }

    @Test
    fun `when the list banks has data`() = runTest {
        // Given
        coEvery { banksRepository.getAllBanksFromApi() } returns Result.success(listBank)

        //When
        val response = getBanks()

        // Then
        coVerify(exactly = 1) { banksRepository.clearBanks() }
        coVerify(exactly = 1) { banksRepository.insertBanks(any()) }
        coVerify(exactly = 0) { banksRepository.getAllBanksFromDatabase() }
        assertEquals(listBank, response.getOrThrow())
    }

    @Test
    fun `when the list quotes has not data but database has data`() = runTest {
        // Given
        coEvery { banksRepository.getAllBanksFromApi() } returns Result.success(emptyList())
        coEvery { banksRepository.getAllBanksFromDatabase() } returns listBank
        // When
        val response = getBanks()

        //Then
        coVerify(exactly = 0) { banksRepository.clearBanks() }
        coVerify(exactly = 0) { banksRepository.insertBanks(any()) }
        coVerify(exactly = 1) { banksRepository.getAllBanksFromDatabase() }
        assertEquals(listBank, response.getOrThrow())
    }
}
