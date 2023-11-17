package com.example.rickandmorty.domain.source

import androidx.paging.PagingData
import com.example.rickandmorty.data.model.local.FavoriteEntity
import com.example.rickandmorty.data.model.remote.character.Result
import kotlinx.coroutines.flow.Flow

interface DataSource {
    interface Remote {
        fun getAllCharacters(query: String?, status: String?): Flow<PagingData<Result>>
    }

    interface Local {
        suspend fun insertCharacterToFavorites(character: FavoriteEntity)
        suspend fun deleteCharacterFromFavorites(character: FavoriteEntity)
        suspend fun isFavorites(id : Int) : Boolean
    }
}