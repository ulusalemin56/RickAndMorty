package com.example.rickandmorty.di

import com.example.rickandmorty.data.database.FavoriteDao
import com.example.rickandmorty.data.database.RickAndMortyDB
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DaoModule {
    @Singleton
    @Provides
    fun provideFavoriteDao(rickAndMortyDB: RickAndMortyDB) : FavoriteDao {
        return rickAndMortyDB.favoriteDao()
    }
}