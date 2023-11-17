package com.example.rickandmorty.domain.repository

import androidx.paging.PagingData
import com.example.rickandmorty.data.model.local.FavoriteEntity
import com.example.rickandmorty.domain.model.CharacterItemUI
import kotlinx.coroutines.flow.Flow

interface Repository {
    suspend fun getAllCharacters(query: String?, status: String?): Flow<PagingData<CharacterItemUI>>
    suspend fun insertCharacterToFavorites(character: CharacterItemUI)
    suspend fun deleteCharacterFromFavorites(character: CharacterItemUI)
}