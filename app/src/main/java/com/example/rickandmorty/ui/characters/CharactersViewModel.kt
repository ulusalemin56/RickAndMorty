package com.example.rickandmorty.ui.characters

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.rickandmorty.domain.model.CharacterItemUI
import com.example.rickandmorty.domain.use_case.characters.GetAllCharactersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(
    val getAllCharactersUseCase: GetAllCharactersUseCase
): ViewModel() {
    private val _allCharacters = MutableStateFlow<PagingData<CharacterItemUI>>(PagingData.empty())
    val allCharacters : StateFlow<PagingData<CharacterItemUI>>
        get() = _allCharacters
    init {
        getAllCharacters()
    }
    private fun getAllCharacters() = viewModelScope.launch {
        getAllCharactersUseCase().cachedIn(viewModelScope).collect {
           _allCharacters.emit(it)
        }
    }
}