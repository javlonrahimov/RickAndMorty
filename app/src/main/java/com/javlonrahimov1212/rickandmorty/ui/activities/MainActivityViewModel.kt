package com.javlonrahimov1212.rickandmorty.ui.activities

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.javlonrahimov1212.rickandmorty.repository.MainRepository
import kotlinx.coroutines.launch
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class MainActivityViewModel(private val mainRepository: MainRepository) : ViewModel() {
    fun deleteAll() = viewModelScope.launch {
        mainRepository.deleteCharacters()
    }

    fun refresh() = viewModelScope.launch {
        try {
            mainRepository.fetchCharacters(page = 0)
        } catch (unknownHostException: UnknownHostException) {
        } catch (socketTimeOutException: SocketTimeoutException) {
        } catch (e: Exception) {
        }
    }
}