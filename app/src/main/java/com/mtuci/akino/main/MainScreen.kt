package com.mtuci.akino.main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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
    val searchText by viewModel.searchText.collectAsState()
    val yearText by viewModel.yearText.collectAsState()
    val countryText by viewModel.countryText.collectAsState()
    val ageText by viewModel.ageText.collectAsState()
    val isSearching by viewModel.isSearching.collectAsState()
    val movieSearchList by viewModel.movieSearchList.collectAsState()

    MainContent(
        pagingData = pagingData,
        openDetails = { id ->
            viewModel.onMovieClick(id) },
        searchText = searchText,
        isSearching = isSearching,
        yearText = yearText,
        countryText = countryText,
        ageText = ageText,
        onYearTextChange = viewModel::onYearTextChange,
        onCountryTextChange = viewModel::onCountryTextChange,
        onAgeTextChange = viewModel::onAgeTextChange,
        movieSearchList = movieSearchList.docs,
        onSearchTextChange = viewModel::onSearchTextChange,
        onApplyClick = viewModel::onApplyClick,
        onSearchClick = viewModel::onSearchClick,
        onBackClick = viewModel::onBackClick
    )
}