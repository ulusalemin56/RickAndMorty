package com.example.rickandmorty.domain.use_case.characters

import com.example.rickandmorty.domain.repository.Repository
import javax.inject.Inject

class GetAllCharactersUseCase @Inject constructor(private val repository: Repository) {
    suspend operator fun invoke(query: String?, status: String?) = repository.getAllCharacters(query, status)
}