package com.example.rickandmorty.data.source.remote

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.rickandmorty.data.model.remote.character.ResultResponse
import com.example.rickandmorty.data.paging_source.CharactersPagingSource
import com.example.rickandmorty.domain.source.RickAndMortyDataSource
import com.example.rickandmorty.util.constants.Constant.NETWORK_PAGE_SIZE
import com.example.rickandmorty.util.network.RickAndMortyService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
    private val rickAndMortyService: RickAndMortyService
) : RickAndMortyDataSource.Remote {
    override fun getCharacters(query: String?, status: String?): Flow<PagingData<ResultResponse>> {
        return Pager(
            config = PagingConfig(pageSize = NETWORK_PAGE_SIZE),
            pagingSourceFactory = {
                CharactersPagingSource(rickAndMortyService, query, status)
            }
        ).flow
    }

    override suspend fun getItemCharacter(id: Int) = rickAndMortyService.getItemCharacter(id)
}