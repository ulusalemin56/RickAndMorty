package com.example.rickandmorty.domain.mapper

import com.example.rickandmorty.data.model.remote.character.Result
import com.example.rickandmorty.domain.model.CharacterItemUI

fun Result.toCaharacterItemUI() = CharacterItemUI(
    id = id,
    name = name,
    status = status,
    species = species,
    type = type,
    gender = gender,
    image = image,
    originName = origin?.name,
    locationName = location?.name
)
