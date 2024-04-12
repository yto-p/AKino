package com.mtuci.akino.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.mtuci.akino.main.data.Movie
import com.mtuci.akino.paging.MoviesPagingSource
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {
    val actions = Channel<Action>()

    val movies: Flow<PagingData<Movie>> = Pager(PagingConfig(pageSize = 20)){
        MoviesPagingSource()
    }.flow

    fun onMovieClick(id: Int){
        viewModelScope.launch {
            actions.send(Action.RouteDetails(id))
        }
    }

    sealed interface Action{
        data class RouteDetails(val id: Int) : Action
    }
}