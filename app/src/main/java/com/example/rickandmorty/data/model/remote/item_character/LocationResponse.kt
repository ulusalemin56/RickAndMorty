package com.example.rickandmorty.data.model.remote.item_character


import com.google.gson.annotations.SerializedName

data class LocationResponse(
    @SerializedName("name")
    val name: String?,
    @SerializedName("url")
    val url: String?
)