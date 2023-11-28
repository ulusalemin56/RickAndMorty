package com.example.rickandmorty.domain.use_case.detail

import com.example.rickandmorty.domain.repository.RickAndMortyRepository
import javax.inject.Inject

class GetItemCharacterUseCase @Inject constructor(
    private val rickAndMortyRepository: RickAndMortyRepository
) {
    operator fun invoke(id: Int) = rickAndMortyRepository.getItemCharacter(id)
}