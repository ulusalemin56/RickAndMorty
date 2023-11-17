package com.example.rickandmorty.di

import com.example.rickandmorty.util.database.FavoriteDao
import com.example.rickandmorty.util.database.RickAndMortyDB
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
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