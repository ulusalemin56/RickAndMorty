package com.example.rickandmorty.data.model.remote.item_character


import com.google.gson.annotations.SerializedName

data class OriginResponse(
    @SerializedName("name")
    val name: String?,
    @SerializedName("url")
    val url: String?
)