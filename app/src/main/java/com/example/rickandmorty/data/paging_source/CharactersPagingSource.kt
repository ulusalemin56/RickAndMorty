package com.example.rickandmorty.data.paging_source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.rickandmorty.data.model.remote.character.ResultResponse
import com.example.rickandmorty.util.constants.Constant.STARTING_PAGE_INDEX
import com.example.rickandmorty.util.network.RickAndMortyService

class CharactersPagingSource(
    private val rickAndMortyService: RickAndMortyService,
    private val query: String?,
    private val status: String?
) : PagingSource<Int, ResultResponse>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ResultResponse> {
        return try {
            val page = params.key ?: STARTING_PAGE_INDEX
            val response = rickAndMortyService.getCharacter(page = page, query, status).resultResponses
            LoadResult.Page(
                data = response,
                prevKey = if (page == STARTING_PAGE_INDEX) null else page.minus(1),
                nextKey = if (response.isEmpty()) null else page.plus(1)
            )
        } catch (e : Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, ResultResponse>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}