package com.example.rickandmorty.data.repository

import androidx.paging.PagingData
import androidx.paging.map
import com.example.rickandmorty.domain.mapper.toCaharacterItemUI
import com.example.rickandmorty.domain.model.CharacterItemUI
import com.example.rickandmorty.domain.repository.Repository
import com.example.rickandmorty.domain.source.DataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val remoteDataSource: DataSource.Remote
) : Repository {
    override suspend fun getAllCharacters(): Flow<PagingData<CharacterItemUI>> {
        return remoteDataSource.getAllCharacters().map { pagingData ->
            pagingData.map {
                it.toCaharacterItemUI()
            }
        }
    }
}