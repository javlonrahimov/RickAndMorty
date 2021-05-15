package com.javlonrahimov1212.rickandmorty.repository

import com.javlonrahimov1212.rickandmorty.database.AppDao
import com.javlonrahimov1212.rickandmorty.models.Character
import com.javlonrahimov1212.rickandmorty.network.ApiHelper
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class MainRepository(private val apiHelper: ApiHelper, private val appDao: AppDao) {

    fun getCharacters() = appDao.getAllCharacters()

    suspend fun fetchCharacters(page: Int) {
        val charactersApi = apiHelper.getCharacters(page).results
        val characters = ArrayList<Character>()
        charactersApi.forEach {
            characters.add(
                Character(
                    gender = it.gender,
                    image = it.image,
                    name = it.name,
                    location = it.location.name,
                    status = it.status,
                    species = it.species,
                    id = it.id
                )
            )
        }
        appDao.insertCharacters(characters)
    }

    suspend fun deleteCharacters() {
        appDao.deleteAll()
        try {
            fetchCharacters(0)
        } catch (unknownHostException: UnknownHostException) {
        } catch (socketTimeOutException: SocketTimeoutException) {
        } catch (e: Exception) {
        }
    }

}