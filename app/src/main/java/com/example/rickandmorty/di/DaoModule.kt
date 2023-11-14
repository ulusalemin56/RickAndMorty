package com.example.rickandmorty.di

import com.example.rickandmorty.util.database.RickAndMortyDB
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object DaoModule {
    @Provides
    fun provideFavoriteDao(rickAndMortyDB: RickAndMortyDB) = rickAndMortyDB.favoriteDao()
}