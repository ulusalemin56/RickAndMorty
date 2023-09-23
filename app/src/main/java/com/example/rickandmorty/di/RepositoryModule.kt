package com.example.rickandmorty.di

import com.example.rickandmorty.data.repository.home.HomeDataSource
import com.example.rickandmorty.data.repository.home.HomeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {
    @Provides
    fun provideHomeRepository(
        dataSource: HomeDataSource
    ) : HomeRepository {
        return HomeRepository(dataSource)
    }
}