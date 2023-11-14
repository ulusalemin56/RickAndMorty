package com.example.rickandmorty.data.model.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Calendar

@Entity(tableName = "favorites")
data class FavoriteEntity(
    @PrimaryKey
    val id : Int,
    val name : String?,
    val status: String?,
    val species : String?,
    val type : String?,
    val gender : String?,
    val image : String?,
    val originName : String?,
    val locationName : String?,
    val addDate: String = Calendar.getInstance().time.toString()
)
