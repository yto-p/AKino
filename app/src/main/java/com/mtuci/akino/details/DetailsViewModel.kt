package com.mtuci.akino.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mtuci.akino.api.ApiClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class DetailsViewModel(id: Int?): ViewModel() {
    val movieDetails = MutableStateFlow<MovieDetails?>(null)

    init {
        getMovieDetails(id)
    }

    private fun getMovieDetails(id: Int?) {
        viewModelScope.launch {
            val details = ApiClient.apiService.getMovieDetails(id!!)
            movieDetails.value = details
        }
    }
}