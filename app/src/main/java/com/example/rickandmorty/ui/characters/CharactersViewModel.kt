package com.example.rickandmorty.ui.characters

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmorty.data.model.character.CharacterResponse
import com.example.rickandmorty.data.repository.home.CharactersRepository
import com.example.rickandmorty.util.constants.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val charactersRepository: CharactersRepository
): ViewModel() {
    private val _allCharacters = MutableStateFlow<Resource<CharacterResponse>>(Resource.Loading)
    val allCharacters : StateFlow<Resource<CharacterResponse>>
        get() = _allCharacters

    init {
        getAllCharacters()
    }

    private fun getAllCharacters() = viewModelScope.launch {
        charactersRepository.getAllCharacters().collect {
           _allCharacters.emit(it)
        }
    }
}