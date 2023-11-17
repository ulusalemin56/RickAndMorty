package com.example.rickandmorty.ui.characters

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.rickandmorty.domain.model.CharacterItemUI
import com.example.rickandmorty.domain.use_case.characters.DeleteCharacterFromFavoritesUseCase
import com.example.rickandmorty.domain.use_case.characters.GetAllCharactersUseCase
import com.example.rickandmorty.domain.use_case.characters.InsertCharacterToFavoritesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val getAllCharactersUseCase: GetAllCharactersUseCase,
    private val insertCharacterToFavoritesUseCase: InsertCharacterToFavoritesUseCase,
    private val deleteCharacterFromFavoritesUseCase: DeleteCharacterFromFavoritesUseCase
) : ViewModel() {
    private val _allCharacters = MutableStateFlow<PagingData<CharacterItemUI>>(PagingData.empty())
    val allCharacters: StateFlow<PagingData<CharacterItemUI>>
        get() = _allCharacters

    init {
        getAllCharacters()
    }

    fun getAllCharacters(query: String? = null, status: String? = null) = viewModelScope.launch {
        getAllCharactersUseCase(query, status).cachedIn(viewModelScope).collect {
            _allCharacters.emit(it)
        }
    }

    fun insertCharacterToFavorites(character: CharacterItemUI) = viewModelScope.launch {
        insertCharacterToFavoritesUseCase(character)
    }

    fun deleteCharacterFromFavorites(character: CharacterItemUI) = viewModelScope.launch {
        deleteCharacterFromFavoritesUseCase(character)
    }
}