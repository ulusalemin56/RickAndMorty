package com.example.rickandmorty.util.network

import com.example.rickandmorty.data.model.character.CharacterResponse
import com.example.rickandmorty.util.constants.Constant
import retrofit2.http.GET
import retrofit2.http.Query

interface RickAndMortyService {
    @GET(Constant.EndPoints.CHARACTERS)
    suspend fun getCharacter(@Query("page") page : Int = 1) : CharacterResponse
}