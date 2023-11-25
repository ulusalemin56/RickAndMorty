package com.example.rickandmorty.domain.use_case.characters

import com.example.rickandmorty.domain.model.CharacterItemUI
import com.example.rickandmorty.domain.repository.RickAndMortyRepository
import javax.inject.Inject

class InsertCharacterToFavoritesUseCase @Inject constructor(
    private val rickAndMortyRepository: RickAndMortyRepository
) {
    suspend operator fun invoke(character: CharacterItemUI) =
        rickAndMortyRepository.insertCharacterToFavorites(character)
}