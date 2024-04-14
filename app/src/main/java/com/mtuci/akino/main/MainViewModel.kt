package com.mtuci.akino.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.mtuci.akino.api.ApiClient
import com.mtuci.akino.main.data.Movie
import com.mtuci.akino.main.data.MoviesResponse
import com.mtuci.akino.paging.MoviesPagingSource
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class MainViewModel: ViewModel() {
    val actions = Channel<Action>()

    val movies: Flow<PagingData<Movie>> = Pager(PagingConfig(pageSize = 20)){
        MoviesPagingSource()
    }.flow

    val yearText = MutableStateFlow("")
    val countryText = MutableStateFlow("")
    val ageText = MutableStateFlow("")

    val searchText = MutableStateFlow("")
    val isSearching = MutableStateFlow(false)
    val movieSearchList = MutableStateFlow(MoviesResponse(listOf()))

    fun getSearchResult(query: String){
        viewModelScope.launch {
            try {
                val search = ApiClient.apiService.getMovieSearchList(1, 10, query)
                movieSearchList.value = search
            } catch (e: HttpException) {
                e.printStackTrace()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

    fun onSearchClick(){
        isSearching.value = true
    }

    fun onApplyClick(){
        isSearching.value = true
        val year =
            yearText.value.trim().ifEmpty {
                null
            }
        val age =
            ageText.value.trim().ifEmpty {
                null
            }
        val country =
            countryText.value.trim().ifEmpty {
                null
            }
        viewModelScope.launch {
            try {
                val result = ApiClient.apiService.getMovieFilterList(1, 40, year, age, country)
                movieSearchList.value = result
            } catch (e: HttpException) {
                e.printStackTrace()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
        yearText.value = ""
        countryText.value = ""
        ageText.value = ""
    }

    fun onBackClick(){
        isSearching.value = false
        searchText.value = ""
        onSearchTextChange(searchText.value)
    }

    fun onSearchTextChange(text: String){
        searchText.value = text
        getSearchResult(text)
    }

    fun onCountryTextChange(text: String){
        countryText.value = text
    }

    fun onAgeTextChange(text: String){
        ageText.value = text
    }

    fun onYearTextChange(text: String){
        yearText.value = text
    }

    fun onMovieClick(id: Int){
        viewModelScope.launch {
            actions.send(Action.RouteDetails(id))
        }
    }

    sealed interface Action{
        data class RouteDetails(val id: Int) : Action
    }
}