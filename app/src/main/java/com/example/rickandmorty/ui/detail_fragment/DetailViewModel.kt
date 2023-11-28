package com.example.rickandmorty.ui.detail_fragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmorty.domain.model.CharacterItemUI
import com.example.rickandmorty.domain.use_case.detail.GetItemCharacterUseCase
import com.example.rickandmorty.util.constants.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getItemCharacterUseCase: GetItemCharacterUseCase
) : ViewModel() {

    private val _itemCharacter = MutableStateFlow<Resource<CharacterItemUI>>(Resource.Loading)
    val itemCharacter: StateFlow<Resource<CharacterItemUI>>
        get() = _itemCharacter

    fun getItemCharacter(id: Int) = viewModelScope.launch {
        getItemCharacterUseCase(id).collect {
            _itemCharacter.emit(it)
        }
    }
}