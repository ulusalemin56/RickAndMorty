package com.example.rickandmorty.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.rickandmorty.data.model.local.FavoriteEntity

@Dao
interface FavoriteDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertCharacterToFavorites(character: FavoriteEntity)

    @Delete
    suspend fun deleteCharacterFromFavorites(character: FavoriteEntity)

    @Query("SELECT EXISTS(SELECT * FROM favorites WHERE id = :id)")
    suspend fun isFavorites(id: Int): Boolean

    @Query("SELECT * FROM favorites ORDER BY addDate DESC")
    suspend fun fetchCharactersFromFavorites(): List<FavoriteEntity>
}