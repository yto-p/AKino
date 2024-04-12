package com.mtuci.akino.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.mtuci.akino.api.ApiClient
import com.mtuci.akino.details.data.Review
import retrofit2.HttpException
import java.io.IOException

class ReviewsPagingSource(val id: Int): PagingSource<Int, Review>() {
    override fun getRefreshKey(state: PagingState<Int, Review>): Int? {
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(
                1
            )?:state.closestPageToPosition(it)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Review> {
        val position = params.key ?: 1
        return try {
            val remoteData = ApiClient.apiService.getMovieReviews(position, params.loadSize, id)
            val nextKey = if (remoteData.docs.isEmpty()){
                null
            } else {
                position + 1
            }
            LoadResult.Page(
                data = remoteData.docs,
                prevKey = null,
                nextKey = nextKey
            )
        } catch (e: HttpException) {
            LoadResult.Error(e)
        } catch (e: IOException) {
            LoadResult.Error(e)
        }
    }
}