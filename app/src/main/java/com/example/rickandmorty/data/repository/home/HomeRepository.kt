package com.example.rickandmorty.data.repository.home

import com.example.rickandmorty.data.model.character.CharacterResponse
import com.example.rickandmorty.util.constants.Resource
import kotlinx.coroutines.flow.Flow

class HomeRepository(
    private val homeDataSource: HomeDataSource
) {
    fun getAllCharacters(): Flow<Resource<CharacterResponse>> {
        return homeDataSource.getAllCharacters()
    }
}