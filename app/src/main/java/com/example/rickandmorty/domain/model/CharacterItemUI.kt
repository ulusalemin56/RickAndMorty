package com.example.rickandmorty.domain.model

data class CharacterItemUI(
    val id : Int,
    val name : String,
    val status: String,
    val species : String,
    val type : String,
    val gender : String,
    val image : String,
    val originName : String,
    val locationName : String,
    var isFavorites : Boolean = false
)
