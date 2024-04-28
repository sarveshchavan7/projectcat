package com.catcompany.projectcat.breed.pagingsource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.catcompany.projectcat.model.Breed

class CatBreedPagingSource(private val getCatBreedList: suspend (pageNumber: Int) -> List<Breed>) :
    PagingSource<Int, Breed>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Breed> {
        return try {
            val pageNumber = params.key ?: 1
            val data = getCatBreedList(pageNumber)
            val nextPageNumber = pageNumber + 1
            LoadResult.Page(
                data = data,
                prevKey = null,
                /*** Backend not sending total pages for this paging api ****/
                /** Since page size is 10 at any point if get less than 10 that's the last page **/
                nextKey = if (data.size < 10) null else nextPageNumber
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Breed>): Int? {
        // Try to find the page key of the closest page to anchorPosition
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}