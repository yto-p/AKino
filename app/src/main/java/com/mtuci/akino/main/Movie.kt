package com.mtuci.akino.main

data class Movie(
    val id: Int,
    val name: String,
    val year: Int,
    val poster: Poster
)

data class Poster(
    val url: String,
    val previewUrl: String
)

data class MoviesResponse(
    val docs: List<Movie>
)
