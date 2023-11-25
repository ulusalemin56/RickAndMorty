package com.example.rickandmorty.domain.source

import androidx.paging.PagingData
import com.example.rickandmorty.data.model.local.FavoriteEntity
import com.example.rickandmorty.data.model.remote.character.ResultResponse
import kotlinx.coroutines.flow.Flow

interface RickAndMortyDataSource {
    interface Remote {
        fun getCharacters(query: String?, status: String?): Flow<PagingData<ResultResponse>>
    }

    interface Local {
        suspend fun insertCharacterToFavorites(character: FavoriteEntity)
        suspend fun deleteCharacterFromFavorites(character: FavoriteEntity)
        suspend fun isFavorites(id : Int) : Boolean
    }
}