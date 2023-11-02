package com.example.rickandmorty.data.source.remote

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.rickandmorty.data.model.character.Result
import com.example.rickandmorty.data.paging_source.CharactersPagingSource
import com.example.rickandmorty.domain.source.DataSource
import com.example.rickandmorty.util.constants.Constant.NETWORK_PAGE_SIZE
import com.example.rickandmorty.util.network.RickAndMortyService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
    private val rickAndMortyService: RickAndMortyService
) : DataSource.Remote {
    override fun getAllCharacters(query: String?, status: String?): Flow<PagingData<Result>> {
        return Pager(
            config = PagingConfig(pageSize = NETWORK_PAGE_SIZE),
            pagingSourceFactory = {
                CharactersPagingSource(rickAndMortyService, query, status)
            }
        ).flow
    }
}