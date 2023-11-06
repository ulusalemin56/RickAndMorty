package com.example.rickandmorty.domain.source

import androidx.paging.PagingData
import com.example.rickandmorty.data.model.character.Result
import kotlinx.coroutines.flow.Flow

interface DataSource {

    interface Remote {
        fun getAllCharacters(query: String?, status: String?): Flow<PagingData<Result>>
    }
}