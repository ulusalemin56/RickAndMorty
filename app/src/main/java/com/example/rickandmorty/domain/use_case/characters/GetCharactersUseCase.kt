package com.example.rickandmorty.domain.use_case.characters

import com.example.rickandmorty.domain.repository.RickAndMortyRepository
import javax.inject.Inject

class GetCharactersUseCase @Inject constructor(
    private val rickAndMortyRepository: RickAndMortyRepository
) {
    suspend operator fun invoke(query: String?, status: String?) =
        rickAndMortyRepository.getCharacters(query, status)
}