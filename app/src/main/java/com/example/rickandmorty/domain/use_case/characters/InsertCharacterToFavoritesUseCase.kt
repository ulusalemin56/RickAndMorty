package com.example.rickandmorty.domain.use_case.characters

import com.example.rickandmorty.domain.model.CharacterItemUI
import com.example.rickandmorty.domain.repository.Repository
import javax.inject.Inject

class InsertCharacterToFavoritesUseCase @Inject constructor(
    private val repository: Repository
) {
    suspend operator fun invoke(character: CharacterItemUI) =
        repository.insertCharacterToFavorites(character)
}