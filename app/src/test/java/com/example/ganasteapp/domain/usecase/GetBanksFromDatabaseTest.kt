package com.example.ganasteapp.domain.usecase

import com.example.ganasteapp.data.repository.BanksRepository
import com.example.ganasteapp.domain.model.Bank
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class GetBanksFromDatabaseTest(){
    @RelaxedMockK
    private lateinit var banksRepository: BanksRepository

    private lateinit var getBanksFromDatabase: GetBanksFromDatabase

    private val listBank = listOf(
        Bank("Paga Todo", "Banco Paga Todo es Para Todos", "10", "https://firebasestorage.googleapis.com/v0/b/stagingpagatodo-286214.appspot.com/o/Challenge%2Flogo-pagatodo.jpeg?alt=media&token=38b6ac4d-85ac-4288-bada-88eb5a0dec20"),
        Bank("BBVA Bancomer", "BBVA Bancomer Creando Oportunidades", "11", "https://firebasestorage.googleapis.com/v0/b/stagingpagatodo-286214.appspot.com/o/Challenge%2Flogo-pagatodo.jpeg?alt=media&token=38b6ac4d-85ac-4288-bada-88eb5a0dec20"),
        Bank("Citibanamex", "Citibanamex, lo mejor de México, lo mejor del mundo.", "7", "https://firebasestorage.googleapis.com/v0/b/stagingpagatodo-286214.appspot.com/o/Challenge%2Flogo-pagatodo.jpeg?alt=media&token=38b6ac4d-85ac-4288-bada-88eb5a0dec20"),
    )

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        getBanksFromDatabase = GetBanksFromDatabase(banksRepository)
    }

    @Test
    fun `when the list banks has data`()= runTest {
        // Given
        coEvery { banksRepository.getAllBanksFromDatabase() } returns listBank

        //When
        val response = getBanksFromDatabase()

        // Then
        coVerify(exactly = 1) { banksRepository.getAllBanksFromDatabase() }
        Assert.assertEquals(listBank, response)
    }
}
