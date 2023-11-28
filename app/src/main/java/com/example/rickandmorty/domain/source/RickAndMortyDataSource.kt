package com.example.rickandmorty.domain.source

import androidx.paging.PagingData
import com.example.rickandmorty.data.model.local.FavoriteEntity
import com.example.rickandmorty.data.model.remote.character.ResultResponse
import com.example.rickandmorty.data.model.remote.item_character.ItemCharacterResponse
import kotlinx.coroutines.flow.Flow

interface RickAndMortyDataSource {
    interface Remote {
        fun getCharacters(query: String?, status: String?): Flow<PagingData<ResultResponse>>
        suspend fun getItemCharacter(id : Int) : ItemCharacterResponse
    }

    interface Local {
        suspend fun insertCharacterToFavorites(character: FavoriteEntity)
        suspend fun deleteCharacterFromFavorites(character: FavoriteEntity)
        suspend fun isFavorites(id : Int) : Boolean
    }
}