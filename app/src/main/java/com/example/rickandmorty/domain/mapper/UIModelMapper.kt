package com.example.rickandmorty.domain.mapper

import com.example.rickandmorty.data.model.local.FavoriteEntity
import com.example.rickandmorty.data.model.remote.character.ResultResponse
import com.example.rickandmorty.domain.model.CharacterItemUI

fun ResultResponse.toCaharacterItemUI(isFavorites : Boolean) = CharacterItemUI(
    id = id,
    name = name.orEmpty(),
    status = status.orEmpty(),
    species = species.orEmpty(),
    type = type.orEmpty(),
    gender = gender.orEmpty(),
    image = image.orEmpty(),
    originName = originResponse?.name.orEmpty(),
    locationName = locationResponse?.name.orEmpty(),
    isFavorites = isFavorites
)

fun CharacterItemUI.toFavoriteEntity() = FavoriteEntity(
    id = id,
    name = name,
    status = status,
    species = species,
    type = type,
    gender = gender,
    image = image,
    originName = originName,
    locationName = locationName
)
