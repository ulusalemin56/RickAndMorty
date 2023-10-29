package com.example.rickandmorty.domain.repository

import androidx.paging.PagingData
import com.example.rickandmorty.domain.model.CharacterItemUI
import kotlinx.coroutines.flow.Flow

interface Repository {
    suspend fun getAllCharacters(): Flow<PagingData<CharacterItemUI>>
}