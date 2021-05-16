package com.javlonrahimov1212.rickandmorty.di

import com.javlonrahimov1212.rickandmorty.database.AppDatabase
import com.javlonrahimov1212.rickandmorty.network.ApiHelper
import com.javlonrahimov1212.rickandmorty.network.RetrofitBuilder
import com.javlonrahimov1212.rickandmorty.repository.MainRepository
import com.javlonrahimov1212.rickandmorty.ui.activities.MainActivityViewModel
import com.javlonrahimov1212.rickandmorty.ui.characters.CharactersViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    single { ApiHelper(RetrofitBuilder.apiService) }

    single { AppDatabase.getDatabase(androidContext()).appDao() }

    single { MainRepository(get(), get()) }

    viewModel { CharactersViewModel(get()) }

    viewModel { MainActivityViewModel(get()) }

}