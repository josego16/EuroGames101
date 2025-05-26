package com.eurogames.data.remote.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.eurogames.data.remote.apiservice.CountryApiService
import com.eurogames.data.remote.response.CountryResponseDto

class CountryPagingSource(
    private val apiService: CountryApiService
) : PagingSource<Int, CountryResponseDto>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CountryResponseDto> {
        val page = params.key ?: 1
        return try {
            val response = apiService.getAllCountries()
            val itemsPerPage = 20
            val paginatedItems = response.drop((page - 1) * itemsPerPage).take(itemsPerPage)

            LoadResult.Page(
                data = paginatedItems,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (paginatedItems.size < itemsPerPage) null else page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, CountryResponseDto>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}