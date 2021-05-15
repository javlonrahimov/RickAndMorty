package com.javlonrahimov1212.rickandmorty.network

import com.javlonrahimov1212.rickandmorty.models.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("character")
    suspend fun getCharacters(@Query("page") page: Int): Response
}