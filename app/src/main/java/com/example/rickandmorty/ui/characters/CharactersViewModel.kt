package com.example.rickandmorty.ui.characters

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.rickandmorty.data.model.character.CharacterResponse
import com.example.rickandmorty.data.repository.home.HomeRepository
import com.example.rickandmorty.ui.base.BaseViewModel
import com.example.rickandmorty.util.constants.ResourceStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val homeRepository: HomeRepository
): BaseViewModel() {
    val allCharactersLiveData = MutableLiveData<CharacterResponse>()

    init {
        getAllCharacters()
    }

    private fun getAllCharacters() = viewModelScope.launch {
        homeRepository.getAllCharacters()
            .asLiveData(viewModelScope.coroutineContext).observeForever {
                when (it.status) {
                    ResourceStatus.LOADING -> {
                        loading.postValue(true)
                    }

                    ResourceStatus.SUCCESS -> {
                        allCharactersLiveData.postValue(it.data!!)
                        loading.postValue(false)
                    }

                    ResourceStatus.ERROR -> {
                        error.postValue(it.throwable)
                        loading.postValue(false)
                    }
                }
            }
    }
}