package com.example.rickandmorty.di

import com.example.rickandmorty.data.repository.home.CharactersDataSource
import com.example.rickandmorty.data.repository.home.CharactersRemoteDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class DataSourceModule {
    @Binds
    abstract fun provideCharactersRemoteDataSource(
        homerRemoteDataSource: CharactersRemoteDataSource
    ) : CharactersDataSource
}