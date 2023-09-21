package com.example.ganasteapp.ui.viewmodel

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ganasteapp.domain.model.Bank
import com.example.ganasteapp.domain.usecase.GetBanks
import com.example.ganasteapp.domain.usecase.GetBanksFromDatabase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BankViewModel @Inject constructor(
    private val getBanks: GetBanks,
    private val getBanksFromDatabase: GetBanksFromDatabase
) : ViewModel() {
    private var _banks = MutableLiveData<List<Bank>>()
    val banks: LiveData<List<Bank>> = _banks
    private var _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean> = _isLoading

    init {
        getAllBanks()
    }

    @VisibleForTesting
    internal fun getAllBanks() {
        viewModelScope.launch {
            _isLoading.postValue(true)
            val result = getBanks()
            if (result.isSuccess)
                _banks.postValue(result.getOrNull())
            _isLoading.postValue(false)
        }
    }

    @VisibleForTesting
    internal fun getAllBanksFromDatabase() {
        viewModelScope.launch {
            val result = getBanksFromDatabase()
            if (result.isNotEmpty()) {
                _banks.postValue(result)
            }
        }
    }
}