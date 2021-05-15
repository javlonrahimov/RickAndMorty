package com.javlonrahimov1212.rickandmorty.network

class ApiHelper(private val apiService: ApiService) {

    suspend fun getCharacters(page: Int) = apiService.getCharacters(page)

}