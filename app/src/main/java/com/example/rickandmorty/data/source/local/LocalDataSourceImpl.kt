package com.example.rickandmorty.data.source.local

import com.example.rickandmorty.data.model.local.FavoriteEntity
import com.example.rickandmorty.domain.source.DataSource
import com.example.rickandmorty.util.database.FavoriteDao
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(
    private val favoriteDao: FavoriteDao
) : DataSource.Local {
    override suspend fun insertCharacterToFavorites(character: FavoriteEntity) {
        favoriteDao.insertCharacterToFavorites(character)
    }
    override suspend fun deleteCharacterFromFavorites(character: FavoriteEntity) {
        favoriteDao.deleteCharacterFromFavorites(character)
    }
    override suspend fun isFavorites(id: Int) = favoriteDao.isFavorites(id)
}