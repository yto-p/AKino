package com.mtuci.akino.main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.paging.compose.collectAsLazyPagingItems

@Composable
fun MainScreen(navController: NavController){
    val viewModel = viewModel{
        MainViewModel()
    }
    LaunchedEffect(Unit){
        for (action in viewModel.actions){
            when(action){
                is MainViewModel.Action.RouteDetails -> navController.navigate("details/${action.id}")
            }
        }
    }
    val pagingData = viewModel.movies.collectAsLazyPagingItems()
    MainContent(
        pagingData = pagingData,
        openDetails = { id ->
            viewModel.onMovieClick(id) },
        onFilterClick = {},
        onSearchClick = {}
    )
}