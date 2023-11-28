package com.example.rickandmorty.data.model.remote.item_character


import com.google.gson.annotations.SerializedName

data class ItemCharacterResponse(
    @SerializedName("created")
    val created: String?,
    @SerializedName("episode")
    val episode: List<String?>?,
    @SerializedName("gender")
    val gender: String?,
    @SerializedName("id")
    val id: Int,
    @SerializedName("image")
    val image: String?,
    @SerializedName("location")
    val locationResponse: LocationResponse?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("origin")
    val originResponse: OriginResponse?,
    @SerializedName("species")
    val species: String?,
    @SerializedName("status")
    val status: String?,
    @SerializedName("type")
    val type: String?,
    @SerializedName("url")
    val url: String?
)