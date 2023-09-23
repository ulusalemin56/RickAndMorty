package com.example.rickandmorty.util.network

import com.example.rickandmorty.data.model.character.CharacterResponse
import retrofit2.http.GET

interface RickAndMortyService {
    @GET("character")
    suspend fun getCharacter() : CharacterResponse
}