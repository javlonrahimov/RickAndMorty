package com.javlonrahimov1212.rickandmorty.models

data class Result(
    val created: String,
    val gender: String,
    val image: String,
    val location: Location,
    val name: String,
    val species: String,
    val status: String,
    val type: String,
    val url: String,
    val id: Int,
)