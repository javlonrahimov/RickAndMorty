package com.javlonrahimov1212.rickandmorty.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.javlonrahimov1212.rickandmorty.models.Character

@Dao
interface AppDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCharacters(results: List<Character>)

    @Query("DELETE FROM character")
    suspend fun deleteAll()

    @Query("SELECT * FROM character")
    fun getAllCharacters(): LiveData<List<Character>>

}