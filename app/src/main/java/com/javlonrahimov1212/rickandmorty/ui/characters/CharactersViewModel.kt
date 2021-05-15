package com.javlonrahimov1212.rickandmorty.ui.characters

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.javlonrahimov1212.rickandmorty.models.Character
import com.javlonrahimov1212.rickandmorty.repository.MainRepository
import kotlinx.coroutines.launch
import java.net.SocketTimeoutException
import java.net.UnknownHostException


class CharactersViewModel(private val mainRepository: MainRepository) :
    ViewModel() {

    var currentPage = 0
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    init {
        _isLoading.value = false
        viewModelScope.launch {
            _isLoading.value = true
            try {
                mainRepository.fetchCharacters(page = 1)
            } catch (unknownHostException: UnknownHostException) {
            } catch (socketTimeOutException: SocketTimeoutException) {
            } catch (e: Exception) {
            }
            _isLoading.value = false
        }
    }

    fun fetchNextPages(page: Int) = viewModelScope.launch {
        _isLoading.value = true
        try {
            mainRepository.fetchCharacters(page)
        } catch (unknownHostException: UnknownHostException) {
        } catch (socketTimeOutException: SocketTimeoutException) {
        } catch (e: Exception) {
        }
        _isLoading.value = false
    }

    val characters: LiveData<List<Character>> = mainRepository.getCharacters()

}