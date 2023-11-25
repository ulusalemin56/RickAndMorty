package com.example.rickandmorty.data.model.remote.character


import com.google.gson.annotations.SerializedName

data class CharacterResponse(
    @SerializedName("info")
    val infoResponse: InfoResponse?,
    @SerializedName("results")
    val resultResponses: List<ResultResponse>
)