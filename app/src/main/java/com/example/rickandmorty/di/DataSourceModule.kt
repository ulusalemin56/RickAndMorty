package com.example.rickandmorty.di

import com.example.rickandmorty.data.source.local.LocalDataSourceImpl
import com.example.rickandmorty.domain.source.DataSource
import com.example.rickandmorty.data.source.remote.RemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {
    @Binds
    @Singleton
    abstract fun provideCharactersRemoteDataSource(
        remoteDataSourceImpl: RemoteDataSourceImpl
    ) : DataSource.Remote

    @Binds
    @Singleton
    abstract fun provideLocalDataSource(
        localDataSourceImpl: LocalDataSourceImpl
    ) : DataSource.Local
}