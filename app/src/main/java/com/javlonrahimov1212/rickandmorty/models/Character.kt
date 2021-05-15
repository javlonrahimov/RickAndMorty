package com.javlonrahimov1212.rickandmorty.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Character(
    @PrimaryKey
    val id: Int,
    @ColumnInfo
    val gender: String,
    @ColumnInfo
    val image: String,
    @ColumnInfo
    val location: String,
    @ColumnInfo
    val name: String,
    @ColumnInfo
    val species: String,
    @ColumnInfo
    val status: String,
)