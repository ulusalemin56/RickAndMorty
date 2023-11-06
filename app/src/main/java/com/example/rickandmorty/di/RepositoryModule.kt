package com.example.rickandmorty.di

import com.example.rickandmorty.data.repository.RepositoryImpl
import com.example.rickandmorty.domain.repository.Repository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun provideHomeRepository(
        repositoryImpl: RepositoryImpl
    ) : Repository
}