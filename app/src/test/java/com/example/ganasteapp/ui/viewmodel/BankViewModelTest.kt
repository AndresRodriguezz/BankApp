package com.example.ganasteapp.ui.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.ganasteapp.MainCoroutineRule
import com.example.ganasteapp.domain.model.Bank
import com.example.ganasteapp.domain.usecase.GetBanks
import com.example.ganasteapp.domain.usecase.GetBanksFromDatabase
import com.example.ganasteapp.getOrAwaitValue
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class BankViewModelTest {

    @RelaxedMockK
    private lateinit var getBanks: GetBanks
    @RelaxedMockK
    private lateinit var getBanksFromDatabase: GetBanksFromDatabase

    private lateinit var viewModel: BankViewModel


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

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @OptIn(ExperimentalCoroutinesApi::class)
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule(UnconfinedTestDispatcher())

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
    }


    @Test
    fun `when viewModel is created at the first time, get all banks and set the value`() = runTest {
        // Given
        coEvery { getBanks() } returns Result.success(listBank)

        // When
        viewModel = BankViewModel(getBanks, getBanksFromDatabase)

        // Then
        val value = viewModel.banks.value
        MatcherAssert.assertThat(value, `is`(listBank))
    }

    @Test
    fun `when viewModel is created at the first time but banks are empty `() = runTest {
        // Given
        coEvery { getBanks() } returns Result.failure(Throwable())

        // When
        viewModel = BankViewModel(getBanks, getBanksFromDatabase)

        // Then
        val value = viewModel.banks.getOrAwaitValue()
        assert(value == null)
    }

    @Test
    fun `when viewModel get banks from database and the value is not empty`() = runTest {
        // Given
        coEvery { getBanksFromDatabase() } returns listBank

        // When
        viewModel = BankViewModel(getBanks, getBanksFromDatabase)
        viewModel.getAllBanksFromDatabase()

        // Then
        val value = viewModel.banks.value

        MatcherAssert.assertThat(value, `is`(listBank))
    }

    @Test
    fun `when viewModel get banks from database and the value is empty`() = runTest {
        // Given
        coEvery { getBanksFromDatabase() } returns emptyList()
        coEvery { getBanks() } returns Result.failure(Throwable())

        // When
        viewModel = BankViewModel(getBanks, getBanksFromDatabase)
        viewModel.getAllBanksFromDatabase()

        // Then
        val value = viewModel.banks.value
        assert(value == null)
    }
}