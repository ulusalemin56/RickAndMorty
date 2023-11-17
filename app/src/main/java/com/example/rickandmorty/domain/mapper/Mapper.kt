package com.example.rickandmorty.domain.mapper

import com.example.rickandmorty.data.model.local.FavoriteEntity
import com.example.rickandmorty.data.model.remote.character.Result
import com.example.rickandmorty.domain.model.CharacterItemUI

fun Result.toCaharacterItemUI(isFavorites : Boolean) = CharacterItemUI(
    id = id,
    name = name,
    status = status,
    species = species,
    type = type,
    gender = gender,
    image = image,
    originName = origin?.name,
    locationName = location?.name,
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
