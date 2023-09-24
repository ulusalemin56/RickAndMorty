package com.example.rickandmorty.data.repository.home

import com.example.rickandmorty.data.model.character.CharacterResponse
import com.example.rickandmorty.util.constants.Resource
import com.example.rickandmorty.util.network.RickAndMortyService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CharactersRemoteDataSource @Inject constructor(
    private val rickAndMortyService: RickAndMortyService
) : CharactersDataSource {
    override fun getAllCharacters(): Flow<Resource<CharacterResponse>> = flow {
        try {
            emit(Resource.Loading())
            val data = rickAndMortyService.getCharacter()
            emit(Resource.Success(data = data))
        } catch (e : Exception) {
            emit(Resource.Error(e))
            e.printStackTrace()
        }
    }
}