package com.example.rickandmorty.util.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.rickandmorty.data.model.local.FavoriteEntity

@Database(
    entities = [FavoriteEntity::class],
    version = 1,
    exportSchema = false
)
abstract class RickAndMortyDB : RoomDatabase(){
    abstract fun favoriteDao() : FavoriteDao
}