package com.mtuci.akino.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import com.mtuci.akino.api.ApiClient
import com.mtuci.akino.details.data.MovieDetails
import com.mtuci.akino.details.data.Poster
import com.mtuci.akino.details.data.PostersResponse
import com.mtuci.akino.details.data.Review
import com.mtuci.akino.main.data.Movie
import com.mtuci.akino.paging.MoviesPagingSource
import com.mtuci.akino.paging.ReviewsPagingSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class DetailsViewModel(id: Int): ViewModel() {
    val isLoading = MutableStateFlow(false)

    val movieDetails = MutableStateFlow<MovieDetails?>(null)
    val moviePosters = MutableStateFlow<List<Poster>?>(null)

    val reviews: Flow<PagingData<Review>> = Pager(PagingConfig(pageSize = 10)){
        ReviewsPagingSource(id)
    }.flow

    init {
        getMovieDetails(id)
        getMoviePosters(id)
    }


    private fun getMovieDetails(id: Int) {
        viewModelScope.launch {
            try {
                isLoading.value = true
                val details = ApiClient.apiService.getMovieDetails(id)
                isLoading.value = false
                movieDetails.value = details
            } catch (e: HttpException) {
                e.printStackTrace()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

    private fun getMoviePosters(id: Int){
        viewModelScope.launch {
            try {
                val posters = ApiClient.apiService.getMoviePosters(
                    page = 1,
                    limit = 20,
                    movieId = id
                )
                moviePosters.value = posters.docs
            } catch (e: HttpException) {
                e.printStackTrace()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }
}