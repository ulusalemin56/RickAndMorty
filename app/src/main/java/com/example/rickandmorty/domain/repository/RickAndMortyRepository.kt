package com.example.rickandmorty.domain.repository

import androidx.paging.PagingData
import com.example.rickandmorty.domain.model.CharacterItemUI
import kotlinx.coroutines.flow.Flow

interface RickAndMortyRepository {
    suspend fun getCharacters(query: String?, status: String?): Flow<PagingData<CharacterItemUI>>
    suspend fun insertCharacterToFavorites(character: CharacterItemUI)
    suspend fun deleteCharacterFromFavorites(character: CharacterItemUI)
}