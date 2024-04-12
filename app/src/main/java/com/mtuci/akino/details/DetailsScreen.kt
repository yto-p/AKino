package com.mtuci.akino.details

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import kotlinx.coroutines.flow.collect

@Composable
fun DetailsScreen(navController: NavController, id: Int?){
    val viewModel = viewModel{
        DetailsViewModel(id)
    }
    val movieDetails by viewModel.movieDetails.collectAsState()
    movieDetails?.let {
        DetailsContent(
        movieDetails = it,
        onBackClick = navController::navigateUp
    )
    }
}