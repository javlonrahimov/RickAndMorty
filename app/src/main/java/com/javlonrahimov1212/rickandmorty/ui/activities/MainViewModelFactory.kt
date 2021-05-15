package com.javlonrahimov1212.rickandmorty.ui.activities

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.javlonrahimov1212.rickandmorty.database.AppDao
import com.javlonrahimov1212.rickandmorty.network.ApiHelper
import com.javlonrahimov1212.rickandmorty.repository.MainRepository

class MainViewModelFactory(private val apiHelper: ApiHelper, private val appDao: AppDao) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainActivityViewModel::class.java)) {
            return MainActivityViewModel(MainRepository(apiHelper, appDao)) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}