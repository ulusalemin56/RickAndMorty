package com.example.rickandmorty.data.repository.home

import com.example.rickandmorty.data.model.character.CharacterResponse
import com.example.rickandmorty.util.constants.Resource
import kotlinx.coroutines.flow.Flow

interface HomeDataSource {
    fun getAllCharacters() : Flow<Resource<CharacterResponse>>
}