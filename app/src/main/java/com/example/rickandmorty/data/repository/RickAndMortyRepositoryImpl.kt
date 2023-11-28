package com.example.rickandmorty.data.repository

import androidx.paging.PagingData
import androidx.paging.map
import com.example.rickandmorty.domain.mapper.toCaharacterItemUI
import com.example.rickandmorty.domain.mapper.toCharacterItemUI
import com.example.rickandmorty.domain.mapper.toFavoriteEntity
import com.example.rickandmorty.domain.model.CharacterItemUI
import com.example.rickandmorty.domain.repository.RickAndMortyRepository
import com.example.rickandmorty.domain.source.RickAndMortyDataSource
import com.example.rickandmorty.util.constants.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RickAndMortyRepositoryImpl @Inject constructor(
    private val remoteRickAndMortyDataSource: RickAndMortyDataSource.Remote,
    private val localRickAndMortyDataSource: RickAndMortyDataSource.Local
) : RickAndMortyRepository {
    override suspend fun getCharacters(
        query: String?,
        status: String?
    ): Flow<PagingData<CharacterItemUI>> {
        return remoteRickAndMortyDataSource.getCharacters(query, status).map { pagingData ->
            pagingData.map {
                it.toCaharacterItemUI(localRickAndMortyDataSource.isFavorites(it.id))
            }
        }
    }

    override suspend fun insertCharacterToFavorites(character: CharacterItemUI) {
        localRickAndMortyDataSource.insertCharacterToFavorites(character.toFavoriteEntity())
    }

    override suspend fun deleteCharacterFromFavorites(character: CharacterItemUI) {
        localRickAndMortyDataSource.deleteCharacterFromFavorites(character.toFavoriteEntity())
    }

    override fun getItemCharacter(id: Int): Flow<Resource<CharacterItemUI>> = flow {
        try {
            emit(Resource.Loading)
            val data = remoteRickAndMortyDataSource.getItemCharacter(id).toCharacterItemUI()
            emit(Resource.Success(data))
        } catch (e : Exception) {
            emit(Resource.Error(e))
        }
    }
}