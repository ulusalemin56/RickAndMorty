package com.example.rickandmorty.di

import com.example.rickandmorty.data.repository.home.CharactersDataSource
import com.example.rickandmorty.data.repository.home.CharactersRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {
    @Provides
    fun provideHomeRepository(
        dataSource: CharactersDataSource
    ) : CharactersRepository {
        return CharactersRepository(dataSource)
    }
}