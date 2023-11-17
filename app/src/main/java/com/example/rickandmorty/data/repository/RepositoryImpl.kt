package com.example.rickandmorty.data.repository

import androidx.paging.PagingData
import androidx.paging.map
import com.example.rickandmorty.data.model.local.FavoriteEntity
import com.example.rickandmorty.domain.mapper.toCaharacterItemUI
import com.example.rickandmorty.domain.mapper.toFavoriteEntity
import com.example.rickandmorty.domain.model.CharacterItemUI
import com.example.rickandmorty.domain.repository.Repository
import com.example.rickandmorty.domain.source.DataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val remoteDataSource: DataSource.Remote,
    private val localDataSource: DataSource.Local
) : Repository {
    override suspend fun getAllCharacters(
        query: String?,
        status: String?
    ): Flow<PagingData<CharacterItemUI>> {
        return remoteDataSource.getAllCharacters(query, status).map { pagingData ->
            pagingData.map {
                it.toCaharacterItemUI(localDataSource.isFavorites(it.id))
            }
        }
    }

    override suspend fun insertCharacterToFavorites(character: CharacterItemUI) {
        localDataSource.insertCharacterToFavorites(character.toFavoriteEntity())
    }

    override suspend fun deleteCharacterFromFavorites(character: CharacterItemUI) {
        localDataSource.deleteCharacterFromFavorites(character.toFavoriteEntity())
    }
}