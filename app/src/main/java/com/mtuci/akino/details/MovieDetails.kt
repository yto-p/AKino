package com.mtuci.akino.details

data class MovieDetails(
    val id: Int,
    val name: String,
    val description: String,
    val rating: Rating,
    val backdrop: Backdrop,
    val movieLength: Int,
    val countries: List<Country>,
    val persons: List<Person>
)

data class Rating(
    val kp: Double,
    val imdb: Double
)

data class Country(
    val name: String
)

data class Backdrop(
    val url: String,
    val previewUrl: String
)

data class Person(
    val id: Int,
    val photo: String,
    val name: String?
)