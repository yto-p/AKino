package com.mtuci.akino.details

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.paging.compose.collectAsLazyPagingItems
import kotlinx.coroutines.flow.collect

@Composable
fun DetailsScreen(navController: NavController, id: Int) {
    val viewModel = viewModel {
        DetailsViewModel(id)
    }
    val isLoading by viewModel.isLoading.collectAsState()

    val movieDetails by viewModel.movieDetails.collectAsState()
    val posters by viewModel.moviePosters.collectAsState()
    val pagingData = viewModel.reviews.collectAsLazyPagingItems()

    DetailsContent(
        isLoading = isLoading,
        movieDetails = movieDetails,
        posters = posters,
        pagingData = pagingData,
        onBackClick = navController::navigateUp,
    )
}