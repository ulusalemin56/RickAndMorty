package com.example.rickandmorty.di

import android.content.Context
import androidx.room.Room
import com.example.rickandmorty.util.database.RickAndMortyDB
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule {
    @Singleton
    @Provides
    fun provideRickAndMortyDB(@ApplicationContext context: Context): RickAndMortyDB {
        return Room.databaseBuilder(
            context.applicationContext,
            RickAndMortyDB::class.java,
            "rickandmorty_data_base"
        ).build()
    }
}