package com.example.rickandmorty.domain.use_case.characters

import com.example.rickandmorty.domain.model.CharacterItemUI
import com.example.rickandmorty.domain.repository.RickAndMortyRepository
import javax.inject.Inject

class DeleteCharacterFromFavoritesUseCase@Inject constructor(
    private val rickAndMortyRepository: RickAndMortyRepository
) {
    suspend operator fun invoke(character : CharacterItemUI) {
        rickAndMortyRepository.deleteCharacterFromFavorites(character)
    }
}